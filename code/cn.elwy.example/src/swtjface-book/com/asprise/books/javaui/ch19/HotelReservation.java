/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 14:41:48 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch19;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class HotelReservation extends ApplicationWindow {
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Make a reservation");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				ReservationWizard wizard = new ReservationWizard();
				
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.setBlockOnOpen(true);
				int returnCode = dialog.open();
				if(returnCode == Dialog.OK)
					System.out.println(wizard.data);
				else
					System.out.println("Cancelled");
			}
		});
		return button;
	}

	/**
	 * @param parentShell
	 */
	public HotelReservation(Shell parentShell) {
		super(parentShell);
	}

	public static void main(String[] args) {
		HotelReservation reservation = new HotelReservation(null);
		reservation.setBlockOnOpen(true);
		reservation.open();
	}
}
