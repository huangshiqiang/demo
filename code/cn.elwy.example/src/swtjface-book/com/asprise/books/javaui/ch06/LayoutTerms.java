/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 27, 2004 7:23:10 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch06;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class LayoutTerms {
	Display display = new Display();
	Shell shell = new Shell(display);

	public LayoutTerms() {
		init();
		
		Composite composite;
		
		System.out.println("Bounds: " + shell.getBounds());
		System.out.println("Client area: " + shell.getClientArea());
		
		shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.wrap = true;
		
		rowLayout.marginLeft = 15;
		rowLayout.marginTop = 5;
		rowLayout.marginRight = 15;
		rowLayout.marginBottom = 10;
		
		rowLayout.spacing = 8;
		
	
		
		shell.setLayout(rowLayout);
		
		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("button1");
		
		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("button number 2");
		
		Button button3 = new Button(shell, SWT.PUSH);
		button3.setText("3");
		


		shell.pack();
		shell.open();

		System.out.println("button1: " + button1.getBounds());
		System.out.println("button2: " + button2.getBounds());

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
		new LayoutTerms();
	}
}
