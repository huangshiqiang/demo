/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-18 18:33:36 by JACK
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
public class Drawings {
	Display display = new Display();
	Shell shell = new Shell(display);

	public Drawings() {
		shell.setLayout(new FillLayout());
		
		Canvas canvas = new Canvas(shell, SWT.BORDER);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				
//				e.gc.drawString("SWT.LINE_SOLID (default)", 10, 10);
//				e.gc.drawString("SWT.LINE_DASH", 10, 30);
//				e.gc.drawString("SWT.LINE_DOT", 10, 50);
//				e.gc.drawString("SWT.LINE_DASHDOT", 10, 70);
//				e.gc.drawString("SWT.LINE_DASHDOTDOT", 10, 90);
//				e.gc.drawString("Line width = 1 (default)", 10, 120);
//				e.gc.drawString("Line width = 3", 10, 140);
//				
//				int start = 150;
//				e.gc.setLineWidth(1);
//				e.gc.setLineStyle(SWT.LINE_SOLID);
//				e.gc.drawLine(start, 15, start + 200, 15);
//				e.gc.setLineStyle(SWT.LINE_DASH);
//				e.gc.drawLine(start, 35, start + 200, 35);
//				e.gc.setLineStyle(SWT.LINE_DOT);
//				e.gc.drawLine(start, 55, start + 200, 55);
//				e.gc.setLineStyle(SWT.LINE_DASHDOT);
//				e.gc.drawLine(start, 75, start + 200, 75);
//				e.gc.setLineStyle(SWT.LINE_DASHDOTDOT);
//				e.gc.drawLine(start, 95, start + 200, 95);
//				
//				e.gc.setLineStyle(SWT.LINE_SOLID);
//				e.gc.drawLine(start, 125, start + 200, 125);
//				e.gc.setLineWidth(3);
//				e.gc.drawLine(start, 145, start + 200, 145);
				
				
//				int[] points = new int[3 * 2];
//				points[0] = 10; // The point at the top. 
//				points[1] = 10;
//				
//				points[2] = 10; // The point at the left bottom.
//				points[3] = 100;
//				
//				points[4] = 100; // the point at the right bottom
//				points[5] = 100;
//				
//				//e.gc.drawPolyline(points);
				
				e.gc.setLineStyle(SWT.LINE_SOLID);
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
				e.gc.fillArc(10, 10, 200, 100, 0, -90);
				e.gc.setLineStyle(SWT.LINE_DOT);
				e.gc.drawLine(0, 60, 220, 60);
				e.gc.drawLine(110, 0, 110, 120);
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
		new Drawings();
	}
}
