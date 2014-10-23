/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-11 0:10:48 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch14;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class BugReport {
	Display display = new Display();
	Shell shell = new Shell(display);

	public BugReport() {
		shell.setLayout(new GridLayout(1, true));
		shell.setImage(new Image(display, "icons/bug.gif"));
		shell.setText("Bug report page");
		
		Group groupBug = new Group(shell, SWT.NULL);
		groupBug.setText("Bug details");
		groupBug.setLayout(new GridLayout(2, false));
		groupBug.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Label(groupBug, SWT.NULL).setText("Priority");
		Combo combo = new Combo(groupBug, SWT.BORDER);
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(groupBug, SWT.NULL).setText("Details");
		Text text = new Text(groupBug, SWT.BORDER | SWT.MULTI);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group groupProxy = new Group(shell, SWT.NULL);
		groupProxy.setText("Connection setting");
		groupProxy.setLayout(new GridLayout(2, false));
		groupProxy.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(groupProxy, SWT.NULL).setText("Proxy host");
		Text textHost = new Text(groupProxy, SWT.SINGLE | SWT.BORDER);
		textHost.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(groupProxy, SWT.NULL).setText("Proxy port");
		Text textPort = new Text(groupProxy, SWT.SINGLE | SWT.BORDER);
		textPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button button = new Button(shell, SWT.PUSH);
		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		//button.setAlignment(SWT.CENTER);
		button.setText("Submit bug report");

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
		new BugReport();
	}
}
