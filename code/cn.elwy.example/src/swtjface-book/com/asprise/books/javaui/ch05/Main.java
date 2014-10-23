/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 24, 2004 3:03:14 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

/*
 * Canvas example snippet: implement tab traversal (behave like a tab group)
 *
 * For a list of all SWT example snippets see
 * http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {
	public static void main(String[] args) {
		Display display = new Display();
		
		Image small = new Image(display, 16, 16);
		GC gc = new GC(small);
		gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
		gc.fillArc(0, 0, 16, 16, 45, 270);
		gc.dispose();
		
		Image large = new Image(display, 32, 32);
		gc = new GC(large);
		gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		gc.fillArc(0, 0, 32, 32, 45, 270);
		gc.dispose();
		
		/* Provide different resolutions for icons to get
		 * high quality rendering wherever the OS needs 
		 * large icons. For example, the ALT+TAB window 
		 * on certain systems uses a larger icon.
		 */
		Shell shell = new Shell(display);
		shell.setText("Small and Large icons");
		shell.setImages(new Image[] {small, large});

		/* No large icon: the OS will scale up the
		 * small icon when it needs a large one.
		 */
		Shell shell2 = new Shell(display);
		shell2.setText("Small icon");
		shell2.setImage(small);
		
		shell.open();
		shell2.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
