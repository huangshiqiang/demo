/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-14 7:42:05 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch24;

import java.io.File;
import java.util.Date;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;

/**
 * 
 */
public class LocalDirectoryBrowser extends TableViewer {

	/**
	 * @param table
	 */
	public LocalDirectoryBrowser(Table table) {
		super(table);
		init();
	}
	
	private void init() {
		// the content provider.
		setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				File dir = (File)inputElement;
				return dir.listFiles();
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
					return FileIconUtil.getIcon((File)element);
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				switch(columnIndex) {
					case 0:
						return ((File)element).getName();
					case 1:
						return ((File)element).length() + "";
					case 2:
						return new Date(((File)element).lastModified()).toString();
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
