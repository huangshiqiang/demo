/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-6-4 11:50:35 by JACK
 * $Id$
 * 
 *****************************************************************************/

package com.asprise.books.javaui.ch20;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 
 */
public class JNLPFormatStrategy implements IFormattingStrategy {

	/**
	 * 
	 */
	public JNLPFormatStrategy() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStarts(java.lang.String)
	 */
	public void formatterStarts(String initialIndentation) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#format(java.lang.String, boolean, java.lang.String, int[])
	 */
	public String format(
		String content,
		boolean isLineStart,
		String indentation,
		int[] positions) {
		
		System.out.println("FORMAT CALLED.");
		
		OutputFormat outputFormat = new OutputFormat();
		outputFormat.setVersion("1.0");
		outputFormat.setEncoding("UTF-8");
		outputFormat.setIndent(4);
		XMLSerializer serializer = new XMLSerializer(outputFormat);
		
		StringWriter writer = new StringWriter();
		serializer.setOutputCharStream(writer);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		InputStream inputStream = new StringBufferInputStream(content);
		
		try {
			Document doc = factory.newDocumentBuilder().parse(inputStream);
			serializer.serialize(doc);
			
			return writer.toString();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
//
//			System.out.println("CONTENT: " + content + "\tIdentation: " + indentation);
//		
//		
//		// Starts a new line for each tag.
//		if( (!isLineStart) && content.charAt(1) != '/' ) {
//			return "\n" + content;
//		}
//		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStops()
	 */
	public void formatterStops() {
	}

}
