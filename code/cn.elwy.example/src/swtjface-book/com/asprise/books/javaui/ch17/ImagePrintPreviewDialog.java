/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-2 15:26:02 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch17;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 *  
 */
public class ImagePrintPreviewDialog extends Dialog {
	ImageViewer viewer;
	Shell shell;
	Canvas canvas;
	Printer printer;
	PrintMargin margin;
	Combo combo;

	public ImagePrintPreviewDialog(ImageViewer viewer) {
		super(viewer.shell);
		this.viewer = viewer;
	}

	public void open() {
		shell =
			new Shell(
				viewer.shell,
				SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		shell.setText("Print preview");
		shell.setLayout(new GridLayout(4, false));

		final Button buttonSelectPrinter = new Button(shell, SWT.PUSH);
		buttonSelectPrinter.setText("Select a printer");
		buttonSelectPrinter.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				PrintDialog dialog = new PrintDialog(shell);
				// Prompts the printer dialog to let the user select a printer.
				PrinterData printerData = dialog.open();

				if (printerData == null) // the user cancels the dialog
					return;
				// Loads the printer.
				final Printer printer = new Printer(printerData);
				setPrinter(
					printer,
					Double.parseDouble(
						combo.getItem(combo.getSelectionIndex())));
			}
		});

		new Label(shell, SWT.NULL).setText("Margin in inches: ");
		combo = new Combo(shell, SWT.READ_ONLY);
		combo.add("0.5");
		combo.add("1.0");
		combo.add("1.5");
		combo.add("2.0");
		combo.add("2.5");
		combo.add("3.0");
		combo.select(1);
		combo.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				double value =
					Double.parseDouble(
						combo.getItem(combo.getSelectionIndex()));
				setPrinter(printer, value);
			}
		});

		final Button buttonPrint = new Button(shell, SWT.PUSH);
		buttonPrint.setText("Print");
		buttonPrint.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (printer == null)
					viewer.print();
				else
					viewer.print(printer, margin);
				shell.dispose();
			}
		});

		canvas = new Canvas(shell, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 4;
		canvas.setLayoutData(gridData);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				int canvasBorder = 20;

				if (printer == null || printer.isDisposed())
					return;
				Rectangle rectangle = printer.getBounds();
				Point canvasSize = canvas.getSize();

				double viewScaleFactor =
					(canvasSize.x - canvasBorder * 2) * 1.0 / rectangle.width;
				viewScaleFactor =
					Math.min(
						viewScaleFactor,
						(canvasSize.y - canvasBorder * 2)
							* 1.0
							/ rectangle.height);

				int offsetX =
					(canvasSize.x - (int) (viewScaleFactor * rectangle.width))
						/ 2;
				int offsetY =
					(canvasSize.y - (int) (viewScaleFactor * rectangle.height))
						/ 2;

				e.gc.setBackground(
					shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
				// draws the page layout
				e.gc.fillRectangle(
					offsetX,
					offsetY,
					(int) (viewScaleFactor * rectangle.width),
					(int) (viewScaleFactor * rectangle.height));

				// draws the margin.
				e.gc.setLineStyle(SWT.LINE_DASH);
				e.gc.setForeground(
					shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));

				int marginOffsetX =
					offsetX + (int) (viewScaleFactor * margin.left);
				int marginOffsetY =
					offsetY + (int) (viewScaleFactor * margin.top);
				e.gc.drawRectangle(
					marginOffsetX,
					marginOffsetY,
					(int) (viewScaleFactor * (margin.right - margin.left)),
					(int) (viewScaleFactor * (margin.bottom - margin.top)));

				if (viewer.image != null) {
					int imageWidth = viewer.image.getBounds().width;
					int imageHeight = viewer.image.getBounds().height;

					double dpiScaleFactorX =
						printer.getDPI().x
							* 1.0
							/ shell.getDisplay().getDPI().x;
					double dpiScaleFactorY =
						printer.getDPI().y
							* 1.0
							/ shell.getDisplay().getDPI().y;

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

					e.gc.drawImage(
						viewer.image,
						0,
						0,
						imageWidth,
						imageHeight,
						marginOffsetX,
						marginOffsetY,
						(int) (dpiScaleFactorX
							* imageSizeFactor
							* imageWidth
							* viewScaleFactor),
						(int) (dpiScaleFactorY
							* imageSizeFactor
							* imageHeight
							* viewScaleFactor));

				}

			}
		});

		shell.setSize(400, 400);
		shell.open();
		setPrinter(null, 1.0);

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				// If no more entries in event queue
				shell.getDisplay().sleep();
			}
		}
	}

	/**
	 * Sets target printer.
	 * 
	 * @param printer
	 */
	void setPrinter(Printer printer, double marginSize) {
		if (printer == null) {
			printer = new Printer(Printer.getDefaultPrinterData());
		}
		this.printer = printer;
		margin = PrintMargin.getPrintMargin(printer, marginSize);
		canvas.redraw();
	}

}
