/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Feb 8, 2004 8:21:31 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch07;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class SampleList {
	Display display = new Display();
	Shell shell = new Shell(display);

	public SampleList() {
		init();
		
		RowLayout rowLayout = new RowLayout();
		shell.setLayout(rowLayout);
		
		(new Label(shell, SWT.NULL)).setText("What programming languages are you proficient in? ");
		
		final List list = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		
		String[] languages = new String[]{"Java", "C", "C++", "SmallTalk"};
		
		for(int i=0; i<languages.length; i++)
			list.add(languages[i]);
		
		list.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				System.err.println(list.getSelectionIndex());
				int[] indices = list.getSelectionIndices();
				String[] items = list.getSelection();
				StringBuffer sb = new StringBuffer("Selected indices: ");
				for(int i=0; i < indices.length; i++) {
					sb.append(indices[i]);
					sb.append("(");
					sb.append(items[i]);
					sb.append(")");
					if(i == indices.length-1)
						sb.append('.');
					else
						sb.append(", ");
				}
				System.out.println(sb.toString());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				int[] indices = list.getSelectionIndices();
				String[] items = list.getSelection();
				StringBuffer sb = new StringBuffer("Default selected indices: ");
				for(int i=0; i < indices.length; i++) {
					sb.append(indices[i]);
					sb.append("(");
					sb.append(items[i]);
					sb.append(")");
					if(i == indices.length-1)
						sb.append('.');
					else
						sb.append(", ");
				}
				System.out.println(sb.toString());
			}
		});
		
		list.selectAll();
		//list.select(1);

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
		new SampleList();
	}
}
