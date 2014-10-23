/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Dec 23, 2003 1:56:09 PM by JACK
 * $Id$
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch01;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The photo uploader implemented in Abstract Window Toolkit.
 */
public class PhotoUploaderAWT extends PhotoUploader {

	Frame frame = new Frame("Photo Uploader (AWT)");

	Label labelUser = new Label("User name: ");
	Label labelPhoto = new Label("Photo: ");

	TextField textUser = new TextField(20);
	TextField textPhoto = new TextField(20);

	Button buttonBrowsePhoto = new Button("Browse");

	Button buttonUpload = new Button("Upload");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asprise.swt.example.ch01.PhotoUploader#init()
	 */
	public void init() {
		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		// Adds components for the first row.
		addComponent(c, frame, labelUser, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(c, frame, textUser, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		// Adds components for the second row.
		addComponent(c, frame, labelPhoto, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(c, frame, textPhoto, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(c, frame, buttonBrowsePhoto, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		// last row.
		addComponent(c, frame, buttonUpload, 2, 0, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		// Adds action listener for the buttons.
		buttonBrowsePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Select a photo");
				fd.setVisible(true);
				String file = fd.getFile();
				if(file != null) {
					textPhoto.setText(file);
				}
				fd.dispose();
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
		frame.show();
	}

	/**
	 * Adds a component to a container with specified layout settings.
	 * @param constraints
	 * @param container
	 * @param component
	 * @param row
	 * @param col
	 * @param width
	 * @param height
	 * @param anchor
	 * @param fill
	 */
	public static void addComponent(
		GridBagConstraints constraints,
		Container container, Component component,
		int row,
		int col,
		int width,
		int height,
		int anchor,
		int fill) {
		
		if(constraints == null)
			constraints = new GridBagConstraints();
		
		constraints.gridx = col;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.anchor = anchor;
		double weightx = 0.0;
		double weighty = 0.0;

		if (width > 1)
			weightx = 1.0;
		if (height > 1)
			weighty = 1.0;
		switch (fill) {
			case GridBagConstraints.HORIZONTAL :
				constraints.weightx = weightx;
				constraints.weighty = 0.0;
				break;
			case GridBagConstraints.VERTICAL :
				constraints.weighty = weighty;
				constraints.weightx = 0.0;
				break;
			case GridBagConstraints.BOTH :
				constraints.weightx = weightx;
				constraints.weighty = weighty;
				break;
			case GridBagConstraints.NONE :
				constraints.weightx = 0.0;
				constraints.weighty = 0.0;
				break;
			default :
				break;
		}
		constraints.fill = fill;
		container.add(component, constraints);
	}
}

