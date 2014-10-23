/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Feb 8, 2004 3:43:08 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch07;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class DropDownAndSimple {
	Display display = new Display();
	Shell shell = new Shell(display);

	public DropDownAndSimple() {
		init();
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 15;
		rowLayout.marginWidth = 15;
		rowLayout.marginHeight = 15;
		
		shell.setLayout(rowLayout);
		
		Combo comboDropDown = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER);
		
		Combo comboSimple = new Combo(shell, SWT.SIMPLE | SWT.BORDER);
		
		for(int i=0; i<3; i++) {
			comboDropDown.add("item " + i);
			comboSimple.add("item " + i);
		}

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
		new DropDownAndSimple();
	}
}
