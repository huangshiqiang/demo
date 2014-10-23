package com.asprise.books.javaui.ch20;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.TextOperationAction;

public class JNLPEditor extends TextEditor {
	private ColorManager colorManager;

	public JNLPEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new JNLPConfiguration(colorManager));
		setDocumentProvider(new JNLPDocumentProvider());
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();
		
		String ID_CA = "com.asprise.books.javaui.ch20.JNLPEditor.CA";
		String ID_FORMAT = "com.asprise.books.javaui.ch20.JNLPEditor.FORMAT";
		
		IAction action = new TextOperationAction(JNLPEditorPlugin.getDefault().getResourceBundle(), "CA", this, SourceViewer.CONTENTASSIST_PROPOSALS);
		action.setActionDefinitionId(ID_CA);
		
		setAction(ID_CA, action);
		setActionActivationCode(ID_CA, ' ', -1, SWT.CTRL);
		
//		action = new TextOperationAction(JNLPEditorPlugin.getDefault().getResourceBundle(), "FORMAT", this, SourceViewer.FORMAT);
//		action.setActionDefinitionId(ID_FORMAT);
//		
//		setAction(ID_FORMAT, action);
//		setActionActivationCode(ID_FORMAT, '`', -1, SWT.CTRL);
	}

}
