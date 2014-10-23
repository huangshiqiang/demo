/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-6-13 18:19:20 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch21;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * 
 */
public class EmailForm  extends ApplicationWindow {

	/**
	 * @param parentShell
	 */
	public EmailForm(Shell parentShell) {
		super(parentShell);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new FillLayout());
		
		// Sets up the toolkit.
		FormToolkit toolkit = new FormToolkit(getShell().getDisplay());
		
		// Creates a form instance.
		// Form form = toolkit.createForm(composite);
		ScrolledForm form = toolkit.createScrolledForm(composite);
		form.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// Sets title.
		form.setText("Composing an Email Message");
		
		// Adds body contents.
		form.getBody().setLayout(new GridLayout(2, false));
		Label label = toolkit.createLabel(form.getBody(), "To: ", SWT.NULL);
		Text textTo = toolkit.createText(form.getBody(), "");
		textTo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		label = toolkit.createLabel(form.getBody(), "Subject: ", SWT.NULL);
		Text textSubject = toolkit.createText(form.getBody(), "");
		textSubject.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		label = toolkit.createLabel(form.getBody(), "Message: ", SWT.NULL);
		Text textMessage = toolkit.createText(form.getBody(), "");
		textMessage.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		label = toolkit.createLabel(form.getBody(), "Option: ", SWT.NULL);
		Button buttonOption = toolkit.createButton(form.getBody(), "save a copy", SWT.CHECK);
	
		
		Button buttonClose = toolkit.createButton(form.getBody(), "Close", SWT.PUSH);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = GridData.END;
		buttonClose.setLayoutData(gridData);
		
		
		// Button button = toolkit.createButton(form.getBody(), "Test", SWT.NULL);	
		
		// Adds tool bar items.
		form.getToolBarManager().add(new Action("Send") {
			public void run() {
				System.out.println("Sending email ...");
			}
		});
		
		form.getToolBarManager().add(new Action("Cancel") {
			public void run() {
				System.out.println("Cancelled.");
			}
		});
		
		form.updateToolBar();

		
		return composite;
	}

	public static void main(String[] args) {
		EmailForm emailForm = new EmailForm(null);
		emailForm.setBlockOnOpen(true);
		emailForm.open();
	}		
	
}
