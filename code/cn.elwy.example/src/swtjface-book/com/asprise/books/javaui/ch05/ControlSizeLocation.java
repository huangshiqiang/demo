/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Jan 23, 2004 11:26:26 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch05;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 *  
 */
public class ControlSizeLocation {
	Display display = new Display();
	Shell shell = new Shell(display);

	Button button;

	Text x;
	Text y;
	Text h;
	Text w;

	Button get;
	Button set;

	public ControlSizeLocation() {
		init();

		shell.pack();
		shell.open();
		//textUser.forceFocus();

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
		GridLayout gridLayout = new GridLayout(2, true);
		shell.setLayout(gridLayout);

		Composite left = new Composite(shell, SWT.NULL);
		left.setLayout(new GridLayout());
		//left.setLayout(new FillLayout());
		left.setLayoutData(new GridData(GridData.FILL_BOTH));
		left.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		button = new Button(left, SWT.PUSH);
		button.setText("Button");
		button.setLayoutData(new GridData());

		Composite right = new Composite(shell, SWT.NULL);
		right.setLayout(new GridLayout(4, true));
		right.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(right, SWT.NULL);
		label.setText("X");

		label = new Label(right, SWT.NULL);
		label.setText("Y");

		label = new Label(right, SWT.NULL);
		label.setText("Width");

		label = new Label(right, SWT.NULL);
		label.setText("Height");

		x = new Text(right, SWT.BORDER);
		y = new Text(right, SWT.BORDER);
		w = new Text(right, SWT.BORDER);
		h = new Text(right, SWT.BORDER);

		SelectionListener selectionListener = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Button b = (Button) e.widget;
				if (b == get) {
					System.out.println("------------------------------");
					System.out.println("getBounds: " + button.getBounds());
					System.out.println("getLocation: " + button.getLocation());
					System.out.println("getSize: " + button.getSize());
					
				}else if(b == set) {
					int vx = getNumber(x);
					int vy = getNumber(y);
					int vw = getNumber(w);
					int vh = getNumber(h);
					
					if(vx != -1 && vy != -1) {
						if(vw != -1 && vh != -1) {
							Rectangle rectangle = new Rectangle(vx, vy, vw, vh);
							button.setBounds(rectangle);
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("# setBounds: " + rectangle);
						}else{
							Point point = new Point(vx, vy);
							button.setLocation(point);
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("# setLocation: " + point);
						}
					}else if(vw != -1 && vh != -1) {
						Point point = new Point(vw, vh);
						button.setSize(point);
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("# setSize: " + point);
					}
				}

			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		};

		get = new Button(right, SWT.PUSH);
		get.setText("Get");
		get.addSelectionListener(selectionListener);

		set = new Button(right, SWT.PUSH);
		set.setText("Set");
		set.addSelectionListener(selectionListener);

	}
	
	/**
	 * 
	 * @param text
	 * @return <code>-1</code> if invalid.
	 */
	private int getNumber(Text text) {
		if(text == null)
			return -1;
		String value = text.getText();
		if(value == null || value.trim().length() == 0)
			return -1;
		try {
			return Integer.parseInt(value.trim());
		}catch(Exception e) {
			
		}
		return -1;
	}

	public static void main(String[] args) {
		new ControlSizeLocation();
	}
}
