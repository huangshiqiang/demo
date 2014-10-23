/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-7 13:28:17 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch14;

/*
 * Sash example snippet: create a sash (allow it to be moved)
 *
 * For a list of all SWT example snippets see
 * http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Shell;

public class SashExample {

public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	final Sash sash = new Sash (shell, SWT.BORDER | SWT.VERTICAL);
	sash.setBounds (10, 10, 15, 60);

	sash.addListener (SWT.Selection, new Listener () {
		public void handleEvent (Event e) {
			System.out.println("Selected. ");
			sash.setBounds (e.x, e.y, e.width, e.height);
		}
	});
	shell.open ();
	sash.setFocus ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
}
} 

