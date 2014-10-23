/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-22 20:53:55 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch14;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

/**
 * 
 */
public class TestToolBar {
	Display display = new Display();
	Shell shell = new Shell(display);

	public TestToolBar() {
		MenuManager menuManager = new MenuManager();
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		final ToolBarManager manager = new ToolBarManager(toolBar);

		// Adds tool bar items using actions.
		final Action actionForward =
			new Action(
				"&Forward",
				ImageDescriptor.createFromFile(
					null,
					"icons/web/forward.gif")) {
			public void run() {
				System.out.println("FORWARD");
			}
		};
		actionForward.setAccelerator(SWT.CTRL + 'F');


		Action actionHome =
			new Action(
				"&Home",
				ImageDescriptor.createFromFile(null, "icons/web/home.gif")) {
			public void run() {
				System.out.println("HOME");
			}
		};
		actionHome.setAccelerator(SWT.CTRL + 'H');


		manager.add(actionForward);
		
		ActionContributionItem item = new ActionContributionItem(actionHome);
		item.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		manager.add(item);

		manager.update(true);
		toolBar.pack();
		
		MenuManager fileMenuManager = new MenuManager("&File");
		fileMenuManager.add(actionForward);
		fileMenuManager.add(actionHome);
		menuManager.add(fileMenuManager);
		menuManager.updateAll(true);
		shell.setMenuBar(menuManager.createMenuBar((Decorations)shell));


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
		new TestToolBar();
	}
}
