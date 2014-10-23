/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 17, 2004 9:58:26 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch08;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class RemarksText {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	Text text;

	public RemarksText() {
		shell.setLayout(new GridLayout(1, false));
		
		(new Label(shell, SWT.NULL)).setText("Remarks:");
		
		text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		text.setText("123456789");
		
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		System.out.println("getText: " + text.getText(1, 6));
		
		char[] chars = text.getLineDelimiter().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			System.out.println("Line delimiter #" + i + ": " + Integer.toHexString(chars[i]) );
		}
		
		text.getOrientation();
		
		System.out.println("Number of chars: " + text.getCharCount());
		System.out.println("Tabs: " + text.getTabs());
		
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				if(e.end == e.start) {
					if( e.character == ' ' && (e.stateMask & SWT.CTRL) != 0 ) {
						if(text.getText(e.end-1, e.end-1).equals("V")) {
							e.text = "erifyListener";
						}else{
							e.doit = false;
						}
					}
				}
			}
		});
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				System.out.println("New character count: " + text.getCharCount());	
			}
		});
		
		// text.append("a");
		
		text.setSelection(1, 4);
		
		System.out.println("getSelection:\t" + text.getSelection());
		System.out.println("getSelectionCount:\t" + text.getSelectionCount());
		System.out.println("getSelectionText:\t" + text.getSelectionText());
		//text.insert("ABC");
		
		init();

		// shell.pack();
		shell.setSize(300, 150);
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
		new RemarksText();
	}
}
