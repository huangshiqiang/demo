/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Feb 16, 2004 7:41:37 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

/**
 * 
 */
public class OptionPane {
	
	
	public static String showInputDialog(Shell parent, String message, String title, String initialValue) {
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		//shell.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		if(title != null)
			shell.setText(title);
		
		GridLayout gridLayout = new GridLayout(1, false);
		shell.setLayout(gridLayout);
		
		Composite content = new Composite(shell, SWT.NULL);
		content.setLayout(new RowLayout());
		//content.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_CYAN));
		
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		content.setLayoutData(gridData);
		
		(new Label(content, SWT.NULL)).setText(message);
		final Text text = new Text(content, SWT.SINGLE | SWT.BORDER);
		if(initialValue != null)
			text.setText(initialValue);
		
		Composite buttons =new Composite(shell, SWT.NULL);
		
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		buttons.setLayoutData(gridData);
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.spacing = 5;
		buttons.setLayout(fillLayout);
		
		final Button buttonOK = new Button(buttons, SWT.PUSH);
		final Button buttonCancel = new Button(buttons, SWT.PUSH);
		
		buttonOK.setText("OK");
		buttonCancel.setText("Cancel");
		
		
		final class DialogSelectionListener implements SelectionListener {
			public String returnText;
			
			public void widgetSelected(SelectionEvent e) {
				onSelected(e.widget);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				onSelected(e.widget);
			}
			
			private void onSelected(Widget widget) {
				if(widget == buttonOK)
					returnText = text.getText();
				shell.dispose();
			}
		}
		
		DialogSelectionListener listener = new DialogSelectionListener();
		
		buttonCancel.addSelectionListener(listener);
		buttonOK.addSelectionListener(listener);
		
		shell.pack();
		
		// Center the shell.
		if(parent != null) {
			Rectangle parentBounds = parent.getBounds();
			Point shellSize = shell.getSize();
			shell.setLocation( parentBounds.x+(parentBounds.width-shellSize.x)/2, parentBounds.y+(parentBounds.height-shellSize.y)/2 );
		}
		
		shell.open();
		
		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				// If no more entries in event queue
				shell.getDisplay().sleep();
			}
		}
		
		return listener.returnText;
	}
}
