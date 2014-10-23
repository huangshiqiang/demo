/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-3-28 20:25:15 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch13;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class SampleSlider {
	Display display = new Display();
	Shell shell = new Shell(display);

	Slider slider;
	Text value;
	
	public SampleSlider() {
		shell.setLayout(new GridLayout(1, true));
		
		Label label = new Label(shell, SWT.NULL);
		label.setText("Volume:");
		
		slider = new Slider(shell, SWT.VERTICAL);
		slider.setBounds(0, 0, 40, 200);
		slider.setMaximum(24);
		slider.setMinimum(0);
		slider.setIncrement(1);
		slider.setPageIncrement(5);
		
		slider.setThumb(4);
		
		slider.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int perspectiveValue = slider.getMaximum() - slider.getSelection() + slider.getMinimum() - slider.getThumb();
				value.setText("Vol: " + perspectiveValue);
			}
		});
		
		value = new Text(shell, SWT.BORDER | SWT.SINGLE);

		value.setEditable(false);
		slider.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		value.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

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
		new SampleSlider();
	}
}
