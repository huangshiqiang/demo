/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 26, 2004 11:02:38 PM by JACK
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
public class CLabelBorder {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	Image image = new Image(display, "eclipse32.gif");

	public CLabelBorder() {
		
		CLabel label1 = new CLabel(shell, SWT.SHADOW_OUT);
		label1.setText("SHADOW_OUT");
		label1.setImage(image);
		label1.setBounds(10, 10, 150, 100);
		
		CLabel label2 = new CLabel(shell, SWT.SHADOW_IN);
		label2.setText("SHADOW_IN");
		label2.setImage(image);
		label2.setBounds(170, 10, 150, 100);
		label2.setBackground(new Color[]{display.getSystemColor(SWT.COLOR_GREEN), 
										   display.getSystemColor(SWT.COLOR_RED),
										   display.getSystemColor(SWT.COLOR_BLUE), 
										   display.getSystemColor(SWT.COLOR_WHITE)},
							   new int[] {25, 50, 100});


		CLabel label3 = new CLabel(shell, SWT.SHADOW_NONE);
		label3.setText("SHADOW_NONE");
		label3.setBackground(image);		
		label3.setBounds(330, 10, 150, 100);
		
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
		new CLabelBorder();
	}
}
