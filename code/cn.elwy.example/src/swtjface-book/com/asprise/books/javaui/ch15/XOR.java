/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-19 15:31:18 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class XOR {
	Display display = new Display();
	Shell shell = new Shell(display);

	public XOR() {
		shell.setLayout(new FillLayout());
		final Canvas canvas = new Canvas(shell, SWT.NULL);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.setXORMode(true);
				
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
				e.gc.fillOval(60, 10, 100, 100); // Top 
				
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
				e.gc.fillOval(10, 60, 120, 120); // left bottom		
				
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
				e.gc.fillOval(110, 60, 100, 100); // right bottom		
				
			}
		});
		
		shell.setSize(300, 220);
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
		new XOR();
	}
}
