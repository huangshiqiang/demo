/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 19:18:40 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch19;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 *  
 */
public class CustomerInfoPage extends WizardPage {
	Text textName;
	Text textPhone;
	Text textEmail;
	Text textAddress;

	public CustomerInfoPage() {
		super("CustomerInfo");
		setTitle("Customer Information");

		setPageComplete(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));

		new Label(composite, SWT.NULL).setText("Full name: ");
		textName = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Phone Number: ");
		textPhone = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textPhone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Email address: ");
		textEmail = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textEmail.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Address: ");
		textAddress = new Text(composite, SWT.MULTI | SWT.BORDER);
		textAddress.setText("\r\n\r\n\r\n");
		textAddress.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				if (event.widget == null || !(event.widget instanceof Text))
					return;

				String string = ((Text) event.widget).getText();

				if (event.widget == textName) {
					((ReservationWizard) getWizard()).data.customerName =
						string;

				} else if (event.widget == textPhone) {
					((ReservationWizard) getWizard()).data.customerPhone =
						string;

				} else if (event.widget == textEmail) {
					if (string.indexOf('@') < 0) {
						setErrorMessage("Invalid email address: " + string);
						((ReservationWizard) getWizard()).data.customerEmail =
							null;
					} else {
						setErrorMessage(null);
						((ReservationWizard) getWizard()).data.customerEmail =
							string;
					}
				} else if (event.widget == textAddress) {
					((ReservationWizard) getWizard()).data.customerAddress =
						string;
				}

				ReservationData data = ((ReservationWizard) getWizard()).data;
				if (data.customerName != null
					&& data.customerPhone != null
					&& data.customerEmail != null
					&& data.customerAddress != null) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		};

		textName.addListener(SWT.Modify, listener);
		textPhone.addListener(SWT.Modify, listener);
		textEmail.addListener(SWT.Modify, listener);
		textAddress.addListener(SWT.Modify, listener);

		if (getDialogSettings() != null && validDialogSettings()) {

				textName.setText(
					getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_NAME));

				textPhone.setText(
					getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_PHONE));

				textEmail.setText(
					getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_EMAIL));

				textAddress.setText(
					getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_ADDRESS));

		}

		setControl(composite);
	}
	
	private boolean validDialogSettings() {
		if (getDialogSettings().get(ReservationWizard.KEY_CUSTOMER_NAME)
			== null
			|| getDialogSettings().get(ReservationWizard.KEY_CUSTOMER_ADDRESS)
				== null
			|| getDialogSettings().get(ReservationWizard.KEY_CUSTOMER_EMAIL)
				== null
			|| getDialogSettings().get(ReservationWizard.KEY_CUSTOMER_PHONE)
				== null)
			return false;
		return true;
	}

}
