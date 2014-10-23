/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 25, 2004 4:28:59 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class ShellTrim {
	Display display = new Display();
	Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.TOOL);
	//Shell shell = new Shell(display, SWT.NO_TRIM);
	// Shell shell = new Shell(display, SWT.MAX);

	public ShellTrim() {
		init();
		
		shell.setText("Style: SHELL_TRIM | TOOL");

		shell.pack();
		shell.open();
		
		Shell shell2 = new Shell();
		shell2.pack();
		shell2.open();
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
		new ShellTrim();
	}
}
