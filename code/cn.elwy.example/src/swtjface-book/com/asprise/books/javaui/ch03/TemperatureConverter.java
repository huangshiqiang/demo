/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 16, 2004 1:23:25 AM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch03;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 *  
 */
public class TemperatureConverter {
	Display display = new Display();
	Shell shell = new Shell(display);

	Label fahrenheitLabel;
	Label celsiusLabel;
	Label messageLabel;

	Text fahrenheitValue;
	Text celsiusValue;

	public TemperatureConverter() {
		shell.setText("SWT Temperature Converter");
		shell.setLayout(new GridLayout(4, false));

		fahrenheitLabel = new Label(shell, SWT.NULL);
		fahrenheitLabel.setText("Fahrenheit: ");

		fahrenheitValue = new Text(shell, SWT.SINGLE | SWT.BORDER);

		celsiusLabel = new Label(shell, SWT.NULL);
		celsiusLabel.setText("Celsius: ");

		celsiusValue = new Text(shell, SWT.SINGLE | SWT.BORDER);
		
		messageLabel = new Label(shell, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 4;
		messageLabel.setLayoutData(gridData);

		ModifyListener listener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				valueChanged((Text) e.widget);
			}
		};

		fahrenheitValue.addModifyListener(listener);
		celsiusValue.addModifyListener(listener);

		shell.pack();
		shell.open();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();
	}

	/**
	 * Performs conversion when one of the text fields changes.
	 * 
	 * @param text
	 *            the event source
	 */
	public void valueChanged(Text text) {
		if (!text.isFocusControl())
			return;

		if (text == fahrenheitValue) {
			try {
				double fValue = Double.parseDouble(text.getText());
				double cValue = (fValue - 32) / 1.8;
				celsiusValue.setText(Double.toString(cValue));
				System.out.println("F -> C: " + cValue);
				messageLabel.setText("Conversion performed successfully.");
			} catch (NumberFormatException e) {
				celsiusValue.setText("");
				messageLabel.setText("Invalid number format: " + text.getText());
			}
		} else {
			try {
				double cValue = Double.parseDouble(text.getText());
				double fValue = cValue * 1.8 + 32;
				fahrenheitValue.setText(Double.toString(fValue));
				System.out.println("C -> F: " + fValue);
				messageLabel.setText("Conversion performed successfully.");
			} catch (NumberFormatException e) {
				fahrenheitValue.setText("");
				messageLabel.setText("Invalid number format: " + text.getText());
			}
		}
	}

	public static void main(String[] args) {
		new TemperatureConverter();
	}
}
