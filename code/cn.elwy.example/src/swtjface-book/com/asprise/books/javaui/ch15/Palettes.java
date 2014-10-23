/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-21 20:12:55 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class Palettes {
	Display display = new Display();
	Shell shell = new Shell(display);

	public Palettes() {
		shell.setLayout(new FillLayout());

		PaletteData paletteData = new PaletteData(0xFF0000, 0x00FF00, 0x0000FF);
		ImageData imageData = new ImageData(100, 100, 24, paletteData);

		for (int i = 0; i < 100; i++) { // each column.
			for (int j = 0; j < 100; j++) { // each row.
				if (j < 30 || (i > 35 && i < 65))
					imageData.setPixel(i, j, 0xFF);
				else
					imageData.setPixel(i, j, 0xFFFF00);
			}
		}

		//		int pixelValue = imageData.getPixel(50, 50);
		//		int redComponent = pixelValue & imageData.palette.redMask;
		//		int greenComponent = pixelValue & imageData.palette.greenMask;
		//		int blueComponent = pixelValue & imageData.palette.blueMask;

		PaletteData paletteData2 =
			new PaletteData(new RGB[] { new RGB(0, 0, 255), // blue
			new RGB(255, 255, 0) // yellow
		});
		
		ImageData imageData2 = new ImageData(100, 100, 1, paletteData2);
		for (int i = 0; i < 100; i++) { // each column.
			for (int j = 0; j < 100; j++) { // each row.
				if (j < 30 || (i > 35 && i < 65))
					imageData2.setPixel(i, j, 0);
				else
					imageData2.setPixel(i, j, 1);
			}
		}
		
		System.out.println(imageData.palette.getRGB(imageData.getPixel(50, 50)));
		
		final Image image2 = new Image(display, imageData2);

		final Image image = new Image(display, imageData);

		final Canvas canvas = new Canvas(shell, SWT.NULL);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(image2, 0, 0);
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
		new Palettes();
	}
}
