/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 26, 2004 9:39:30 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class LabelWrap {


	public LabelWrap() {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		String text = "Professional Java Interfaces With SWT/JFace, by Jack Li Guojie";
		
		Label labelNoWrap = new Label(shell, SWT.BORDER);
		labelNoWrap.setText(text);
		labelNoWrap.setBounds(10, 10, 100, 100);
		
		Label labelWrap = new Label(shell, SWT.WRAP | SWT.BORDER);
		labelWrap.setText(text);
		labelWrap.setBounds(120, 10, 100, 100);
		
		
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

	}

	public static void main(String[] args) {
		new LabelWrap();
	}
}
