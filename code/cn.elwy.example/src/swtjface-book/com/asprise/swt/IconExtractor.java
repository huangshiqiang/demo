/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-14 8:15:10 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.swt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 */
public class IconExtractor {

	/**
	 * 
	 */
	public IconExtractor() {
		super();
	}

	public static void main(String[] args) throws IOException {
		com.asprise.util.io.FileTraverser traverser = new com.asprise.util.io.FileTraverser("E:/Eclipse/eclipse/plugins");
		System.out.println("Starting inspection ");
		java.io.File[] files = traverser.findFilesWithExtension("gif", false);
		System.out.println("Total GIF files found: " + files);

		for(int i=0; i<files.length; i++) {
			java.io.File src = files[i];
			java.io.File dest = new java.io.File("E:/Eclipse/eclipse/icons", src.getName());
	
			if(src.length() > 4000) { // skip figures larger than 4K bytes.
				continue;
			}
	
			FileInputStream reader = new FileInputStream(src);
			FileOutputStream writer = new FileOutputStream(dest);
	
			com.asprise.util.io.IOCopier.copyStream(reader, writer);
			writer.flush();
			writer.close();
			reader.close();
	
			System.out.println("File copied #" + i + ", Name: " + src.getName());
		}
	}
}
