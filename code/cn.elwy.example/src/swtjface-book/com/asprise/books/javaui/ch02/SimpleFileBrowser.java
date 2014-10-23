/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 7, 2004 2:38:45 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch02;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 *  
 */
public class SimpleFileBrowser {
	Display display = new Display();
	Shell shell = new Shell(display);

	ImageRegistry imageRegistry;

	Table table;

	public SimpleFileBrowser() {
		init();

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

	private void init() {
		shell.setText("File Browser");
		shell.setLayout(new GridLayout(1, true));
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Browse ...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.NULL);
				String path = dialog.open();
				if (path != null) {

					File file = new File(path);
					if (file.isFile())
						displayFiles(new String[] { file.toString()});
					else
						displayFiles(file.list());

				}
			}
		});

		GridData gd = new GridData(GridData.FILL_BOTH);

		table = new Table(shell, SWT.MULTI);
		table.setLayoutData(gd);

		// creates an image registry and adds icons to the image registry.
		imageRegistry = new ImageRegistry();

		ImageDescriptor defaultIcon =
			ImageDescriptor.createFromFile(null, "img/default.gif");
		imageRegistry.put("default", defaultIcon);

		ImageDescriptor jarIcon =
			ImageDescriptor.createFromFile(null, "img/jar.gif");
		imageRegistry.put("jar", jarIcon);
	}
	


	public void displayFiles(String[] files) {
		// Removes all existing table items.
		table.removeAll();

		for (int i = 0; files != null && i < files.length; i++) {
			TableItem item = new TableItem(table, SWT.NULL);
			Image image = null;

			if (files[i].endsWith(".jar")) {
				image = imageRegistry.get("jar");
			} else {
				image = imageRegistry.get("default");
			}

			item.setImage(image);
			item.setText(files[i]);
		}

	}

	public void displayFiles2(String[] files) {

		// Disposes all of the images used by the table items first. 
		TableItem[] items = table.getItems();
		for(int i=0; items != null && i < items.length; i++) {
			if(items[i].getImage() != null)
				items[i].getImage().dispose();
		}
		
		// Removes all existing table items.
		table.removeAll();

		for (int i = 0; files != null && i < files.length; i++) {
			TableItem item = new TableItem(table, SWT.NULL);
			Image image = null;

			if (files[i].endsWith(".jar")) {
				image = new Image(display, "img/jar.gif");
			} else {
				image = new Image(display, "img/default.gif");
			}

			item.setImage(image);
			item.setText(files[i]);
		}
	}	
	
	public static void main(String[] args) {
		new SimpleFileBrowser();
	}
}
