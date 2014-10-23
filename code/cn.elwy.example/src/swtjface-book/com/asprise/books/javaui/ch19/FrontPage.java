/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 15:09:10 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch19;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * 
 */
public class FrontPage extends WizardPage {
	Combo comboRoomTypes;
	
	Combo comboArrivalYear;
	Combo comboArrivalMonth;
	Combo comboArrivalDay;
	Combo comboDepartureYear;
	Combo comboDepartureMonth;
	Combo comboDepartureDay;
	
	FrontPage() {
		super("FrontPage");
		setTitle("Your reservation information");
		setDescription("Select the type of room and your arrival date & departure date");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		
		new Label(composite, SWT.NULL).setText("Arrival date: ");
		
		Composite compositeArrival = new Composite(composite, SWT.NULL);
		compositeArrival.setLayout(new RowLayout());
		
		
		String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
		};
		
		Calendar calendar = new GregorianCalendar(); // today.
		((ReservationWizard)getWizard()).data.arrivalDate = calendar.getTime();
		
		comboArrivalMonth = new Combo(compositeArrival, SWT.BORDER | SWT.READ_ONLY);
		for(int i=0; i<months.length; i++)
			comboArrivalMonth.add(months[i]);
		comboArrivalMonth.select(calendar.get(Calendar.MONTH));
		
		comboArrivalDay = new Combo(compositeArrival, SWT.BORDER | SWT.READ_ONLY);
		for(int i=0; i<31; i++)
			comboArrivalDay.add("" + (i+1));
		comboArrivalDay.select(calendar.get(Calendar.DAY_OF_MONTH)-1);
		
		comboArrivalYear = new Combo(compositeArrival, SWT.BORDER | SWT.READ_ONLY);
		for(int i=2004; i<2010; i++)
			comboArrivalYear.add("" + i);
		comboArrivalYear.select(calendar.get(Calendar.YEAR)-2004);
		
		calendar.add(Calendar.DATE, 1); // tomorrow. 
		((ReservationWizard)getWizard()).data.departureDate = calendar.getTime();

		
		new Label(composite, SWT.NULL).setText("Departure date: ");
		
		Composite compositeDeparture = new Composite(composite, SWT.NULL | SWT.READ_ONLY);
		compositeDeparture.setLayout(new RowLayout());
		
		comboDepartureMonth = new Combo(compositeDeparture, SWT.NULL | SWT.READ_ONLY);
		for(int i=0; i<months.length; i++) 
			comboDepartureMonth.add(months[i]);
		comboDepartureMonth.select(calendar.get(Calendar.MONTH));
		
		comboDepartureDay = new Combo(compositeDeparture, SWT.NULL | SWT.READ_ONLY);
		for(int i=0; i<31; i++) 
			comboDepartureDay.add("" + (i+1));
		comboDepartureDay.select(calendar.get(Calendar.DAY_OF_MONTH)-1);
		
		comboDepartureYear = new Combo(compositeDeparture, SWT.NULL | SWT.READ_ONLY);
		for(int i=2004; i<2010; i++)
			comboDepartureYear.add("" + i);
		comboDepartureYear.select(calendar.get(Calendar.YEAR)-2004);
		
		// draws a line. 
		Label line = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		 
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		line.setLayoutData(gridData);
		
		new Label(composite, SWT.NULL).setText("Room type: ");
		comboRoomTypes = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		comboRoomTypes.add("Standard room (rate: $198)");
		comboRoomTypes.add("Deluxe room (rate: $298)");
		comboRoomTypes.select(0);
		
		Listener selectionListener = new Listener() {
			public void handleEvent(Event event) {
				int arrivalDay = comboArrivalDay.getSelectionIndex() + 1;
				int arrivalMonth = comboArrivalMonth.getSelectionIndex();
				int arrivalYear = comboArrivalYear.getSelectionIndex() + 2004;
				
				int departureDay = comboDepartureDay.getSelectionIndex() + 1;
				int departureMonth = comboDepartureMonth.getSelectionIndex();
				int departureYear = comboDepartureYear.getSelectionIndex() + 2004;
				
				setDates(arrivalDay, arrivalMonth, arrivalYear, 
						departureDay, departureMonth, departureYear);
			}
		};
		
		comboArrivalDay.addListener(SWT.Selection, selectionListener);
		comboArrivalMonth.addListener(SWT.Selection, selectionListener);
		comboArrivalYear.addListener(SWT.Selection, selectionListener);
		comboDepartureDay.addListener(SWT.Selection, selectionListener);
		comboDepartureMonth.addListener(SWT.Selection, selectionListener);
		comboDepartureYear.addListener(SWT.Selection, selectionListener);
		
		comboRoomTypes.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				((ReservationWizard)getWizard()).data.roomType = comboRoomTypes.getSelectionIndex();
			}
		});
		
		setControl(composite);
		
	}
	
	// validates the dates and update the model data object. 
	private void setDates(int arrivalDay, int arrivalMonth, int arrivalYear, int departureDay, int departureMonth, int departureYear) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, arrivalDay);
		calendar.set(Calendar.MONTH, arrivalMonth);
		calendar.set(Calendar.YEAR, arrivalYear);
		
		Date arrivalDate = calendar.getTime();
		
		calendar.set(Calendar.DAY_OF_MONTH, departureDay);
		calendar.set(Calendar.MONTH, departureMonth);
		calendar.set(Calendar.YEAR, departureYear);
		
		Date departureDate = calendar.getTime();
		
		System.out.println(arrivalDate + " - " + departureDate);
		
		if(! arrivalDate.before(departureDate)) { // arrival date is before dep. date.
			setErrorMessage("The arrival date is not before the departure date");
			setPageComplete(false);
		}else{
			setErrorMessage(null); // clear error message. 
			setPageComplete(true);
			((ReservationWizard)getWizard()).data.arrivalDate = arrivalDate;
			((ReservationWizard)getWizard()).data.departureDate = departureDate;
		}
	}

}
