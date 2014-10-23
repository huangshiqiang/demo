/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 29, 2004 12:29:17 AM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch06;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class RowLayoutSample {
	Display display = new Display();
	Shell shell = new Shell(display);

	public RowLayoutSample() {
		RowLayout rowLayout = new RowLayout();
		//rowLayout.fill = true;
		//rowLayout.justify = true;
		//rowLayout.pack = false;
		//rowLayout.type = SWT.VERTICAL;
		//rowLayout.wrap = false;
		
		shell.setLayout(rowLayout);
		
		
		
		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("button1");
		button1.setLayoutData(new RowData(100, 35));
		
		List list = new List(shell, SWT.BORDER);
		list.add("item 1");
		list.add("item 2");
		list.add("item 3");
		
		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("button #2");				
		
		//shell.setSize(120, 120);
		shell.pack();
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

	private void init() {

	}

	public static void main(String[] args) {
		new RowLayoutSample();
	}
}
