/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Oct 25, 2003 2:02:04 PM by JACK
 * $Id: HelloWorld.java,v 1.1 2003/12/22 12:07:54 jackwind Exp $
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch04;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * HelloWorld is the simplest SWT application, which displays a Shell with a title. 
 */
public class HelloWorld {

	public static void main2(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hello, world!");
		shell.open();		
		
		while(! shell.isDisposed()) {
			if(! display.readAndDispatch()) {// If no more entries in event queue
				display.sleep();
			}
		}
		
		display.dispose();
	}
	
	public static void main(String[] args) {
		ApplicationWindow aw = new ApplicationWindow(null);
		aw.setBlockOnOpen(true);
		aw.open();
	}

}
