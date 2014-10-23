/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 23, 2004 8:40:41 PM by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

/**
 *  
 */
public class BasicEditor2 {
	Display display = new Display();
	Shell shell = new Shell(display);

	// The control used for text displaying and editing.
	StyledText text;

	// Is there any changes since last saving action?
	boolean hasUnsavedChanges;

	// The file associated with current text content.
	File file;

	// The recent directory
	private String lastOpenDirectory;

	// The name of this program.
	public static final String APP_NAME = "BasicEditor v2.0";

	MenuItem miWrap = null;

	public BasicEditor2() {
		

		// Action: create new text.
		Action actionNew =
			new Action(
				"&New",
				ImageDescriptor.createFromFile(null, "icons/new.gif")) {
			public void run() {
				if (handleChangesBeforeDiscard()) {
					file = null;
					text.setText("");
				}
			}
		};
		actionNew.setAccelerator(SWT.CTRL + 'N');

		// Action: open a text file.
		Action actionOpen =
			new Action(
				"&Open",
				ImageDescriptor.createFromFile(null, "icons/open.gif")) {
			public void run() {
				if (handleChangesBeforeDiscard())
					loadTextFromFile();
			}
		};
		actionOpen.setAccelerator(SWT.CTRL + 'O');
		
		// Action: save the text to a file. 
		Action actionSave =
			new Action(
				"&Save\tCtrl+S",
				ImageDescriptor.createFromFile(null, "icons/save.gif")) {
			public void run() {
				saveTextToFile();
			}
		};
		//actionSave.setAccelerator(SWT.CTRL + 'S');
		
		// Action: copy selected text.
		Action actionCopy =
			new Action(
				"&Copy",
				ImageDescriptor.createFromFile(null, "icons/copy.gif")) {
			public void run() {
				text.copy();
			}
		};
		actionCopy.setAccelerator(SWT.CTRL + 'C');
		
		// Separator. 
		
		// Action: cut the selected text. 
		Action actionCut =
			new Action(
				"Cu&t",
				ImageDescriptor.createFromFile(null, "icons/cut.gif")) {
			public void run() {
				text.cut();
			}
		};
		actionCut.setAccelerator(SWT.CTRL + 'X');
		
		// Action: paste the text on clipboard. 
		Action actionPaste =
			new Action(
				"&Paste",
				ImageDescriptor.createFromFile(null, "icons/paste.gif")) {
			public void run() {
				text.paste();
			}
		};
		actionPaste.setAccelerator(SWT.CTRL + 'P');
		
		
		// Separator. 
		
		// Action: set wrap property. 
		Action actionWrap =
			new Action(
				"&Wrap", IAction.AS_CHECK_BOX) {
			public void run() {
				text.setWordWrap(isChecked());
			}
		};
		actionWrap.setAccelerator(SWT.CTRL + 'W');
		
		// Action: exit. 
		Action actionExit = new Action("&Exit@Ctrl+X") {
			public void run() {
				if (handleChangesBeforeDiscard())
					shell.dispose();
			}
		};
		
		System.out.println(actionWrap.getText());


		
		
		// Add a tool bar.
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		
		toolBarManager.add(actionNew);
		toolBarManager.add(actionOpen);
		toolBarManager.add(actionSave);
		toolBarManager.add(new Separator());
		toolBarManager.add(actionCopy);
		toolBarManager.add(actionCut);
		toolBarManager.add(actionPaste);
		
		toolBarManager.add(new Separator());
		
		toolBarManager.add(actionWrap);

		toolBarManager.update(true);

		
		shell.setLayout(new GridLayout());

		System.out.println("Client area: " + shell.getClientArea());

		text =
			new StyledText(
				shell,
				SWT.MULTI
					| SWT.WRAP
					| SWT.BORDER
					| SWT.H_SCROLL
					| SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));

		Font font = new Font(shell.getDisplay(), "Courier New", 10, SWT.NORMAL);
		text.setFont(font);

		text.setText("BasicEditor version 1.0\r\nWriten by Jack Li Guojie. ");
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				hasUnsavedChanges = true;
			}
		});

		// Add menus.
		MenuManager barMenuManager = new MenuManager();
		
		MenuManager fileMenuManager = new MenuManager("&File");
		MenuManager editMenuManager = new MenuManager("&Edit");
		MenuManager formatMenuManager = new MenuManager("&Format");
		
		barMenuManager.add(fileMenuManager);
		barMenuManager.add(editMenuManager);
		barMenuManager.add(formatMenuManager);
		
		fileMenuManager.add(actionNew);
		fileMenuManager.add(actionOpen);
		fileMenuManager.add(actionSave);
		fileMenuManager.add(new Separator());
		
		fileMenuManager.add(actionExit);
		
		editMenuManager.add(actionCopy);
		editMenuManager.add(actionCut);
		editMenuManager.add(actionPaste);
		
		formatMenuManager.add(actionWrap);

		// Add the menu bar to the shell.
		// shell.setMenuBar(menuBar);
		barMenuManager.updateAll(true);
		shell.setMenuBar(barMenuManager.createMenuBar((Decorations)shell));

		shell.setSize(400, 200);
		shell.open();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();
	}

	ImageRegistry imageRegistry = new ImageRegistry();

	/**
	 * Retrieves the image corresponding to the given file name. Note that the
	 * image is managed by an image registry. You should not dispose the image
	 * after use.
	 * 
	 * @param shortFileName
	 * @return
	 */
	private Image getImage(String shortFileName) {
		if (imageRegistry.getDescriptor(shortFileName) == null) {
			ImageDescriptor descriptor =
				ImageDescriptor.createFromFile(null, "icons/" + shortFileName);
			imageRegistry.put(shortFileName, descriptor);
		}
		return imageRegistry.get(shortFileName);
	}

	/**
	 * Hands unsaved changes before the text is discarded.
	 * 
	 * @return whether furthur action should be carried on.
	 */
	boolean handleChangesBeforeDiscard() {
		if (!hasUnsavedChanges)
			return true;

		MessageBox messageBox =
			new MessageBox(
				shell,
				SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
		messageBox.setMessage(
			"Do you want to save the changes to "
				+ (file == null ? "a file?" : file.getName()));
		messageBox.setText(APP_NAME);
		int ret = messageBox.open();
		if (ret == SWT.YES) {
			return saveTextToFile();
		} else if (ret == SWT.NO) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Loads the text from a file selected by the user.
	 * 
	 * @return the status of the operation.
	 */
	boolean loadTextFromFile() {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		if (lastOpenDirectory != null)
			dialog.setFilterPath(lastOpenDirectory);

		String selectedFile = dialog.open();
		if (selectedFile == null) {
			log("Action cancelled: loading the text from a file");
			return false;
		}

		file = new File(selectedFile);
		lastOpenDirectory = file.getParent();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			text.setText(sb.toString());
			return true;
		} catch (IOException e) {
			log("Failed to load the text from file: " + file);
			log(e.toString());
		}
		return false;
	}

	/**
	 * Saves the content of the styled text to the file. If the file has not
	 * been specified yet, a FileDialog prompts up for the user to select a
	 * file.
	 * 
	 * @return the status of the operation.
	 * @throws IOException
	 */
	boolean saveTextToFile() {
		if (file == null) {
			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			if (lastOpenDirectory != null)
				dialog.setFilterPath(lastOpenDirectory);

			String selectedFile = dialog.open();
			if (selectedFile == null) {
				log("Action cancelled: saving the text to a file");
				return false;
			}

			file = new File(selectedFile);

			lastOpenDirectory = file.getParent();
		}

		try {
			FileWriter writer = new FileWriter(file);
			writer.write(text.getText());
			writer.close();
			log("The text has been saved to file: " + file);

			hasUnsavedChanges = false;
			return true;
		} catch (IOException e) {
			log("Failed to save the text to file: " + file);
			log(e.toString());
		}
		return false;
	}

	/**
	 * Logs system messages.
	 * 
	 * @param message
	 */
	void log(String message) {
		System.out.println(message);
	}

	public static void main(String[] args) {
		new BasicEditor2();
	}
}
