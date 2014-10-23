/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-3-29 13:32:09 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch13;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class Scales {
	Display display = new Display();
	Shell shell = new Shell(display);

	public Scales() {
		Scale scaleH = new Scale(shell, SWT.NULL);
		Scale scaleV = new Scale(shell, SWT.VERTICAL);
		
		scaleH.setBounds(0, 0, 100, 50);
		scaleV.setBounds(0, 50, 50, 100);
		
		System.out.println("Min: " + scaleH.getMinimum());
		System.out.println("Max: " + scaleH.getMaximum());

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
		new Scales();
	}
}
