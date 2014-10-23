/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 19, 2004 8:49:16 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch08;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class SampleStyledText {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	StyledText styledText;

	public SampleStyledText() {
		init();
		
		shell.setLayout(new GridLayout());
		
		styledText = new StyledText(shell, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		styledText.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Font font = new Font(shell.getDisplay(), "Courier New", 12, SWT.NORMAL);
		styledText.setFont(font);
		
		styledText.setText("123456789\r\nABCDEFGHI");
		
		StyleRange styleRange1 = new StyleRange();
		styleRange1.start = 2;
		styleRange1.length = 16;
		styleRange1.foreground = shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
		styleRange1.background = shell.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
		styleRange1.fontStyle = SWT.BOLD;
		
		StyleRange styleRange2 = new StyleRange();
		styleRange2.start = 14;
		styleRange2.length = 3;
		styleRange2.fontStyle = SWT.NORMAL;
		styleRange2.foreground = shell.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
		styleRange2.background = shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
		
//		styledText.setStyleRange(styleRange1);
//		styledText.setStyleRange(styleRange2);
		
		//styledText.setStyleRanges(new StyleRange[]{styleRange1, styleRange2});
		//styledText.setStyleRanges(new StyleRange[]{styleRange2, styleRange1});
		
		//styledText.setLineBackground(1, 1, shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		
//		styledText.setSelection(4);
//		System.out.println(printStyleRanges(styledText.getStyleRanges()) );
//		styledText.insert("000");
		
		
		System.out.println(printStyleRanges(styledText.getStyleRanges()) );
		
//		styledText.setStyleRanges(new StyleRange[]{styleRange1});
//		System.out.println(printStyleRanges(styledText.getStyleRanges()) );
		
		//shell.pack();
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
	
	private String printStyleRanges(StyleRange[] styleRanges) {
		
		if(styleRanges == null)
			return "null";
		else if(styleRanges.length == 0)
			return "[]";
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<styleRanges.length; i++) {
			sb.append(styleRanges[i] + "\n");
		}
		
		return sb.toString();
	}

	private void init() {

	}

	public static void main(String[] args) {
		new SampleStyledText();
	}
}
