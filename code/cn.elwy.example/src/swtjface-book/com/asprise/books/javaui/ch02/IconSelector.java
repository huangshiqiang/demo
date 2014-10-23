/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 5, 2004 2:15:34 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch02;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
public class IconSelector {

	Display display = new Display();
	Shell shell = new Shell(display);

	Label labelIconFile;

	Text textIconFile;
	
	Button buttonIconBrowse;
	Button buttonSetIcon;
	
	Image shellIcon;
	
	Image buttonIcon;
	
	public IconSelector() {
		initializeUI();
		
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
	
	private void initializeUI() {
		GridLayout gridLayout = new GridLayout(3, false);
		shell.setLayout(gridLayout);

		// Adds components to the first row.
		labelIconFile = new Label(shell, SWT.NULL);
		
		textIconFile = new Text(shell, SWT.SINGLE | SWT.BORDER);
		
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		textIconFile.setLayoutData(gridData);

		
		buttonIconBrowse = new Button(shell, SWT.PUSH);

		// last row.
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.CENTER;
		buttonSetIcon = new Button(shell, SWT.PUSH);
		buttonSetIcon.setLayoutData(gridData);

		shell.setText("Icon Selector");

		labelIconFile.setText("Select an icon:");

		buttonIconBrowse.setText("Browse");
		buttonSetIcon.setText("Set Icon");

		// Register listeners.
		buttonIconBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					textIconFile.setText(file);
				}
			}
		});

		buttonSetIcon.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(shellIcon != null) 
					shellIcon.dispose();
				
				try {
					shellIcon = new Image(display, textIconFile.getText());
					shell.setImage(shellIcon);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});	
	
	}
	
	public static void main(String[] args) {
		IconSelector iconSelector = new IconSelector();
	}
}
