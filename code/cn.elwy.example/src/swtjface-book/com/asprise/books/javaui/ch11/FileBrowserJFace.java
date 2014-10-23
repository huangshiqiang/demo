/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 12, 2004 11:51:42 PM by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch11;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

/**
 * 
 */
public class FileBrowserJFace {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	File rootDir;
	
	TreeViewer treeViewer;

	public FileBrowserJFace() {

		Action actionSetRootDir = new Action("Set Root Dir") {
			public void run() {
				DirectoryDialog dialog = new DirectoryDialog(shell);
				String path = dialog.open();
				if (path != null) {
					treeViewer.setInput(new File(path));
				}
			}
		};
		
		final ViewerFilter directoryFilter = new ViewerFilter() {
			public boolean select(
				Viewer viewer,
				Object parentElement,
				Object element) {
				return ((File)element).isDirectory();
			}
		};
		
		Action actionShowDirectoriesOnly = new Action("Show directories only") {
			public void run() {
				if(! isChecked())
					treeViewer.removeFilter(directoryFilter);
				else
					treeViewer.addFilter(directoryFilter);
			}
		};
		actionShowDirectoriesOnly.setChecked(false);

		Action actionDeleteFile = new Action("Delete the selected file") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection();
				File file = (File)selection.getFirstElement();
				if(file == null) {
					System.out.println("Please select a file first.");
					return;
				}
				
				MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
				messageBox.setMessage("Are you sure to delete file: " + file.getName() + "?");
				if(messageBox.open() == SWT.YES) {
					File parentFile = file.getParentFile();
					if(file.delete()) {
						System.out.println("File has been deleted. ");
						// Notifies the viewer for update. 
						treeViewer.refresh(parentFile, false);
					}else{
						System.out.println("Unable to delete file.");
					}
				}
					
			}
		};

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
		ToolBarManager manager = new ToolBarManager(toolBar);
		manager.add(actionSetRootDir);
		manager.add(actionShowDirectoriesOnly);
		manager.add(actionDeleteFile);

		manager.update(true);
		
	
		shell.setLayout(new GridLayout());

		treeViewer = new TreeViewer(shell, SWT.BORDER);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));

		// Sets the content provider.
		treeViewer.setContentProvider(new ITreeContentProvider() {
			public Object[] getChildren(Object parentElement) {
				File[] files = ((File)parentElement).listFiles();
				if(files == null)
					return new Object[0];
				return files;
			}

			public Object getParent(Object element) {
				return ((File)element).getParentFile();
			}

			public boolean hasChildren(Object element) {
				File file = (File)element;
				File[] files = file.listFiles();
				if(files == null || files.length == 0)
					return false;
				return true;
			}

			public Object[] getElements(Object inputElement) {
				File[] files = ((File)inputElement).listFiles();
				if(files == null)
					return new Object[0];
				return files;
			}

			public void dispose() {
			}

			public void inputChanged(
				Viewer viewer,
				Object oldInput,
				Object newInput) {
				shell.setText("Now browsing: " + newInput);
			}
		});
		
		// Sets the label provider. 
		treeViewer.setLabelProvider(new LabelProvider() {
			public Image getImage(Object element) {
				return getIcon((File)element);
			}

			public String getText(Object element) {
				return ((File)element).getName();
			}
		});
		
		// Sorts the tree. 
		treeViewer.setSorter(new ViewerSorter() {
			public int category(Object element) {
				File file = (File)element;
				if(file.isDirectory())
					return 0;
				else
					return 1;
			}
		});
		
		
		treeViewer.setInput(new File("F:/jdk1.5"));
		
		shell.setSize(400, 260);
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

	/**
	 * Renames a file.
	 * 
	 * @param file
	 * @param newName
	 * @return the after after being renamed or <code>null</code> if renaming
	 *         fails.
	 */
	private File renameFile(File file, String newName) {
		File dest = new File(file.getParentFile(), newName);
		if (file.renameTo(dest)) {
			return dest;
		} else {
			return null;
		}
	}


	private ImageRegistry imageRegistry;
	Image iconFolder = new Image(shell.getDisplay(), "icons/folder.gif");
	Image iconFile = new Image(shell.getDisplay(), "icons/file.gif");

	/**
	 * Returns an icon representing the specified file.
	 * 
	 * @param file
	 * @return
	 */
	private Image getIcon(File file) {
		if (file.isDirectory())
			return iconFolder;

		int lastDotPos = file.getName().indexOf('.');
		if (lastDotPos == -1)
			return iconFile;

		Image image = getIcon(file.getName().substring(lastDotPos + 1));
		return image == null ? iconFile : image;
	}

	/**
	 * Returns the icon for the file type with the specified extension.
	 * 
	 * @param extension
	 * @return
	 */
	private Image getIcon(String extension) {
		if (imageRegistry == null)
			imageRegistry = new ImageRegistry();
		Image image = imageRegistry.get(extension);
		if (image != null)
			return image;

		Program program = Program.findProgram(extension);
		ImageData imageData = (program == null ? null : program.getImageData());
		if (imageData != null) {
			image = new Image(shell.getDisplay(), imageData);
			imageRegistry.put(extension, image);
		} else {
			image = iconFile;
		}

		return image;
	}
	
	
	
	public static void main(String[] args) {
		new FileBrowserJFace();
	}
}
