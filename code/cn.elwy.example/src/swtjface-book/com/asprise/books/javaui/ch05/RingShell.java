/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 25, 2004 7:58:06 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class RingShell {
	
	int[] createCircle(int radius, int centerX, int centerY) {
		int[] points = new int[360 * 2];
		for(int i=0; i<360; i++) {
			points[i*2] = centerX + (int)(radius * Math.cos(Math.toRadians(i)));
			points[i*2+1] = centerY + (int)(radius * Math.sin(Math.toRadians(i)));
		}
		return points;
	}
	
	Point originalPosition = null;

	public RingShell() {
		final Display display = new Display();
		final Shell shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
		shell.setBackground(display.getSystemColor(SWT.COLOR_DARK_MAGENTA));
		
		Region region = new Region();
		region.add(createCircle(100, 100, 100));
		region.subtract(createCircle(50, 100, 100));
		shell.setRegion(region);
		
		// Make the shell movable by using the mouse pointer. 
		shell.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose(); // Double click to dispose the shell.
			}

			public void mouseDown(MouseEvent e) {
				originalPosition = new Point(e.x, e.y);
			}

			public void mouseUp(MouseEvent e) {
				originalPosition = null;
			}
		});
		
		shell.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				if(originalPosition == null)
					return;
				Point point = display.map(shell, null, e.x, e.y);
				shell.setLocation(point.x - originalPosition.x, point.y - originalPosition.y);
				System.out.println("Moved from: " + originalPosition + " to " + point);
			}
		});
		
		Rectangle regionBounds = region.getBounds();
		shell.setSize(regionBounds.width, regionBounds.height);
		shell.open();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}
		display.dispose();
		region.dispose();
	}
	
	public static void main(String[] args) {
		new RingShell();
	}
}
