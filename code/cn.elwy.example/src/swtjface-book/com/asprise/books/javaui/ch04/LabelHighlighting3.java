/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Oct 28, 2003 8:10:30 PM by JACK
 * $Id: LabelHighlighting3.java,v 1.1 2003/12/22 12:07:54 jackwind Exp $
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch04;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class LabelHighlighting3 {
	Display display = new Display();
	Shell shell = new Shell(display);
	Label label = new Label(shell, SWT.SHADOW_IN | SWT.CENTER);
	MouseTrackListener listener = new MouseEnterExitListener();
	
	public LabelHighlighting3() {
		
		label.setText("Point your cursor here ...");
		label.setBounds(30, 30, 200, 30);
		
		label.addMouseTrackListener(listener);
		
		shell.setText("Move your cursor to test ...");
		shell.setSize(260, 120);
		shell.open();		
		
			
		while(! shell.isDisposed()) {
			if(! display.readAndDispatch()) {// If no more entries in event queue
				display.sleep();
			}
		}
		
		display.dispose();
	}
	
	class MouseEnterExitListener extends MouseTrackAdapter {
		public void mouseEnter(MouseEvent e) {
			display.syncExec(new Runnable() {
				public void run() {
					label.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
					label.setText("Cursor enters the label");
				}
			});

		}

		public void mouseExit(MouseEvent arg0) {
			display.syncExec(new Runnable() {
				public void run() {
					label.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
					label.setText("Cursor leaves the label");
				}
			});
		}
	}


	public static void main(String[] args) {
		new LabelHighlighting3();
	}
}
