/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 27, 2004 11:50:44 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch06;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class Layouting {

	Display display = new Display();
	Shell shell = new Shell(display);
	
	int count = 0;

	public Layouting() {
		init();
		shell.setLayout(new RowLayout());
		
		final Composite composite = new Composite(shell, SWT.BORDER);
		composite.setLayout(new RowLayout());
		composite.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
		composite.addControlListener(new ControlListener() {
			public void controlMoved(ControlEvent e) {
				// TODO Auto-generated method stub

			}

			public void controlResized(ControlEvent e) {
				System.out.println("Composite resize.");
			}
		});
		
		Button buttonAdd = new Button(shell, SWT.PUSH);
		buttonAdd.setText("Add new button");
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Button button = new Button(composite, SWT.PUSH);
				button.setText("Button #" + (count++));
				//composite.layout(true);
				composite.pack();
				//shell.layout(true;
				// shell.pack(true);
			}
		});
		
	
		// shell.pack();
		shell.setSize(450, 100);
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
		new Layouting();
	}
}
