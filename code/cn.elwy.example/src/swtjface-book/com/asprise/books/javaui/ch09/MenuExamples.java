/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 24, 2004 2:23:13 PM by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch09;

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
public class MenuExamples {
	Display display = new Display();
	Shell shell = new Shell(display);

	public MenuExamples() {
		init();

		Menu menuBar = new Menu(shell, SWT.BAR);
		
		MenuItem itemHello = new MenuItem(menuBar, SWT.PUSH);
		itemHello.setText("&Hello");
		itemHello.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("HELLO");
			}
		});

		MenuItem itemCascade = new MenuItem(menuBar, SWT.CASCADE);
		itemCascade.setText("&CASCADE item");

		Menu menu = new Menu(itemCascade);

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

		final MenuItem itemCheck = new MenuItem(menu, SWT.CHECK);
		itemCheck.setText("CHEC&K item\tCtrl+K");
		itemCheck.setAccelerator(SWT.CTRL + 'K');
		itemCheck.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("item selected: CHECK item");
				System.out.println("Selection: " + itemCheck.getSelection());
			}
		});
		
		new MenuItem(menu, SWT.SEPARATOR);

		final MenuItem itemRadio = new MenuItem(menu, SWT.RADIO);
		itemRadio.setText("&RADIO item\tCtrl+R");
		itemRadio.setAccelerator(SWT.CTRL + 'R');
		itemRadio.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("item selected: RADIO item");
				System.out.println("Selection: " + itemRadio.getSelection());
			}
		});

		itemCascade.setMenu(menu);

		//shell.setMenu(menuBar);
		shell.setMenuBar(menuBar);
		menuBar.setDefaultItem(itemCascade);

		shell.setSize(300, 120);
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
		new MenuExamples();
	}
}
