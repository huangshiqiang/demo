/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 23, 2004 8:56:30 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class WidgetStyles {
	Display display = new Display();
	Shell shell = new Shell(display);
	Button button;

	public WidgetStyles() {
		init();

		shell.pack();
		shell.open();
		
		
		System.out.println("Bounds: " + button.getBounds() );
		System.out.println("computeSize: " + button.computeSize(100, SWT.DEFAULT));
		System.out.println("computeSize: " + button.computeSize(40, SWT.DEFAULT));
		System.out.println("computeSize: " + button.computeSize(SWT.DEFAULT, 100));
		System.out.println("computeSize: " + button.computeSize(SWT.DEFAULT, 20));
		System.out.println("computeSize: " + button.computeSize(SWT.DEFAULT, 15));
		System.out.println("computeSize: " + button.computeSize(100, 200));
		System.out.println("computeSize: " + button.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		System.out.println("toControl: " + button.toControl(100, 200));
		System.out.println("toDisplay: " + button.toDisplay(100, 200));
		
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				System.out.println(e.widget + " disposed");

			}
		});

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
		
		button = new Button(shell, SWT.PUSH | SWT.LEFT);
		button.setText("Button");
	}

	public static void main(String[] args) {
		new WidgetStyles();
	}
}
