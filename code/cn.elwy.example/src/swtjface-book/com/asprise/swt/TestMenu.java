/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-27 13:26:52 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class TestMenu {

	/**
	 * 
	 */
	public Display display = new Display();
	Shell shell = new Shell(display);

	public TestMenu() {
		init();

		shell.pack();
		shell.open();
		//textUser.forceFocus();
		

		Menu menuBar = new Menu(shell, SWT.BAR);
		
		MenuItem itemHello = new MenuItem(menuBar, SWT.CASCADE);
		itemHello.setText("&Hello");
		itemHello.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("HELLO");
			}
		});
		
		Menu menu = new Menu(itemHello);

		MenuItem itemPush = new MenuItem(menu, SWT.PUSH);
		itemPush.setText("&PUSH item\tCtrl+P");
		itemPush.setAccelerator(SWT.CTRL + 'P');
		Image icon = new Image(shell.getDisplay(), "icons/new.gif");
		itemPush.setImage(icon);
		itemPush.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("item selected: PUSH item");
			}
		});

		shell.setMenuBar(menuBar);		
		
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
		new TestMenu();
	}

}
