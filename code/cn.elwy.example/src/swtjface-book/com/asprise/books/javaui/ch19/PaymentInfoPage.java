/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 20:08:05 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch19;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class PaymentInfoPage extends WizardPage {
	Combo comboCreditCardTypes;
	Text textCreditCardNumber;
	Text textCreditCardExpiration;
	
	public PaymentInfoPage() {
		super("PaymentInfo");
		setTitle("Payment information");
		setDescription("Please enter your credit card details");
		setPageComplete(false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		
		new Label(composite, SWT.NULL).setText("Credit card type: ");
		comboCreditCardTypes = new Combo(composite, SWT.READ_ONLY | SWT.BORDER);
		comboCreditCardTypes.add("American Express");
		comboCreditCardTypes.add("Master Card");
		comboCreditCardTypes.add("Visa");
		comboCreditCardTypes.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(composite, SWT.NULL).setText("Credit card number: ");
		textCreditCardNumber = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textCreditCardNumber.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(composite, SWT.NULL).setText("Expiration (MM/YY)");
		textCreditCardExpiration = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textCreditCardExpiration.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		comboCreditCardTypes.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				((ReservationWizard)getWizard()).data.creditCardType = comboCreditCardTypes.getSelectionIndex();
				if(((ReservationWizard)getWizard()).data.creditCardNumber != null && 
				((ReservationWizard)getWizard()).data.creditCardExpiration != null)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		});
		
		textCreditCardNumber.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				((ReservationWizard)getWizard()).data.creditCardNumber = textCreditCardNumber.getText();

				if(((ReservationWizard)getWizard()).data.creditCardNumber != null && 
				((ReservationWizard)getWizard()).data.creditCardExpiration != null)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		});
		
		textCreditCardExpiration.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				String text = textCreditCardExpiration.getText().trim();
				if(text.length() == 5 && text.charAt(2) == '/') {
					((ReservationWizard)getWizard()).data.creditCardExpiration = text;
					setErrorMessage(null);
				}else{
					((ReservationWizard)getWizard()).data.creditCardExpiration = null;
					setErrorMessage("Invalid expiration date: " + text);
				}
				

				if(((ReservationWizard)getWizard()).data.creditCardNumber != null && 
				((ReservationWizard)getWizard()).data.creditCardExpiration != null)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		});
		
		setControl(composite);
	}

}
