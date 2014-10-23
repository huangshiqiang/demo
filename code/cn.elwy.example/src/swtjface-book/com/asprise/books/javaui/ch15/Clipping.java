/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-19 15:03:55 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class Clipping {
	Display display = new Display();
	Shell shell = new Shell(display);

	public Clipping() {
		shell.setLayout(new FillLayout());
		
		final Canvas canvas = new Canvas(shell, SWT.NULL);
		final Image image = new Image(display, "icons/eclipse.gif");
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Region region = new Region();
				region.add(new int[]{60, 10, 10, 100, 110, 100});
				e.gc.setClipping(region);
				
				e.gc.drawImage(image, 0, 0);
			}
		});

		shell.setSize(200, 140);
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
		new Clipping();
	}
}
