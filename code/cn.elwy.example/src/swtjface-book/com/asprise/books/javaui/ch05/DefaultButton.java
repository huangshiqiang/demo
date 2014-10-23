/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 25, 2004 11:46:01 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class DefaultButton {

	public DefaultButton() {
		Display display = new Display();
		Shell shell = new Shell(display);

		shell.setLayout(new RowLayout());

		final String[] ratings =
			new String[] { "Killer!", "Good stuff", "So-so", "Needs work" };
		final Button[] radios = new Button[ratings.length];
		for (int i = 0; i < ratings.length; i++) {
			radios[i] = new Button(shell, SWT.RADIO);
			radios[i].setText(ratings[i]);
		}

		Button cancelButton = new Button(shell, SWT.PUSH);
		cancelButton.setText("Canel");

		Button rateButton = new Button(shell, SWT.PUSH);
		rateButton.setText("Rate!");
		rateButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				for (int i = 0; i < radios.length; i++)
					if (radios[i].getSelection())
						System.out.println("Rating: " + ratings[i]);

			}

			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("Default selection");
			}
		});

		shell.setDefaultButton(rateButton);
		System.out.println(shell.getDefaultButton());

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
		new DefaultButton();
	}
}
