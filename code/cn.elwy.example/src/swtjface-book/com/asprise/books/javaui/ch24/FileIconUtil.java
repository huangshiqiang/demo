/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-14 7:50:09 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch24;

import java.io.File;

import org.apache.commons.net.ftp.FTPFile;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

/**
 * File icon provider. A display object must be created before 
 * you can access this method.
 */
public class FileIconUtil {
	static ImageRegistry imageRegistry;
	
	static Image iconFolder;
	static Image iconFile;
	
	static {
		iconFolder = new Image(Display.getCurrent(), "icons/folder.gif");
		iconFile = new Image(Display.getCurrent(), "icons/file.gif");
	}

	/**
	 * Returns an icon representing the specified file.
	 * 
	 * @param file
	 * @return
	 */
	public static Image getIcon(File file) {
		if (file.isDirectory())
			return iconFolder;

		int lastDotPos = file.getName().indexOf('.');
		if (lastDotPos == -1)
			return iconFile;

		Image image = getIcon(file.getName().substring(lastDotPos + 1));
		return image == null ? iconFile : image;
	}
	
	/**
	 * Returns an icon representing the specified file.
	 * 
	 * @param file
	 * @return
	 */
	public static Image getIcon(FTPFile file) {
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
	private static Image getIcon(String extension) {
		if (imageRegistry == null)
			imageRegistry = new ImageRegistry();
		Image image = imageRegistry.get(extension);
		if (image != null)
			return image;

		Program program = Program.findProgram(extension);
		ImageData imageData = (program == null ? null : program.getImageData());
		if (imageData != null) {
			image = new Image(Display.getCurrent(), imageData);
			imageRegistry.put(extension, image);
		} else {
			image = iconFile;
		}

		return image;
	}	
}
