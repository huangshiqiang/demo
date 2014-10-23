/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Dec 23, 2003 2:39:53 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch01;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *  
 */
public class PhotoUploaderSwing extends PhotoUploader {

	JFrame frame = new JFrame("Photo Uploader (Swing)");

	JLabel labelUser = new JLabel("User name: ");
	JLabel labelPhoto = new JLabel("Photo: ");

	JTextField textUser = new JTextField(20);
	JTextField textPhoto = new JTextField(20);

	JButton buttonBrowsePhoto = new JButton("Browse");

	JButton buttonUpload = new JButton("Upload");
	
	static {
		// Set L&F.
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asprise.swt.example.ch01.PhotoUploader#init()
	 */
	public void init() {

		frame.getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Adds components for the first row.
		PhotoUploaderAWT.addComponent(
			c,
			frame.getContentPane(),
			labelUser,
			0,
			0,
			1,
			1,
			GridBagConstraints.CENTER,
			GridBagConstraints.NONE);
		PhotoUploaderAWT.addComponent(
			c,
			frame.getContentPane(),
			textUser,
			0,
			1,
			1,
			1,
			GridBagConstraints.CENTER,
			GridBagConstraints.HORIZONTAL);

		// Adds components for the second row.
		PhotoUploaderAWT.addComponent(
			c,
			frame.getContentPane(),
			labelPhoto,
			1,
			0,
			1,
			1,
			GridBagConstraints.CENTER,
			GridBagConstraints.NONE);
		PhotoUploaderAWT.addComponent(
			c,
			frame.getContentPane(),
			textPhoto,
			1,
			1,
			1,
			1,
			GridBagConstraints.CENTER,
			GridBagConstraints.HORIZONTAL);
		PhotoUploaderAWT.addComponent(
			c,
			frame.getContentPane(),
			buttonBrowsePhoto,
			1,
			2,
			1,
			1,
			GridBagConstraints.CENTER,
			GridBagConstraints.NONE);

		// last row.
		PhotoUploaderAWT.addComponent(
			c,
			frame.getContentPane(),
			buttonUpload,
			2,
			0,
			3,
			1,
			GridBagConstraints.CENTER,
			GridBagConstraints.NONE);

		// Adds action listener for the buttons.
		buttonBrowsePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(frame)
					== JFileChooser.APPROVE_OPTION) {
					textPhoto.setText(chooser.getSelectedFile().getName());
				}
			}
		});

		buttonUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadPhoto(textUser.getText(), textPhoto.getText());
				System.exit(0);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asprise.swt.example.ch01.PhotoUploader#prompt()
	 */
	public void prompt() {
		frame.pack();
		frame.setVisible(true);
	}

}
