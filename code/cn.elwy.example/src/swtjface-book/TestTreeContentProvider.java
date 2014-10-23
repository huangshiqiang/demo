import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 13, 2004 3:54:35 PM by JACK $Id$
 *  
 ******************************************************************************/

/**
 *  
 */
public class TestTreeContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		System.out.println("Get children.");
		File[] files = ((File) parentElement).listFiles();
		if (files == null)
			return new Object[0];
		return files;
	}

	public Object getParent(Object element) {
		return ((File) element).getParentFile();
	}

	public boolean hasChildren(Object element) {
		System.out.println("Has children.");
		File file = (File) element;
		File[] files = file.listFiles();
		if (files == null || files.length == 0)
			return false;
		return true;
	}

	public Object[] getElements(Object inputElement) {
		System.out.println("Get elements.");
		return new Object[] { inputElement };
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//shell.setText("Browsing: " + newInput);
	}
}
