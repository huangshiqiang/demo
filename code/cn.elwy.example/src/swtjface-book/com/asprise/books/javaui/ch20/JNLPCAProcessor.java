/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-6-4 10:45:58 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch20;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 *  
 */
public class JNLPCAProcessor implements IContentAssistProcessor {

	// Proposed parts before the cursor
	final static String[] PARTS1 =
		{
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n",
			"<jnlp spec=\"1.0+\" codebase=\"\" href=\"\">",
			"<information>",
			"<title>",
			"<vendor>",
			"<homepage href=\"",
			"<description>",
			"<icon href=\"",
			"<security>",
			"<resources>",
			"<j2se version=\"",
			"<jar href=\"",
			"<application-desc main-class=\"" };

	// Proposed parts after the cursor
	final static String[] PARTS2 =
		{
			"",
			"\n</jnlp>",
			"\n</information>",
			"</title>",
			"</vendor>",
			"\"/>",
			"</description>",
			"\"/>",
			"\n</security>",
			"\n</resources>",
			"\"/>",
			"\"/>",
			"\"/>" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public ICompletionProposal[] computeCompletionProposals(
		ITextViewer viewer,
		int documentOffset) {

		IDocument document = viewer.getDocument();

		// computes the tag starting part.
		StringBuffer sb = new StringBuffer();
		int offset = documentOffset;
		for (;;) {
			char c;
			try {
				c = document.getChar(--offset);
			} catch (BadLocationException e) {
				sb.setLength(0);
				break;
			}
			if (c == '>' || Character.isWhitespace(c)) {
				sb.setLength(0);
				break;
			}
			sb.append(c);
			if (c == '<') {
				sb = sb.reverse();
				break;
			}
		}

		String startingPart = sb.toString();
		ArrayList list = new ArrayList();
		if (startingPart.length() > 0) {
			for (int i = 0; i < PARTS1.length; i++) {
				if (PARTS1[i].startsWith(startingPart)) {
					String completeText = PARTS1[i] + PARTS2[i];
					int cursorPos = PARTS1[i].length();
					CompletionProposal proposal =
						new CompletionProposal(
							completeText,
							documentOffset - startingPart.length(),
							startingPart.length(),
							cursorPos);
					list.add(proposal);
				}
			}
		}

		ICompletionProposal[] proposals = new ICompletionProposal[list.size()];
		list.toArray(proposals);

		return proposals;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public IContextInformation[] computeContextInformation(
		ITextViewer viewer,
		int documentOffset) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '<' };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
	 */
	public String getErrorMessage() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
	 */
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

}
