/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 3, 2004 2:09:15 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch10;

import java.io.File;
import java.util.Date;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 
 */
public class FileBrowser {
	Display display = new Display();
	Shell shell = new Shell(display);

	Table table;
	
	File rootDir;
	
	Image iconFolder = new Image(shell.getDisplay(), "icons/folder.gif");
	Image iconFile = new Image(shell.getDisplay(), "icons/file.gif");
	
	public FileBrowser() {
		GridLayout gridLayout = new GridLayout();
		shell.setLayout(gridLayout);
		
		// Tool bar. 
		
		
		
		// Table. 
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);

		//table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tcFileName = new TableColumn(table, SWT.LEFT);
		tcFileName.setText("File name");
		//tcFileName.setImage(new Image(shell.getDisplay(), "icons/folder.gif"));
		
		TableColumn tcFileSize = new TableColumn(table, SWT.RIGHT);
		tcFileSize.setText("Size");
		
		TableColumn tcDateModified = new TableColumn(table, SWT.RIGHT);
		tcDateModified.setText("Date Modified");
		
		tcFileName.setWidth(200);
		tcFileSize.setWidth(80);
		tcDateModified.setWidth(180);
		
		TableItem item = new TableItem(table, SWT.NULL);
		item.setText(new String[]{"Name", "Size"});
		
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		table.pack();
		
		shell.pack();
		shell.open();
		
		setRootDirectory(new File("."));
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
	
	private void setRootDirectory(File dir) {
		this.rootDir = dir;
		if(! (dir.exists() && dir.isDirectory()))
			throw new IllegalArgumentException("Invalid directory. ");
		
		// Remove all existing items. 
		table.removeAll();
		
		File[] files = dir.listFiles();
		for(int i=0; files != null && i < files.length; i++) {
			File file = files[i];
			TableItem item = new TableItem(table, SWT.NULL);
			
			item.setBackground(i % 2 == 0 ? shell.getDisplay().getSystemColor(SWT.COLOR_WHITE) :
										  shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));
			
			item.setText(0, file.getName());
			item.setText(2, new Date(file.lastModified()).toString());
			
			
			if(file.isDirectory()) {
				item.setImage(0, iconFolder);
			}else{
				int lastDot = file.getName().lastIndexOf('.');
				if(lastDot >= 0 && lastDot < file.getName().length()) {
					item.setImage(0, getIcon(file.getName().substring(lastDot+1)));
				}
				long kbytes = file.length() / 1024;
				if(kbytes == 0)
					kbytes = 1;
				item.setText(1, kbytes + " KB");
			}
		}
		
	}
	
	private ImageRegistry imageRegistry;
	
	/**
	 * Returns the icon for the file type with the specified extension. 
	 * @param extension
	 * @return
	 */
	public Image getIcon(String extension) {
		if(imageRegistry == null)
			imageRegistry = new ImageRegistry();
		Image image = imageRegistry.get(extension);
		if(image != null)
			return image;
		
		Program program = Program.findProgram(extension);
		if(program != null) {
			image = new Image(shell.getDisplay(), program.getImageData());
			imageRegistry.put(extension, image);
		}else{
			image = iconFile;
		}
		
		return image;
	}

	public static void main(String[] args) {
		new FileBrowser();
	}
}
