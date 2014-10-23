/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-13 10:10:07 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class FileViewer extends ApplicationWindow {

	
	// The text control used to display the file content.
	Text text; 
	
	String content;
	String lineDelimiter;
	
	IRunnableWithProgress runnableWithProgress = new IRunnableWithProgress() {
		public void run(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
			System.out.println("Running from thread: " + Thread.currentThread().getName());
			
			getShell().getDisplay().syncExec(new Runnable() {
				public void run() {
					content = text.getText();
					lineDelimiter = text.getLineDelimiter();
				}
			});

			monitor.beginTask("Counting total number of lines", content.length());
			int lines = 1;
			for(int i=0; i<content.length(); i++) {
				if(monitor.isCanceled()) { // checks whether the operation has been cancelled.
					monitor.done();
					System.out.println("Action cancelled");
					return;
				}
				
				// Checks the existance of the line delimiter.
				if(i + lineDelimiter.length() < content.length()) {
					if(lineDelimiter.equals(content.substring(i, i+lineDelimiter.length()))) {
						lines ++;
					}
				}
				
				monitor.worked(1);
				Thread.sleep(1); // 1ms
			}
			
			monitor.done();
			System.out.println("Total number of lines: " + lines);
		}
	};
	
	Action actionCount = new Action("Count", ImageDescriptor.createFromFile(null, "icons/run.gif")) {
		public void run() {
			try {
				FileViewer.this.run(true, true, runnableWithProgress);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	/**
	 * @param parentShell
	 */
	public FileViewer(Shell parentShell) {
		super(parentShell);
		addMenuBar();
		addStatusLine();
		addToolBar(SWT.FLAT);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		getShell().setText("FileViewer v2.0");
		setStatus("Ready");
		
		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		text.setSize(300, 200);
		return text;
	}
	
	Action actionOpenFile = new Action("Open", ImageDescriptor.createFromFile(null, "icons/open.gif")) {
		public void run() {
			FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
			final String file = dialog.open();
			if(file != null) {
				try {
					String content = readFileAsAString(new File(file));
					text.setText(content);
					setStatus("File loaded successfully: " + file);
				} catch (IOException e) {
					e.printStackTrace();
					setStatus("Failed to load file: " + file);
				}
			}
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
	 */
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("");
		
		MenuManager fileMenuManager = new MenuManager("&File");
		fileMenuManager.add(actionOpenFile);
		
		menuManager.add(fileMenuManager);
		
		MenuManager toolsMenuManager = new MenuManager("&Tools");
		toolsMenuManager.add(actionCount);
		menuManager.add(toolsMenuManager);
		
		return menuManager;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.ApplicationWindow#createStatusLineManager()
	 */
	protected StatusLineManager createStatusLineManager() {
		return super.createStatusLineManager();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
	 */
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		toolBarManager.add(actionOpenFile);
		toolBarManager.add(actionCount);

		return toolBarManager;
	}

	public static void main(String[] args) {
		ApplicationWindow viewer = new FileViewer(null);
		viewer.setBlockOnOpen(true);
		viewer.open();
	}
	
	/**
	 * Reads the content of a file into a String. 
	 * @param file file to be read
	 * @return
	 * @throws IOException
	 */
	public static String readFileAsAString(File file) throws IOException {
		return new String(getBytesFromFile(file));
	}
	
	/**
	 * Returns the contents of the file in a byte array. 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
			throw new IllegalArgumentException("File is too large! (larger or equal to 2G)");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
			&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException(
				"Could not completely read file " + file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
}
