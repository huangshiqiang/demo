/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 26, 2004 7:38:13 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class ButtonStyles {


	public ButtonStyles() {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		shell.setLayout(new GridLayout(5, true));
		
		Button arrowButton = new Button(shell, SWT.ARROW);
		arrowButton.setText("&Arrow Button");

		Button checkButton = new Button(shell, SWT.CHECK);
		checkButton.setText("&Check Button");
		
		Button pushButton = new Button(shell, SWT.PUSH);
		pushButton.setText("&Push Button");
		
		Button radioButton = new Button(shell, SWT.RADIO);
		radioButton.setText("&Radio Button");
		
		Button toggleButton = new Button(shell, SWT.TOGGLE);
		toggleButton.setText("&Toggle Button");	
		
		// shell.pack();
		shell.setSize(500, 100);
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

	}

	public static void main(String[] args) {
		new ButtonStyles();
	}
}
