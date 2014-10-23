/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 16, 2004 7:20:52 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch12;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class FormattedText {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	// the label used to display the text.
	Label label;
	
	Button buttonColor;
	Button buttonFont;
	
	// current selected color.
	Color color;
	// current selected font.
	Font font;

	public FormattedText() {
		label = new Label(shell, SWT.BORDER | SWT.WRAP);
		label.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		label.setText("Java UI Programming with SWT/JFace");
		
		buttonColor = new Button(shell, SWT.PUSH);
		buttonColor.setText("Change color");
		buttonColor.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				ColorDialog colorDialog = new ColorDialog(shell);
				if(color != null)
					colorDialog.setRGB(color.getRGB());
				RGB value = colorDialog.open();
				if(value != null) {
					if(color != null)
						color.dispose();
					color = new Color(display, value);
					label.setForeground(color);
				}else{
					System.out.println("Setting foreground color action canceled.");
				}
			}
		});
		
		buttonFont = new Button(shell, SWT.PUSH);
		buttonFont.setText("Change font");
		buttonFont.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FontDialog fontDialog = new FontDialog(shell);
				if(font != null)
					fontDialog.setFontList(font.getFontData());
				FontData fontData = fontDialog.open();
				if(fontData != null) {
					if(font != null)
						font.dispose();
					font = new Font(display, fontData);
					label.setFont(font);
				}else{
					System.out.println("Setting font action canceled.");
				}
			}
		});
		
		label.setBounds(0, 0, 300, 120);
		buttonColor.setBounds(50, 130, 90, 25);
		buttonFont.setBounds(160, 130, 90, 25);

		shell.setSize(300, 190);
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
		new FormattedText();
	}
}
