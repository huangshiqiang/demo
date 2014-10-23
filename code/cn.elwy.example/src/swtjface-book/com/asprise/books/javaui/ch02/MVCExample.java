/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 8, 2004 7:35:41 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch02;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class MVCExample {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	String[] toolkits = new String[] { "AWT", "Swing", "SWT/JFace" };

	public MVCExample() {
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
		shell.setLayout(new GridLayout());
		shell.setText("Java UI Toolkit List");
		
		List list = new List(shell, SWT.MULTI | SWT.BORDER);
		for(int i=0; i < toolkits.length; i++)
			list.add(toolkits[i]);
		
		GridData gridData = new GridData(GridData.FILL_BOTH);
		list.setLayoutData(gridData);
	}

	public static void main(String[] args) {
		new MVCExample();
	}
}
