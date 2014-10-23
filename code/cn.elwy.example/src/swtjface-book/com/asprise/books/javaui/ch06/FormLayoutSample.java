/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 30, 2004 7:11:12 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch06;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class FormLayoutSample {

	Display display = new Display();
	Shell shell = new Shell(display);

	public FormLayoutSample() {

		shell.setLayout(new FormLayout());
		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("button1");

		FormData formData = new FormData();
		formData.left = new FormAttachment(20);
		formData.top = new FormAttachment(20);
		button1.setLayoutData(formData);		
		
		
		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("button number 2");
		
		formData = new FormData();
		formData.left = new FormAttachment(button1, 0, SWT.CENTER);
		formData.top = new FormAttachment(button1, 0, SWT.CENTER);
		button2.setLayoutData(formData);
		
//		Button button3 = new Button(shell, SWT.PUSH);
//		button3.setText("3");
//		
//		formData = new FormData();
//		formData.top = new FormAttachment(button2, 10);
//		formData.left = new FormAttachment(button2, 0, SWT.LEFT);
//		button3.setLayoutData(formData);

		shell.pack();
		//shell.setSize(500, 600);
		shell.open();
		//textUser.forceFocus();
		
		//System.out.println("Button3: " + button3.getBounds());

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
		new FormLayoutSample();
	}
}
