/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 15, 2004 1:22:06 AM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch03;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class HelloWorld {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		shell.setText("Hello, world!");
		
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
}
