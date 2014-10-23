/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-18 15:03:44 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class GCCreation {
	Display display = new Display();
	Shell shell = new Shell(display);

	public GCCreation() {
		Image image = new Image(display, "icons/eclipse.gif");
		
		// Clones the image. 
		Image image2 = new Image(display, image.getImageData());
		// Draws an oval
		GC gc = new GC(image2);
		gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		gc.drawOval(10, 10, 90, 40);
		gc.dispose();
		
		CLabel label = new CLabel(shell, SWT.NULL);
		label.setImage(image);
		label.setBounds(10, 10, 130, 130);
		
		CLabel label2 = new CLabel(shell, SWT.NULL);
		label2.setImage(image2);
		label2.setBounds(150, 10, 130, 130);

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


	public static void main(String[] args) {
		new GCCreation();
	}
}
