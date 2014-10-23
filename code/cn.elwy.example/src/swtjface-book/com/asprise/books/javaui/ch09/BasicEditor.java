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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 *  
 */
public class BasicEditor {
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
	public static final String APP_NAME = "BasicEditor v1.0";

	
	MenuItem miWrap = null;
	
	public BasicEditor() {
		shell.setLayout(new GridLayout());
		

		// Add a tool bar. 
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT );
		ToolItem tiNew = new ToolItem(toolBar, SWT.PUSH);
		tiNew.setText("&New");
		tiNew.setImage(getImage("new.gif"));
		tiNew.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(handleChangesBeforeDiscard())  {
					file = null;
					text.setText("");
				}
			}
		});
		
		ToolItem tiOpen = new ToolItem(toolBar, SWT.PUSH);
		tiOpen.setText("&Open");
		tiOpen.setImage(getImage("open.gif"));
		tiOpen.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(handleChangesBeforeDiscard())
					loadTextFromFile();
			}
		});
		
		ToolItem tiSave = new ToolItem(toolBar, SWT.PUSH);
		tiSave.setText("&Save");
		tiSave.setImage(getImage("save.gif"));
		tiSave.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				saveTextToFile();
			}
		});		
		
		new ToolItem(toolBar, SWT.SEPARATOR);
	
		ToolItem tiCopy = new ToolItem(toolBar, SWT.PUSH);
		tiCopy.setText("&Copy");
		tiCopy.setImage(getImage("copy.gif"));
		tiCopy.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.copy();
			}
		});
		
		ToolItem tiCut = new ToolItem(toolBar, SWT.PUSH);
		tiCut.setText("Cu&t");
		tiCut.setImage(getImage("cut.gif"));
		tiCut.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.cut();
			}
		});
		
		ToolItem tiPaste = new ToolItem(toolBar, SWT.PUSH);
		tiPaste.setText("&Paste");
		tiPaste.setImage(getImage("paste.gif"));
		tiPaste.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.paste();
			}
		});
		
		new ToolItem(toolBar, SWT.SEPARATOR);
		
		final ToolItem tiWrap = new ToolItem(toolBar, SWT.CHECK);
		tiWrap.setText("&Wrap");
		tiWrap.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					text.setWordWrap(tiWrap.getSelection());
					miWrap.setSelection(tiWrap.getSelection());
				}
		});		
		
		toolBar.pack();
		
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
		Menu menuBar = new Menu(shell, SWT.BAR);
		
		// --- sub menu: File
		MenuItem fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuItem.setText("&File");
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		
		MenuItem miNew = new MenuItem(fileMenu, SWT.PUSH);
		miNew.setText("&New\tCtrl+N");
		miNew.setImage(getImage("new.gif"));
		miNew.setAccelerator(SWT.CTRL + 'N');
		miNew.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(handleChangesBeforeDiscard())  {
					file = null;
					text.setText("");
				}
			}
		});
		
		MenuItem miOpen = new MenuItem(fileMenu, SWT.PUSH);
		miOpen.setText("&Open\tCtrl+O");
		miOpen.setAccelerator(SWT.CTRL + 'O');
		miOpen.setImage(getImage("open.gif"));
		miOpen.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(handleChangesBeforeDiscard())
					loadTextFromFile();
			}
		});
		
		MenuItem miSave = new MenuItem(fileMenu, SWT.PUSH);
		miSave.setText("&Save\tCtrl+S");
		miSave.setImage(getImage("save.gif"));
		miSave.setAccelerator(SWT.CTRL + 'S');
		miSave.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				saveTextToFile();
			}
		});
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		MenuItem miExit = new MenuItem(fileMenu, SWT.PUSH);
		miExit.setText("&Exit");
		miExit.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(handleChangesBeforeDiscard())
					shell.dispose();
			}
		});
		
		fileMenuItem.setMenu(fileMenu);
		
		// --- sub menu: Edit.
		MenuItem editMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		editMenuItem.setText("&Edit");
		
		Menu editMenu = new Menu(shell, SWT.DROP_DOWN);
		
		MenuItem miCopy = new MenuItem(editMenu, SWT.PUSH);
		miCopy.setText("&Copy\tCtrl+C");
		miCopy.setImage(getImage("copy.gif"));
		miCopy.setAccelerator(SWT.CTRL + 'C');
		miCopy.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.copy();
			}
		});
		
		MenuItem miCut = new MenuItem(editMenu, SWT.PUSH);
		miCut.setText("Cu&t\tCtrl+X");
		miCut.setImage(getImage("cut.gif"));
		miCut.setAccelerator(SWT.CTRL + 'X');
		miCut.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.cut();
			}
		});
		
		MenuItem miPaste = new MenuItem(editMenu, SWT.PUSH);
		miPaste.setText("&Paste\tCtrl+P");
		miPaste.setImage(getImage("paste.gif"));
		miPaste.setAccelerator(SWT.CTRL + 'P');
		miPaste.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.paste();
			}
		});
		
		editMenuItem.setMenu(editMenu);
		
		// --- sub menu: Format.
		MenuItem formatMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		formatMenuItem.setText("&Format");
		
		Menu formatMenu = new Menu(shell, SWT.DROP_DOWN);
		
		miWrap = new MenuItem(formatMenu, SWT.CHECK);
		miWrap.setText("&Wrap\tCtrl+W");
		miWrap.setAccelerator(SWT.CTRL + 'W');
		miWrap.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				text.setWordWrap(miWrap.getSelection());
				tiWrap.setSelection(miWrap.getSelection());
			}
		});
		
		formatMenuItem.setMenu(formatMenu);
		
		// Add the menu bar to the shell.
		shell.setMenuBar(menuBar);
	
		
		
		
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
	 * Retrieves the image corresponding to the given file name. 
	 * Note that the image is managed by an image registry. You should not 
	 * dispose the image after use. 
	 * @param shortFileName
	 * @return
	 */
	private Image getImage(String shortFileName) {
		if(imageRegistry.getDescriptor(shortFileName) == null) {
			ImageDescriptor descriptor = ImageDescriptor.createFromFile(null, "icons/" + shortFileName);
			imageRegistry.put(shortFileName, descriptor);
		}
		return imageRegistry.get(shortFileName);
	}
	
	/**
	 * Hands unsaved changes before the text is discarded. 
	 * @return whether furthur action should be carried on. 
	 */
	boolean handleChangesBeforeDiscard() {
		if(! hasUnsavedChanges)
			return true;
		
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
		messageBox.setMessage("Do you want to save the changes to " + (file == null ? "a file?" : file.getName()));
		messageBox.setText(APP_NAME);
		int ret = messageBox.open();
		if(ret == SWT.YES) {
			return saveTextToFile();
		}else if(ret == SWT.NO) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Loads the text from a file selected by the user. 
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
			while((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			text.setText(sb.toString());
			return true;
		}catch(IOException e) {
			log("Failed to load the text from file: " + file);
			log(e.toString());
		}
		return false;
	}

	/**
	 * Saves the content of the styled text to the file. If the file has
	 * not been specified yet, a FileDialog prompts up for the user to
	 * select a file.
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
		new BasicEditor();
	}
}
