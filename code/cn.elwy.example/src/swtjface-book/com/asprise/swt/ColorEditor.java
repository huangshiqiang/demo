/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 16, 2004 9:54:32 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.swt;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * A "button" of a certain color determined by the color picker.
 */
public class ColorEditor {
	
	private Point extent;
	private Image image;
	private RGB colorValue;
	private Color color;
	private Button button;
	
	public ColorEditor(Composite parent) {
		
		button= new Button(parent, SWT.PUSH);
		extent= computeImageSize(parent);
		image= new Image(parent.getDisplay(), extent.x, extent.y);
		
		GC gc= new GC(image);
		gc.setBackground(button.getBackground());
		gc.fillRectangle(0, 0, extent.x, extent.y);
		gc.dispose();
		
		button.setImage(image);
		
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ColorDialog colorDialog= new ColorDialog(button.getShell());
				colorDialog.setRGB(colorValue);
				RGB newColor = colorDialog.open();
				if (newColor != null) {
					colorValue = newColor;
					updateColorImage();
				}
			}
		});
		
		button.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				if (image != null)  {
					image.dispose();
					image= null;
				}
				if (color != null) {
					color.dispose();
					color = null;
				}
			}
		});
	}
	
	public RGB getColorValue() {
		return colorValue;
	}
	
	public void setColorValue(RGB rgb) {
		colorValue= rgb;
		updateColorImage();
	}
	
	public Button getButton() {
		return button;
	}
	
	protected void updateColorImage() {
		
		Display display= button.getDisplay();
		
		GC gc= new GC(image);
		gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
		gc.drawRectangle(0, 2, extent.x - 1, extent.y - 4);
		
		if (color != null)
			color.dispose();
			
		color= new Color(display, colorValue);
		gc.setBackground(color);
		gc.fillRectangle(1, 3, extent.x - 2, extent.y - 5);
		gc.dispose();
		
		button.setImage(image);
	}
	
	protected Point computeImageSize(Control window) {
		GC gc= new GC(window);
		Font f= JFaceResources.getFontRegistry().get(JFaceResources.DEFAULT_FONT);
		gc.setFont(f);
		int height= gc.getFontMetrics().getHeight();
		gc.dispose();
		Point p= new Point(height * 3 - 6, height);
		return p;
	}
}
