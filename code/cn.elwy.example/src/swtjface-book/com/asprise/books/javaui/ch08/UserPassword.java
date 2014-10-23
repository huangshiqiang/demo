/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 17, 2004 9:28:47 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch08;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class UserPassword {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	Text textUser;
	Text textPassword;

	private void init() {
		(new Label(shell, SWT.NULL)).setText("User name: ");
		
		textUser = new Text(shell, SWT.SINGLE | SWT.BORDER);
		textUser.setText("default_user");
		textUser.setTextLimit(16);
		
		(new Label(shell, SWT.NULL)).setText("Password: ");
		
		textPassword = new Text(shell, SWT.SINGLE | SWT.BORDER);
		System.out.println(textPassword.getEchoChar());
		textPassword.setEchoChar('*');
	}	
	
	public UserPassword() {
		shell.setLayout(new GridLayout(2, false));
		
		init();
		
		textUser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
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



	public static void main(String[] args) {
		new UserPassword();
	}
}
