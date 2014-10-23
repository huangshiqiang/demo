/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 22, 2004 1:29:05 AM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch08;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineBackgroundEvent;
import org.eclipse.swt.custom.LineBackgroundListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class HighlightOddLine {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	StyledText styledText;
	
	public HighlightOddLine() {
		shell.setLayout(new GridLayout());
		
		styledText = new StyledText(shell, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		styledText.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		styledText.addLineBackgroundListener(new LineBackgroundListener() {
			public void lineGetBackground(LineBackgroundEvent event) {
				if(styledText.getLineAtOffset(event.lineOffset) % 2 == 1)
					event.lineBackground = shell.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
			}
		});
		
		styledText.setText("Line 0\r\nLine 1\r\nLine 2\r\nLine 3\r\nLine 4\r\nLine 5\r\nLine 6");

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
		new HighlightOddLine();
	}
}
