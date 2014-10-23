/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-19 13:48:04 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class DrawText {
	Display display = new Display();
	Shell shell = new Shell(display);

	public DrawText() {
		shell.setLayout(new FillLayout());
		
		final Canvas canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		
		final Image image = new Image(display, "icons/eclipse.gif");
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle size = image.getBounds();
				// Draws the background image.
				e.gc.drawImage(image, 0, 0, size.width, size.height, 0, 0, canvas.getSize().x, canvas.getSize().y);
				
				Font font = new Font(display, "Tahoma", 18, SWT.BOLD);
				e.gc.setFont(font);
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));

				String english = "SWT rocks!";
				String chinese = "\u4e2d\u6587\u6c49\u5b57\u6d4b\u8bd5";
				
				e.gc.drawString(english, 10, 10);
				e.gc.drawString(chinese, 10, 80, true);
				
				String text = "Text to be drawn in the center";
				Point textSize = e.gc.textExtent(text);
				e.gc.drawText(text, (canvas.getSize().x - textSize.x)/2, (canvas.getSize().y - textSize.y)/2);
				
				font.dispose();
			}
		});
		

		shell.setSize(300, 150);
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
		new DrawText();
	}
}
