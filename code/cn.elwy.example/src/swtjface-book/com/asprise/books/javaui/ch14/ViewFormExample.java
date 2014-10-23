/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-9 15:43:00 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch14;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class ViewFormExample {
	Display display = new Display();
	Shell shell = new Shell(display);

	public ViewFormExample() {
		shell.setLayout(new FillLayout());
		
		final ViewForm viewForm = new ViewForm(shell, SWT.BORDER);
		Label label = new Label(viewForm, SWT.NULL);
		label.setText("Top center");
		
		viewForm.setTopCenter(label);

		shell.setSize(400, 200);
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
		new ViewFormExample();
	}
}
