/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Dec 23, 2003 2:52:59 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch01;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 *  
 */
public class PhotoUploaderSWT extends PhotoUploader {

	Display display = new Display();
	Shell shell = new Shell(display);

	Label labelUser;
	Label labelPhoto;

	Text textUser;
	Text textPhoto;

	Button buttonBrowsePhoto;

	Button buttonUpload;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asprise.swt.example.ch01.PhotoUploader#init()
	 */
	public void init() {
		GridLayout gridLayout = new GridLayout(3, false);
		shell.setLayout(gridLayout);

		// Adds components to the first row.
		labelUser = new Label(shell, SWT.NULL);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		textUser = new Text(shell, SWT.SINGLE | SWT.BORDER);
		textUser.setLayoutData(gridData);

		new Label(shell, SWT.NULL);

		// 2nd row.
		labelPhoto = new Label(shell, SWT.NULL);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		textPhoto = new Text(shell, SWT.SINGLE | SWT.BORDER);
		textPhoto.setLayoutData(gridData);

		buttonBrowsePhoto = new Button(shell, SWT.PUSH);

		// last row.
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.CENTER;
		buttonUpload = new Button(shell, SWT.PUSH);
		buttonUpload.setLayoutData(gridData);

		shell.setText("Photo Uploader (SWT)");

		labelUser.setText("User name: ");
		labelPhoto.setText("Photo: ");

		buttonBrowsePhoto.setText("Browse");
		buttonUpload.setText("Upload");
		
		// Register listeners.
		buttonBrowsePhoto.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					textPhoto.setText(file);
				}
			}
		});

		buttonUpload.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				uploadPhoto(textUser.getText(), textPhoto.getText());
				shell.dispose();
			}
		});
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asprise.swt.example.ch01.PhotoUploader#prompt()
	 */
	public void prompt() {
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

}
