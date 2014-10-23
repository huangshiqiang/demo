/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 5, 2004 2:50:44 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch02;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class SharedMenu {
	Display display = new Display();
	Shell shell = new Shell(display);

	public SharedMenu() {

		menu();

		shell.pack();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();
	}
	
	private void menu() {
		shell.setLayout(new GridLayout(2, true));
		
		final Composite composite1 = new Composite(shell, SWT.BORDER);
		composite1.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		
		final Composite composite2 = new Composite(shell, SWT.BORDER);
		composite2.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		
		Menu menu = new Menu(composite1);
		MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
		menuItem.setText("Popup menu");
		
		menuItem.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				System.out.println("Menu item is disposed.");
			}
		});

		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Dispsoing ...");
				//composite2.setMenu(null);
				composite2.dispose();
			}
		});
		
		composite1.setMenu(menu);
		composite2.setMenu(menu);
	}

/*	
	private void complex() {
		shell.setLayout(new GridLayout(2, true));
		
		Label label = new Label(shell, SWT.NULL);
		label.setText("Label");
		
		Composite composite = new Composite(shell, SWT.NULL);
		composite.setLayout(new GridLayout());
		
		Button button1 = new Button(composite, SWT.PUSH);
		button1.setText("Button 1");
		
		Button button2 = new Button(composite, SWT.PUSH);
		button2.setText("Button 2");
		
	}
	
	private void addButton() {
		shell.setLayout(new GridLayout());
		
		Label label = new Label(shell, SWT.NULL);
		
		final Color color = new Color(display, 255, 0, 0);
		
		label.setForeground(color);
		label.setForeground(display.getSystemColor(SWT.COLOR_RED));
		
		label.setText("Testing label");
		
		label.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				color.dispose();
			}
		});
	}

	private void draw() {
		GC gc = new GC(shell);
		gc.setLineWidth(3);
		
		//Color color = new Color(display, 255, 0, 0);
		
		//gc.setForeground(color);
		gc.drawRectangle(10, 10, 200, 100);
		
		gc.dispose();
	}
*/

	public static void main(String[] args) {
		new SharedMenu();
	}
	
}
