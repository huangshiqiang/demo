/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-21 21:49:01 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class Transparency {
	Display display = new Display();
	Shell shell = new Shell(display);

	public Transparency() {
		shell.setLayout(new FillLayout());
		
		ImageData imageData = new ImageData("icons/eclipse.jpg");
		
		final Image image = new Image(display, imageData);

		RGB white = new RGB(255, 255, 255);
		for(int i=0; i<imageData.width; i++) {
			for(int j=0; j<imageData.height; j++) {
				RGB rgb = imageData.palette.getRGB(imageData.getPixel(i, j));
				int threshold = 220;

				if(rgb.red > threshold && rgb.green > threshold && rgb.blue > threshold)
					imageData.setPixel(i, j, imageData.palette.getPixel(white));
			}
		}
		
		imageData.transparentPixel = imageData.palette.getPixel(new RGB(255, 255, 255));
		
		final Image image2 = new Image(display, imageData);
		
		final Canvas canvas = new Canvas(shell, SWT.NULL);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(image, 10, 20);
				e.gc.drawImage(image2, 200, 20);
			}
		});

		shell.setSize(400, 140);
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
		new Transparency();
	}
}
