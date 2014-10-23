/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 26, 2004 11:31:38 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class CLabelGradientBG {
	Display display = new Display();
	Shell shell = new Shell(display);
	Image image = new Image(display, "eclipse32.gif");

	public CLabelGradientBG() {
		init();

		CLabel labelGradientBg = new CLabel(shell, SWT.SHADOW_IN);
		labelGradientBg.setText("CLabel with gradient colored background");
		labelGradientBg.setImage(image);
		labelGradientBg.setBounds(10, 10, 300, 100);
		labelGradientBg.setBackground(
			new Color[] {
				display.getSystemColor(SWT.COLOR_GREEN),
				display.getSystemColor(SWT.COLOR_WHITE),
				display.getSystemColor(SWT.COLOR_RED)},
			new int[] { 50, 100 });

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
		new CLabelGradientBG();
	}
}
