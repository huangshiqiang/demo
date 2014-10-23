/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-14 22:41:43 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch24;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.net.ftp.FTPFile;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * 
 */
public class RemoteDirectoryBrowser extends TableViewer {
	/**
	 * @param table
	 */
	public RemoteDirectoryBrowser(Table table) {
		super(table);
		init();
	}
	
	private void init() {
		// the content provider.
		setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				FTPFile[] files = (FTPFile[])inputElement;
				return files;
			}

			public void dispose() {
			}

			public void inputChanged(
				Viewer viewer,
				Object oldInput,
				Object newInput) {
			}
		});
		
		// the label provider.
		setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				if(columnIndex == 0)
					return FileIconUtil.getIcon((FTPFile)element);
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				switch(columnIndex) {
					case 0:
						return ((FTPFile)element).getName();
					case 1:
						return ((FTPFile)element).getSize() + "";
					case 2:
						Calendar cal = ((FTPFile)element).getTimestamp();
						return cal.get(Calendar.YEAR) + "-" + 
						cal.get(Calendar.MONTH) + "-" +
						cal.get(Calendar.DAY_OF_MONTH) + " " +
						cal.get(Calendar.HOUR_OF_DAY) + ":" +
						cal.get(Calendar.MINUTE) + ":" +
						cal.get(Calendar.SECOND);
					default:
						return "";		
				}
			}

			public void addListener(ILabelProviderListener listener) {
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
			}
		});
	}
	
}
