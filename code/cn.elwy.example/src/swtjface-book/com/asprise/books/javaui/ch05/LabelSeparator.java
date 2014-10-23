/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 26, 2004 9:29:09 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class LabelSeparator {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	Image image = new Image(display, "eclipse32.gif");

	public LabelSeparator() {
		init();
		// shell.setLayout(new RowLayout());
		
		Label label = new Label(shell, SWT.BORDER);
		// Label label = new Label(shell, SWT.SEPARATOR);
		label.setImage(image);
		label.setText("Label");
		label.setBounds(10, 10, 150, 150);
		
		CLabel clabel = new CLabel(shell, SWT.SHADOW_IN);
		clabel.setImage(image);
		clabel.setText("CLabel");
		clabel.setBounds(170, 10, 150, 150);
		
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
		new LabelSeparator();
	}
}
