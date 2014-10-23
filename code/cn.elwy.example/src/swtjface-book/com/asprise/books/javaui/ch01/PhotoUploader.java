/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Dec 23, 2003 1:58:17 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch01;

import javax.swing.JOptionPane;

/**
 *  
 */
public abstract class PhotoUploader {
	/**
	 * Performs initialization here.
	 *  
	 */
	public abstract void init();

	/**
	 * Prompts the photo selection UI.
	 *  
	 */
	public abstract void prompt();

	/**
	 * Uploads the specified photo.
	 * 
	 * @param name
	 * @param photoLocation
	 */
	public void uploadPhoto(String name, String photoLocation) {
		System.out.println("Photo uploaded for user " + name + ".");
		System.out.println("Specified photo is: " + photoLocation);
	}

	public static void main(String[] args) {
		Object[] options = { "AWT", "Swing", "SWT" };
		int n =
			JOptionPane.showOptionDialog(
				null,
				"Please select the implementation of " + "the photo uploader",
				"",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[2]);
		
		

		PhotoUploader photoUploader = null;
		
		switch(n) {
			case 0:
				photoUploader = new PhotoUploaderAWT();
				break;
				
			case 1:
				photoUploader = new PhotoUploaderSwing();
				break;
			
			case 2:
				photoUploader = new PhotoUploaderSWT();
				break;
		}

		photoUploader.init();
		photoUploader.prompt();
	}
}
