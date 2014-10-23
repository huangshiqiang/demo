/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-2 14:57:37 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch17;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 *  
 */
public class ImageViewer {
	Display display = new Display();
	Shell shell = new Shell(display);

	Canvas canvas;

	Image image;
	String fileName;
	public ImageViewer() {
		shell.setText("Image viewer");
		shell.setLayout(new GridLayout(1, true));

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
		ToolItem itemOpen = new ToolItem(toolBar, SWT.PUSH);
		itemOpen.setText("Open");
		itemOpen.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					if (image != null)
						image.dispose();
					image = null;
					try {
						image = new Image(display, file);
					} catch (RuntimeException e) {
						// e.printStackTrace();
					}

					if (image != null) {
						fileName = file;
					} else {
						System.err.println(
							"Failed to load image from file: " + file);
					}
					canvas.redraw();
				}
			}
		});

		ToolItem itemPrintPreview = new ToolItem(toolBar, SWT.PUSH);

		itemPrintPreview.setText("Preview");
		itemPrintPreview.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				ImagePrintPreviewDialog dialog =
					new ImagePrintPreviewDialog(ImageViewer.this);
				dialog.open();
			}
		});

		ToolItem itemPrint = new ToolItem(toolBar, SWT.PUSH);
		itemPrint.setText("Print");
		itemPrint.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				print();
			}
		});

		canvas = new Canvas(shell, SWT.BORDER);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (image == null) {
					e.gc.drawString("No image", 0, 0);
				} else {
					e.gc.drawImage(image, 0, 0);
				}
			}
		});

		image = new Image(display, "icons/scene.jpg");
		fileName = "scene.jpg";

		shell.setSize(500, 400);
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

	/**
	 * Lets the user to select a printer and prints the image on it.
	 *  
	 */
	void print() {
		PrintDialog dialog = new PrintDialog(shell);
		// Prompts the printer dialog to let the user select a printer.
		PrinterData printerData = dialog.open();

		if (printerData == null) // the user cancels the dialog
			return;
		// Loads the printer.
		Printer printer = new Printer(printerData);
		print(printer, null);
	}

	/**
	 * Prints the image current displayed to the specified printer.
	 * 
	 * @param printer
	 */
	void print(final Printer printer, PrintMargin printMargin) {
		if (image == null) // If no image is loaded, do not print.
			return;

		final Point printerDPI = printer.getDPI();
		final Point displayDPI = display.getDPI();
		System.out.println(displayDPI + " " + printerDPI);
		
		final PrintMargin margin = (printMargin == null ?  PrintMargin.getPrintMargin(printer, 1.0) : printMargin);

		Thread printThread = new Thread() {
			public void run() {
				if (!printer.startJob(fileName)) {
					System.err.println("Failed to start print job!");
					printer.dispose();
					return;
				}

				GC gc = new GC(printer);

				if (!printer.startPage()) {
					System.err.println("Failed to start a new page!");
					gc.dispose();
					return;
				} else {
					int imageWidth = image.getBounds().width;
					int imageHeight = image.getBounds().height;

					// Handles DPI conversion.
					double dpiScaleFactorX = printerDPI.x * 1.0 / displayDPI.x;
					double dpiScaleFactorY = printerDPI.y * 1.0 / displayDPI.y;

					// If the image is too large to draw on a page, reduces its
					// width and height proportionally.
					double imageSizeFactor =
						Math.min(
							1,
							(margin.right - margin.left)
								* 1.0
								/ (dpiScaleFactorX * imageWidth));
					imageSizeFactor =
						Math.min(
							imageSizeFactor,
							(margin.bottom - margin.top)
								* 1.0
								/ (dpiScaleFactorY * imageHeight));

					// Draws the image to the printer.
					gc.drawImage(
						image,
						0,
						0,
						imageWidth,
						imageHeight,
						margin.left,
						margin.top,
						(int) (dpiScaleFactorX * imageSizeFactor * imageWidth),
						(int) (dpiScaleFactorY
							* imageSizeFactor
							* imageHeight));
					gc.dispose();
				}

				printer.endPage();
				printer.endJob();

				printer.dispose();
				System.out.println("Printing job done!");
			}
		};
		printThread.start();
	}

	public static void main(String[] args) {
		new ImageViewer();
	}
}
