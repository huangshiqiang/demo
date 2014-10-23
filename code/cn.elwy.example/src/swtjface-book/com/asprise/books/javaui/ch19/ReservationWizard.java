/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 16:38:39 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch19;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;


// The data model.
class ReservationData {
	Date arrivalDate;
	Date departureDate;
	int roomType;

	String customerName;
	String customerPhone;
	String customerEmail;
	String customerAddress;

	int creditCardType;
	String creditCardNumber;
	String creditCardExpiration;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("* HOTEL ROOM RESERVATION DETAILS *\n");
		sb.append("Arrival date:\t" + arrivalDate.toString() + "\n");
		sb.append("Departure date:\t" + departureDate.toString() + "\n");
		sb.append("Room type:\t" + roomType + "\n");
		sb.append("Customer name:\t" + customerName + "\n");
		sb.append("Customer email:\t" + customerEmail + "\n");
		sb.append("Credit card no.:\t" + creditCardNumber + "\n");
		
		return sb.toString();
	}
}

/**
 * 
 */
public class ReservationWizard extends Wizard {
	
	static final String DIALOG_SETTING_FILE = "userInfo.xml";
	
	static final String KEY_CUSTOMER_NAME = "customer-name";
	static final String KEY_CUSTOMER_EMAIL = "customer-email";
	static final String KEY_CUSTOMER_PHONE = "customer-phone";
	static final String KEY_CUSTOMER_ADDRESS = "customer-address";
	
	
	
	// the model object. 
	ReservationData data = new ReservationData();
	
	public ReservationWizard() {
		setWindowTitle("Hotel room reservation wizard");
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(null, "icons/hotel.gif"));

		DialogSettings dialogSettings = new DialogSettings("userInfo");
		try {
			// loads existing settings if any. 
			dialogSettings.load(DIALOG_SETTING_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		setDialogSettings(dialogSettings);
	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		addPage(new FrontPage());
		addPage(new CustomerInfoPage());
		addPage(new PaymentInfoPage());
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		if(getDialogSettings() != null) {
			getDialogSettings().put(KEY_CUSTOMER_NAME, data.customerName);
			getDialogSettings().put(KEY_CUSTOMER_PHONE, data.customerPhone);
			getDialogSettings().put(KEY_CUSTOMER_EMAIL, data.customerEmail);
			getDialogSettings().put(KEY_CUSTOMER_ADDRESS, data.customerAddress);
			try {
				// Saves the dialog settings into the specified file. 
				getDialogSettings().save(DIALOG_SETTING_FILE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			// puts the data into a database ...
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Store data", 100);
					monitor.worked(40);
					
					// store data here ...
					System.out.println(data);
					
					Thread.sleep(2000);
					monitor.done();
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#performCancel()
	 */
	public boolean performCancel() {
		boolean ans = MessageDialog.openConfirm(getShell(), "Confirmation", "Are you sure to cancel the task?");
		if(ans)
			return true;
		else
			return false;
	}	
}
