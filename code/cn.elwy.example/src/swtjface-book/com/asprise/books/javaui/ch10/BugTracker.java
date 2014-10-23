/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 3, 2004 7:53:44 PM by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;

/**
 *  
 */
public class BugTracker {
	Display display = new Display();
	Shell shell = new Shell(display);

	Table table;

	Image bugIcon = new Image(shell.getDisplay(), "icons/bug.gif");

	public BugTracker() {
		GridLayout gridLayout = new GridLayout();
		shell.setLayout(gridLayout);
		shell.setText("Bug Tracking System");

		// Action.
		Action actionAddNew = new Action("New bug") {
			public void run() {
					// Append.
	TableItem item = new TableItem(table, SWT.NULL);
				item.setImage(bugIcon);
				table.select(table.getItemCount() - 1);
			}
		};

		Action actionDelete = new Action("Delete selected") {
			public void run() {
				int index = table.getSelectionIndex();
				if (index < 0) {
					System.out.println("Please select an item first. ");
					return;
				}
				MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
				messageBox.setText("Confirmation");
				messageBox.setMessage(
					"Are you sure to remove the bug with id #"
						+ table.getItem(index).getText(0));
				if (messageBox.open() == SWT.YES) {
					table.remove(index);
				}
			}
		};

		Action actionSave = new Action("Save") {
			public void run() {
				saveBugs();
			}
		};

		ToolBar toolBar = new ToolBar(shell, SWT.RIGHT | SWT.FLAT);

		ToolBarManager manager = new ToolBarManager(toolBar);
		manager.add(actionAddNew);
		manager.add(actionDelete);
		manager.add(new Separator());
		manager.add(actionSave);
		manager.update(true);

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tcID = new TableColumn(table, SWT.LEFT);
		tcID.setText("ID");

		TableColumn tcSummary = new TableColumn(table, SWT.NULL);
		tcSummary.setText("Summary");

		TableColumn tcAssignedTo = new TableColumn(table, SWT.NULL);
		tcAssignedTo.setText("Assigned to");

		TableColumn tcSolved = new TableColumn(table, SWT.NULL);
		tcSolved.setText("Solved");

		tcID.setWidth(60);
		tcSummary.setWidth(200);
		tcAssignedTo.setWidth(80);
		tcSolved.setWidth(50);

		table.pack();

		// Table editor.
		final TableEditor editor = new TableEditor(table);

		table.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				// Locate the cell position.
				Point point = new Point(event.x, event.y);
				final TableItem item = table.getItem(point);
				if (item == null)
					return;
				int column = -1;
				for (int i = 0; i < table.getColumnCount(); i++) {
					Rectangle rect = item.getBounds(i);
					if (rect.contains(point))
						column = i;
				}
				if (column != 3)
					return;

				// Cell position located, now open the table editor.

				final Button button = new Button(table, SWT.CHECK);
				button.setSelection(
					item.getText(column).equalsIgnoreCase("YES"));

				editor.horizontalAlignment = SWT.LEFT;
				editor.grabHorizontal = true;

				editor.setEditor(button, item, column);

				final int selectedColumn = column;
				Listener buttonListener = new Listener() {
					public void handleEvent(final Event e) {
						switch (e.type) {
							case SWT.FocusOut :
								item.setText(
									selectedColumn,
									button.getSelection() ? "YES" : "NO");
								button.dispose();
								break;
							case SWT.Traverse :
								switch (e.detail) {
									case SWT.TRAVERSE_RETURN :
										item.setText(
											selectedColumn,
											button.getSelection()
												? "YES"
												: "NO");
										//FALL THROUGH
									case SWT.TRAVERSE_ESCAPE :
										button.dispose();
										e.doit = false;
								}
								break;
						}
					}
				};

				button.addListener(SWT.FocusOut, buttonListener);
				button.addListener(SWT.Traverse, buttonListener);

				button.setFocus();

			}
		});

		table.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				// Locate the cell position.
				Point point = new Point(event.x, event.y);
				final TableItem item = table.getItem(point);
				if (item == null)
					return;
				int column = -1;
				for (int i = 0; i < table.getColumnCount(); i++) {
					Rectangle rect = item.getBounds(i);
					if (rect.contains(point))
						column = i;
				}
				if (column < 0 || column >= 3)
					return;

				// Cell position located, now open the table editor.

				final Text text = new Text(table, SWT.NONE);
				text.setText(item.getText(column));

				editor.horizontalAlignment = SWT.LEFT;
				editor.grabHorizontal = true;

				editor.setEditor(text, item, column);

				final int selectedColumn = column;
				Listener textListener = new Listener() {
					public void handleEvent(final Event e) {
						switch (e.type) {
							case SWT.FocusOut :
								item.setText(selectedColumn, text.getText());
								text.dispose();
								break;
							case SWT.Traverse :
								switch (e.detail) {
									case SWT.TRAVERSE_RETURN :
										item.setText(
											selectedColumn,
											text.getText());
										//FALL THROUGH
									case SWT.TRAVERSE_ESCAPE :
										text.dispose();
										e.doit = false;
								}
								break;
						}
					}
				};

				text.addListener(SWT.FocusOut, textListener);
				text.addListener(SWT.Traverse, textListener);

				text.setFocus();
			}

		});

		loadBugs();

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Selected: " + table.getSelection()[0]);
			}
		});
		
		Listener sortListener = new Listener() {
			public void handleEvent(Event event) {
				if(! (event.widget instanceof TableColumn))
					return;
				TableColumn tc = (TableColumn)event.widget;
				sortTable(table, table.indexOf(tc));
				System.out.println("The table is sorted by column #" + table.indexOf(tc));
			}
		};
		
		for(int i=0; i<table.getColumnCount(); i++)
			((TableColumn)table.getColumn(i)).addListener(SWT.Selection, sortListener);

		shell.pack();
		shell.open();
		//textUser.forceFocus();

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
	 * Sorts the given table by the specified column.
	 * @param columnIndex
	 */
	public static void sortTable(Table table, int columnIndex) {
		if(table == null || table.getColumnCount() <= 1)
			return;
		if(columnIndex < 0 || columnIndex >= table.getColumnCount())
			throw new IllegalArgumentException("The specified column does not exist. ");
		
		final int colIndex = columnIndex;
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((TableItem)o1).getText(colIndex).compareTo(((TableItem)o2).getText(colIndex));
			}

			public boolean equals(Object obj) {
				return false;
			}
		};
		
		TableItem[] tableItems = table.getItems();
		Arrays.sort(tableItems, comparator);
		
		for(int i=0; i<tableItems.length; i++) {
			TableItem item = new TableItem(table, SWT.NULL);
			for(int j=0; j<table.getColumnCount(); j++) {
				item.setText(j, tableItems[i].getText(j));
				item.setImage(j, tableItems[i].getImage(j));
			}
			tableItems[i].dispose();
		}
	}

	private void saveBugs() {
		// Save bugs to a file.
		DataOutputStream out = null;
		try {
			File file = new File("bugs.dat");

			out = new DataOutputStream(new FileOutputStream(file));

			for (int i = 0; i < table.getItemCount(); i++) {
				TableItem item = table.getItem(i);
				out.writeUTF(item.getText(0));
				out.writeUTF(item.getText(1));
				out.writeUTF(item.getText(2));
				out.writeBoolean(item.getText(3).equalsIgnoreCase("YES"));
			}
		} catch (IOException ioe) {
			// Ignore.
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadBugs() {
		// Load bugs from a file.
		DataInputStream in = null;

		try {
			File file = new File("bugs.dat");
			if (!file.exists())
				return;
			in = new DataInputStream(new FileInputStream(file));

			while (true) {
				String id = in.readUTF();
				String summary = in.readUTF();
				String assignedTo = in.readUTF();
				boolean solved = in.readBoolean();

				TableItem item = new TableItem(table, SWT.NULL);
				item.setImage(bugIcon);
				item.setText(
					new String[] {
						id,
						summary,
						assignedTo,
						solved ? "Yes" : "No" });
			}

		} catch (IOException ioe) {
			// Ignore.

		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new BugTracker();
	}
}
