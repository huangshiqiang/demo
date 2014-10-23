/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-2 21:35:30 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch17;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */
public class SimplePrint {
	Display display = new Display();
	Shell shell = new Shell(display);

	public SimplePrint() {
		shell.pack();
		shell.open();

		PrintDialog dialog = new PrintDialog(shell);
		// Opens a dialog and let use user select the 
		// target printer and configure various settings.
		PrinterData printerData = dialog.open();
		if(printerData != null) { // If a printer is selected
			// Creates a printer.
			Printer printer = new Printer(printerData);
			
			// Starts the print job.
			if(printer.startJob("Text")) {
				GC gc = new GC(printer);

				// Starts a new page.
				if(printer.startPage()) {
					gc.drawString("Eclipse", 200, 200);
					
					// Finishes the page. 
					printer.endPage();
				}
				
				gc.dispose();
				
				// Ends the job.
				printer.endJob();
			}
			
			// Disposes the printer object after use. 
			printer.dispose();
			
			System.out.println("Print job done.");
		}
		
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
		new SimplePrint();
	}
}
