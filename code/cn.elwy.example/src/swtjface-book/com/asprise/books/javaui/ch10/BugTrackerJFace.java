/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 5, 2004 7:38:59 PM by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;

/**
 *  
 */
public class BugTrackerJFace {
	
	/**
	 * Represents a bug report. 
	 *
	 */
	public static class Bug {
		// For the sake of simplicity, all variables are public.
		public String id;
		public String summary;
		public String assignedTo;
		public boolean isSolved;
		
		public Bug(String id, String summary, String assignedTo, boolean isSolved) {
			this.id = id;
			this.summary = summary;
			this.assignedTo = assignedTo;
			this.isSolved = isSolved;
		}

		public static Vector loadBugs(File file) {
			Vector v = new Vector();
			// Load bugs from a file.
			DataInputStream in = null;

			try {
				if (!file.exists())
					return v;
				in = new DataInputStream(new FileInputStream(file));

				while (true) {
					String id = in.readUTF();
					String summary = in.readUTF();
					String assignedTo = in.readUTF();
					boolean solved = in.readBoolean();
					v.add(new Bug(id, summary, assignedTo, solved));
				}
				
			} catch (IOException ioe) {
				// Ignore.
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return v;
		}
	}

	

			
	
	Display display = new Display();
	Shell shell = new Shell(display);
	
	Table table;
	TableViewer tableViewer;
	
	Vector bugs;
	
	Image bugIcon = new Image(shell.getDisplay(), "icons/bug.gif");
	
	String[] colNames = new String[]{"ID", "Summary", "Assigned to", "Solved"};

	// Sorter. 
	class BugSorter extends ViewerSorter {
		private String property;
		private int propertyIndex;
		
		public BugSorter(String sortByProperty) {
			for(int i=0; i<colNames.length; i++) {
				if(colNames[i].equals(sortByProperty)) {
					this.property = sortByProperty;
					this.propertyIndex = i;
					return;
				}
			}
			
			throw new IllegalArgumentException("Unrecognized property: " + sortByProperty);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ViewerSorter#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public int compare(Viewer viewer, Object e1, Object e2) {
			Bug bug1 = (Bug)e1;
			Bug bug2 = (Bug)e2;
			
			switch(propertyIndex) {
				case 0:
					return bug1.id.compareTo(bug2.id);
				case 1:
					return bug1.summary.compareTo(bug2.summary);
				case 2:
					return bug1.assignedTo.compareTo(bug2.assignedTo);
				case 3:
					if(bug1.isSolved == bug2.isSolved)
						return 0;
					
					if(bug1.isSolved)
						return 1;
					else
						return -1;
				default:
					return 0;
			}
			
		}

	}	
	
	
	public BugTrackerJFace() {
		// Action.
		Action actionAddNew = new Action("New bug") {
			public void run() {
					// Append.
				Bug bug = new Bug("", "", "", false);
				bugs.add(bug);
				tableViewer.refresh(false);
			}
		};

		Action actionDelete = new Action("Delete selected") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection();
				Bug bug = (Bug)selection.getFirstElement();
				if (bug == null) {
					System.out.println("Please select an item first. ");
					return;
				}
				MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
				messageBox.setText("Confirmation");
				messageBox.setMessage(
					"Are you sure to remove the bug with id #"
						+ bug.id);
				if (messageBox.open() == SWT.YES) {
					bugs.remove(bug);
					tableViewer.refresh(false);
				}
			}
		};

		Action actionSave = new Action("Save") {
			public void run() {
				saveBugs(bugs);
			}
		};
		
		final ViewerFilter filter = new ViewerFilter() {
			public boolean select(
				Viewer viewer,
				Object parentElement,
				Object element) {
				if(! ((Bug)element).isSolved)
					return true;
				return false;
			}
		};
		
		Action actionShowUnsolvedOnly = new Action("Show unsolved only") {
			public void run() {
				if(! isChecked())
					tableViewer.removeFilter(filter);
				else
					tableViewer.addFilter(filter);
			}
		};
		actionShowUnsolvedOnly.setChecked(false);

		ToolBar toolBar = new ToolBar(shell, SWT.RIGHT | SWT.FLAT);

		ToolBarManager manager = new ToolBarManager(toolBar);
		manager.add(actionAddNew);
		manager.add(actionDelete);
		manager.add(new Separator());
		manager.add(actionSave);
		manager.add(new Separator());
		manager.add(actionShowUnsolvedOnly);
		
		manager.update(true);
		
		
		shell.setLayout(new GridLayout());
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);

		TableColumn tcID = new TableColumn(table, SWT.LEFT);
		tcID.setText(colNames[0]);

		TableColumn tcSummary = new TableColumn(table, SWT.NULL);
		tcSummary.setText(colNames[1]);

		TableColumn tcAssignedTo = new TableColumn(table, SWT.NULL);
		tcAssignedTo.setText(colNames[2]);

		TableColumn tcSolved = new TableColumn(table, SWT.NULL);
		tcSolved.setText(colNames[3]);

		tcID.setWidth(60);
		tcSummary.setWidth(200);
		tcAssignedTo.setWidth(80);
		tcSolved.setWidth(50);
		
		
		tableViewer = new TableViewer(table);
		
		tableViewer.getTable().setLinesVisible(true);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// Sets the content provider. 
		tableViewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				Vector v = (Vector)inputElement;
				return v.toArray();
			}

			public void dispose() {
				System.out.println("Disposing ...");
			}

			public void inputChanged(
				Viewer viewer,
				Object oldInput,
				Object newInput) {
					System.out.println("Input changed: old=" + 
						oldInput + ", new=" + newInput);
			}
		});
		
		// Sets the label provider. 
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				if(columnIndex == 0)
					return bugIcon;
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				Bug bug = (Bug)element;
				switch(columnIndex) {
					case 0:
						return bug.id;
					case 1:
						return bug.summary;
					case 2:
						return bug.assignedTo;
					case 3:
						return bug.isSolved ? "YES" : "NO";
				}
				return null;
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
		
		
		// Sets cell editors. 
		tableViewer.setColumnProperties(colNames);
		
		CellEditor[] cellEditors = new CellEditor[4];
		
		cellEditors[0] = new TextCellEditor(table);
		cellEditors[1] = cellEditors[0];
		cellEditors[2] = cellEditors[0];
		cellEditors[3] = new CheckboxCellEditor(table);
		
		tableViewer.setCellEditors(cellEditors);
		
		tableViewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				return true;
			}

			public Object getValue(Object element, String property) {
				// Get the index first. 
				int index = -1;
				for(int i=0; i<colNames.length; i++) {
					if(colNames[i].equals(property)) {
						index = i;
						break;
					}
				}
				Bug bug = (Bug)element;
				
				switch(index) {
					case 0:
						return bug.id;
					case 1:
						return bug.summary;
					case 2:
						return bug.assignedTo;
					case 3:
						return new Boolean(bug.isSolved);
				}
				
				return null;
			}

			public void modify(Object element, String property, Object value) {
				System.out.println("Modify: " + element + ", " + property + ", " + value);
				// Get the index first. 
				int index = -1;
				for(int i=0; i<colNames.length; i++) {
					if(colNames[i].equals(property)) {
						index = i;
						break;
					}
				}
				
				Bug bug = null;
				if(element instanceof Item) {
					TableItem item = (TableItem)element;
					bug = (Bug)item.getData();
				}else{
					bug = (Bug)element;
				}
				
				switch(index) {
					case 0:
						bug.id = (String)value;
						break;
					case 1:
						bug.summary = (String)value;
						break;
					case 2:
						bug.assignedTo = (String)value;
						break;
					case 3:
						bug.isSolved = ((Boolean)value).booleanValue();
						break;
				}
				
				tableViewer.update(bug, null);
			}
		});
		
		// Setting sorters. 
		tcID.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				tableViewer.setSorter(new BugSorter(colNames[0]));
			}
		});
		
		tcSummary.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				tableViewer.setSorter(new BugSorter(colNames[1]));
			}
		});
		
		tcAssignedTo.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				tableViewer.setSorter(new BugSorter(colNames[2]));
			}
		});		
		
		tcSolved.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				tableViewer.setSorter(new BugSorter(colNames[3]));
			}
		});
		
		bugs = Bug.loadBugs(new File("bugs.dat"));
		
		tableViewer.setInput(bugs);

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

	
	
	private void saveBugs(Vector v) {
		// Save bugs to a file.
		DataOutputStream out = null;
		try {
			File file = new File("bugs.dat");

			out = new DataOutputStream(new FileOutputStream(file));

			for (int i = 0; i < v.size(); i++) {
				Bug bug = (Bug)v.elementAt(i);
				out.writeUTF(bug.id);
				out.writeUTF(bug.summary);
				out.writeUTF(bug.assignedTo);
				out.writeBoolean(bug.isSolved);
			}
		} catch (IOException ioe) {
			// Ignore.
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new BugTrackerJFace();
	}
}
