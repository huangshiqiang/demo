/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-18 16:27:03 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class CanvasExample {
	Display display = new Display();
	Shell shell = new Shell(display);

	public CanvasExample() {
		Canvas canvas = new Canvas(shell, SWT.NULL);
		canvas.setBounds(10, 10, 200, 100);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawRoundRectangle(10, 10, 180, 80, 10, 10);
			}
		});
		
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
		new CanvasExample();
	}
}
