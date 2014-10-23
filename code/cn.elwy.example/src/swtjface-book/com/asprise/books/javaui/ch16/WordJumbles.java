/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-26 15:46:26 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch16;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class WordJumbles {
	Display display = new Display();
	Shell shell = new Shell(display);

	String word;

	Label[] labelsRowOne;
	Label[] labelsRowTwo;

	Font font = new Font(display, "Arial", 18, SWT.BOLD);

	public void setDragSource(final Label label) {
		//	Allows text to be moved only.
		int operations = DND.DROP_MOVE;
		final DragSource dragSource = new DragSource(label, operations);

		// Data should be transfered in plain text format.
		Transfer[] formats = new Transfer[] { TextTransfer.getInstance()};
		dragSource.setTransfer(formats);
	
		dragSource.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				// Disallows drags if text is not available.
				if (label.getText().length() == 0)
					event.doit = false;
			}

			public void dragSetData(DragSourceEvent event) {
				// Provides the text data.
				if (TextTransfer.getInstance().isSupportedType(event.dataType))
					event.data = label.getText();
			}

			public void dragFinished(DragSourceEvent event) {
				// Removes the text after the move operation.
				if (event.doit == true || event.detail == DND.DROP_MOVE) {
					label.setText("");
				}
			}
		});
		
		label.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				dragSource.dispose();
			}
		});
	}

	public void setDropTarget(final Label label) {
		int operations = DND.DROP_MOVE;
		final DropTarget dropTarget = new DropTarget(label, operations);

		// Data should be transfered in plain text format.
		Transfer[] formats = new Transfer[] { TextTransfer.getInstance()};
		dropTarget.setTransfer(formats);

		dropTarget.addDropListener(new DropTargetListener() {
			public void dragEnter(DropTargetEvent event) {
				// Does not accept any drop if the label has text on it.
				if(label.getText().length() != 0)
					event.detail = DND.DROP_NONE;
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dragOperationChanged(DropTargetEvent event) {
			}

			public void dragOver(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (TextTransfer
					.getInstance()
					.isSupportedType(event.currentDataType)) {
					String text = (String) event.data;
					label.setText(text);
					// Checks the result.
					check();
				}
			}

			public void dropAccept(DropTargetEvent event) {
			}
		});
		
		label.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				dropTarget.dispose();
			}
		});
	}
	
	private void check() {
		for(int i=0; i<word.length(); i++) {
			if(! labelsRowTwo[i].getText().equals(word.charAt(i) + ""))
				return;
		}
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setMessage("Success!");
		messageBox.open();
	}

	public WordJumbles(String word) {
		this.word = word;
		
		shell.setText("Word Jumbles");

		labelsRowOne = new Label[word.length()];
		labelsRowTwo = new Label[word.length()];

		int width = 40;

		// In the production version, you need to implement random permutation
		// generation.
		// Apache Jakarta Commons provides this function, see
		// org.apache.commons.math.random.RandomDataImpl
		int[] randomPermutation = { 5, 2, 6, 3, 1, 4, 0 };

		for (int i = 0; i < word.length(); i++) {
			final Label labelRowOne = new Label(shell, SWT.BORDER);
			labelsRowOne[i] = labelRowOne;
			labelRowOne.setBounds(10 + width * i, 10, width - 5, width - 5);
			labelRowOne.setFont(font);
			labelRowOne.setText(word.charAt(randomPermutation[i]) + "");
			labelRowOne.setAlignment(SWT.CENTER);
			
			setDragSource(labelRowOne);
			//setDropTarget(labelRowOne);

			final Label labelRowTwo = new Label(shell, SWT.BORDER);
			labelsRowTwo[i] = labelRowTwo;
			labelRowTwo.setBounds(
				10 + width * i,
				20 + width,
				width - 5,
				width - 5);
			labelRowTwo.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			labelRowTwo.setFont(font);
			labelRowTwo.setAlignment(SWT.CENTER);
			
			setDragSource(labelRowTwo);
			//setDropTarget(labelRowTwo);
		}
		
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

	public static void main(String[] args) {
		new WordJumbles("ECLIPSE");
	}
}
