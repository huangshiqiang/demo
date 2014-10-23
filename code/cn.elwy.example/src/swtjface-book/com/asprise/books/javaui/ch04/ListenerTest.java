/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Oct 27, 2003 10:38:29 PM by JACK
 * $Id: ListenerTest.java,v 1.1 2003/12/22 12:07:54 jackwind Exp $
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch04;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class ListenerTest {
	public ListenerTest() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Left click your mouse");
		shell.setSize(200, 100);
		shell.open();		
		
		shell.addListener(SWT.MouseDown, new SimpleListener("Shell mouse down listener"));
		
		display.addFilter(SWT.MouseDown, new SimpleListener("Display mouse down Listener"));
		display.addFilter(SWT.MouseUp, new SimpleListener("Display mouse up Listener"));
		
		while(! shell.isDisposed()) {
			if(! display.readAndDispatch()) {// If no more entries in event queue
				display.sleep();
			}
		}
		
		display.dispose();
	}
	
	class SimpleListener implements Listener{
		String name;
		
		public SimpleListener(String name) {
			this.name = name;
		}

		public void handleEvent(Event e) {
			System.out.println("Event: [" + EventUtil.getEventName(e.type) + "] from " + name + ". \tCurrent Time (in ms):  " + System.currentTimeMillis());
		}
	}

	public static void main(String[] args) {
		new ListenerTest();
	}
}
