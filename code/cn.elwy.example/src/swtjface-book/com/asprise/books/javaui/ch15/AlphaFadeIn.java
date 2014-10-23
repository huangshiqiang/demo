/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-21 22:36:43 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
 * 
 */
public class AlphaFadeIn {
	Display display = new Display();
	Shell shell = new Shell(display);

	public AlphaFadeIn() {
		shell.setLayout(new FillLayout());
		final Canvas canvas = new Canvas(shell, SWT.NULL);
		
		ImageData imageData = new ImageData("icons/eclipse.jpg");
		
		ImageData imageData2 = imageData.scaledTo(100, 100);
		Image image2 = new Image(display, imageData2);
		
		byte[] alphaValues = new byte[imageData.height * imageData.width];
		for(int j=0; j<imageData.height; j++) {
			for(int i=0; i<imageData.width; i++) {
				alphaValues[j*imageData.width + i] = (byte) (255 - 255 * i / imageData.width);
			}
		}
		
		
		
		imageData.alphaData = alphaValues;
		
		final Image image = new Image(display, imageData);
		
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(image, 10, 10);
			}
		});

		shell.setSize(200, 100);
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
		new AlphaFadeIn();
	}
}
