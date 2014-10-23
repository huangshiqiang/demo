/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-27 16:53:36 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


/**
 * Represents a bookmark.
 *
 */
class Bookmark {
	public String name;
	public String href;
	public String addDate;
	public String lastVisited;
	public String lastModified;
}

/**
 *  
 */
public class BookmarkOrganizer {
	private static String folderLinePrefix = "<DT><H3 FOLDED";
	private static String urlLinePrefix = "<DT><A HREF";
	private static Pattern folderPattern = Pattern.compile("\"(\\d+)\">(.*)<");
	private static Pattern urlPattern =
		Pattern.compile("\"(.*)\".*\"(.*)\".*\"(.*)\".*\"(.*)\">(.*)<");

	private static String KEY_ADD_DATE = "ADD_DATE";
	private static String KEY_HREF = "HREF";
	private static String KEY_LAST_VISITED = "LAST_VISITED";
	private static String KEY_LAST_MODIFIED = "LAST_MODIFIED";

	Display display = new Display();
	Shell shell = new Shell(display);

	Tree tree;
	Label label;

	TreeItem rootItem;

	Image iconRoot = new Image(display, "icons/icon.gif");
	Image iconFolder = new Image(display, "icons/folder.gif");
	Image iconURL = new Image(display, "icons/file.gif");

	TreeItem dragSourceItem;

	public BookmarkOrganizer() {
		shell.setText("Bookmark organizer");
		shell.setLayout(new GridLayout(1, true));

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
		ToolItem itemOpen = new ToolItem(toolBar, SWT.PUSH);
		itemOpen.setText("Load");
		itemOpen.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					// removes existing items.
					TreeItem[] items = rootItem.getItems();
					for (int i = 0; i < items.length; i++)
						items[i].dispose();

					loadBookmark(new File(file), rootItem);
					setStatus("Bookmarks loaded successfully");
				}
			}
		});

		ToolItem itemSave = new ToolItem(toolBar, SWT.PUSH);
		itemSave.setText("Save as");
		itemSave.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				String file = dialog.open();
				if (file != null) {
					try {
						BufferedWriter writer =
							new BufferedWriter(new FileWriter(file));
						saveBookmark(writer, rootItem);
						writer.close();
						setStatus(
							"Bookmarks saved successfully to file: " + file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		tree = new Tree(shell, SWT.BORDER);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		rootItem = new TreeItem(tree, SWT.NULL);
		rootItem.setText("BOOKMARKS");
		rootItem.setImage(iconRoot);

		label = new Label(shell, SWT.BORDER);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final DragSource dragSource =
			new DragSource(tree, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK);
		dragSource.setTransfer(
			new Transfer[] { BookmarkTransfer.getInstance()});

		dragSource.addDragListener(new DragSourceAdapter() {
			public void dragStart(DragSourceEvent event) {
				TreeItem[] selection = tree.getSelection();
				// Only a URL bookmark can be dragged.
				if (selection.length > 0 && selection[0].getData() != null) {
					event.doit = true;
					dragSourceItem = selection[0];
				} else {
					event.doit = false;
				}
			};

			public void dragSetData(DragSourceEvent event) {
				if (BookmarkTransfer
					.getInstance()
					.isSupportedType(event.dataType))
					event.data = dragSourceItem.getData();
			}

			public void dragFinished(DragSourceEvent event) {
				if (event.detail == DND.DROP_MOVE)
					dragSourceItem.dispose();
				dragSourceItem = null;
			}

		});

		final DropTarget dropTarget =
			new DropTarget(tree, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK);
		dropTarget.setTransfer(
			new Transfer[] { BookmarkTransfer.getInstance()});
		dropTarget.addDropListener(new DropTargetAdapter() {
			public void dragOver(DropTargetEvent event) {
				event.feedback =
					DND.FEEDBACK_EXPAND
						| DND.FEEDBACK_SCROLL
						| DND.FEEDBACK_SELECT;
			}

			public void dropAccept(DropTargetEvent event) {
				// can only drops into to a folder
				if (event.item == null
					|| ((TreeItem) event.item).getData() != null)
					event.detail = DND.DROP_NONE;
			}

			public void drop(DropTargetEvent event) {
				try {
					if (event.data == null) {
						event.detail = DND.DROP_NONE;
						return;
					}

					TreeItem item =
						new TreeItem((TreeItem) event.item, SWT.NULL);
					Bookmark bookmark = (Bookmark) event.data;
					item.setText(bookmark.name);
					item.setImage(iconURL);
					item.setData(bookmark);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		});

		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				Bookmark bookmark = (Bookmark) item.getData();
				if (bookmark != null) {
					setStatus(bookmark.href);
				} else if (item.getData(KEY_ADD_DATE) != null) { // folder.
					setStatus("Folder: " + item.getText());
				}
			}
		});

		shell.setSize(400, 300);
		shell.open();
		//textUser.forceFocus();

		loadBookmark(new File("icons/mybookmark.htm"), rootItem);

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();
	}

	/**
	 * Writes the bookmark(s) into the given buffered writer. 
	 * @param writer
	 * @param item
	 * @throws IOException
	 */
	private void saveBookmark(BufferedWriter writer, TreeItem item)
		throws IOException {
		if (item.getData() == null
			&& item.getData(KEY_ADD_DATE) == null) { // root item.
			writer.write(
				"<!DOCTYPE NETSCAPE-Bookmark-file-1>\n"
					+ "<!-- This is an automatically generated file.\n"
					+ "It will be read and overwritten.\n"
					+ "Do Not Edit! -->\n"
					+ "<TITLE>Bookmarks</TITLE>\n"
					+ "<H1>Bookmarks</H1>\n"
					+ "<DL><p>\n");

			TreeItem[] items = item.getItems();
			for (int i = 0; i < items.length; i++)
				saveBookmark(writer, items[i]);

			writer.write("</DL><p>\n");
		} else if (item.getData() == null) { // folder
			writer.write(
				"\t<DT><H3 FOLDED ADD_DATE=\""
					+ item.getData(KEY_ADD_DATE)
					+ "\">"
					+ item.getText()
					+ "</H3>\n");
			writer.write("\t<DL><p>\n");

			TreeItem[] items = item.getItems();
			for (int i = 0; i < items.length; i++)
				saveBookmark(writer, items[i]);

			writer.write("\t</DL><p>\n");

		} else { // url.
			Bookmark bookmark = (Bookmark) item.getData();
			writer.write(
				"\t\t<DT><A HREF=\""
					+ bookmark.href
					+ "\" ADD_DATE=\""
					+ bookmark.addDate
					+ "\" LAST_VISIT=\""
					+ bookmark.lastVisited
					+ "\" LAST_MODIFIED=\" +"
					+ bookmark.lastModified
					+ "\">"
					+ bookmark.name
					+ "</A>\n");
		}
	}

	/**
	 * Loads the bookmarks from the specified file. 
	 * @param file
	 * @param rootItem
	 */
	private void loadBookmark(File file, TreeItem rootItem) {
		TreeItem parent = rootItem;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.startsWith(folderLinePrefix)) { // a folder.
					Matcher matcher = folderPattern.matcher(line);
					if (matcher.find()) {
						String addDate = matcher.group(1);
						String name = matcher.group(2);

						TreeItem item = new TreeItem(parent, SWT.NULL);
						item.setText(name);
						item.setData(KEY_ADD_DATE, addDate);
						item.setImage(iconFolder);
						parent = item;
					}
				} else if (line.startsWith(urlLinePrefix)) { // a url
					Matcher matcher = urlPattern.matcher(line);
					if (matcher.find()) {
						Bookmark bookmark = new Bookmark();
						bookmark.href = matcher.group(1);
						bookmark.addDate = matcher.group(2);
						bookmark.lastVisited = matcher.group(3);
						bookmark.lastModified = matcher.group(4);
						bookmark.name = matcher.group(5);

						TreeItem item = new TreeItem(parent, SWT.NULL);
						item.setText(bookmark.name);
						item.setData(bookmark);
						item.setImage(iconURL);

					}
				} else if (line.equals("</DL><p>")) { // folder boundry.
					parent = parent.getParentItem();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setStatus(String message) {
		label.setText(message);
	}

	public static void main(String[] args) {
		new BookmarkOrganizer();
	}
}
