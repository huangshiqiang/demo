/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-6-13 13:32:48 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch21;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class SimpleForm extends ApplicationWindow {

	/**
	 * @param parentShell
	 */
	public SimpleForm(Shell parentShell) {
		super(parentShell);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		
		// Sets up the toolkit.
		FormToolkit toolkit = new FormToolkit(getShell().getDisplay());
		
		// create a form instance.
		Form form = toolkit.createForm(composite);
		form.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		form.setText("Eclipse Forms");
		
		form.getBody().setLayout(new GridLayout());
		Button button = toolkit.createButton(form.getBody(), "Test", SWT.NULL);	
		
		// tool bar
		form.getToolBarManager().add(new Action("TEST") {
			public void run() {
			}
		});
		
		Menu menu = new Menu(form.getBody());
		MenuItem item = new MenuItem(menu, SWT.NULL);
		item.setText("Testing item");
		form.setMenu(menu);
		
		form.updateToolBar();
		
		return composite;
	}

	public static void main(String[] args) {
		SimpleForm simpleForm = new SimpleForm(null);
		simpleForm.setBlockOnOpen(true);
		simpleForm.open();
	}	
	
}
