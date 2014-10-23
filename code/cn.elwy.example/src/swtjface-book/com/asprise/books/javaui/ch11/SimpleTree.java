/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 10, 2004 8:08:56 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 */
public class SimpleTree {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	Tree tree;

	public SimpleTree() {
		shell.setLayout(new GridLayout());
		
		tree = new Tree(shell, SWT.BORDER);
		
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		TreeItem item = new TreeItem(tree, SWT.NULL);
		item.setText("ITEM");
		
		TreeItem item2 = new TreeItem(item, SWT.NULL);
		item2.setText("ITEM2");
		
		TreeItem item3 = new TreeItem(item2, SWT.NULL);
		item3.setText("ITEM3");
		
		System.out.println("item: " + item.getParent() + ", " + item.getParentItem());
		System.out.println("item2: " + item2.getParent() + ", " + item2.getParentItem());
		
		System.out.println(tree.getItemCount());
		System.out.println(tree.getItems().length);
		
		shell.setSize(300, 200);
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
		new SimpleTree();
	}
}
