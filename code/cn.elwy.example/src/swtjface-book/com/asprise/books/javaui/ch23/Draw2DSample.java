/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-3 17:22:26 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch23;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class Draw2DSample extends ApplicationWindow {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.ApplicationWindow#addToolBar(int)
	 */
	protected void addToolBar(int style) {
		super.addToolBar(style);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new FillLayout());
		
		Canvas canvas = new Canvas(composite, SWT.NULL);
		
		LightweightSystem lws = new LightweightSystem(canvas);
		Button button = new Button("Button", new Image(getShell().getDisplay(), "icons/eclipse0.gif"));
		lws.setContents(button);
		
		return composite;
	}

	/**
	 * @param parentShell
	 */
	public Draw2DSample(Shell parentShell) {
		super(parentShell);
	}

	public static void main(String[] args) {
		Draw2DSample window = new Draw2DSample(null);
		window.setBlockOnOpen(true);
		window.open();
	}
}
