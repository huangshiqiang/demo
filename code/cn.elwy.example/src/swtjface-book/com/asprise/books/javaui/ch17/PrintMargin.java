/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-2 18:55:02 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch17;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

/**
 * Contains margin information (in pixels) for a print job.
 *  
 */
public class PrintMargin {
	// Margin to the left side, in pixels
	public int left;
	//	Margins to the right side, in pixels
	public int right;
	//	Margins to the top side, in pixels
	public int top;
	//	Margins to the bottom side, in pixels
	public int bottom;

	private PrintMargin(int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	/**
	 * Returns a PrintMargin object containing the true border margins for the
	 * specified printer with the given margin in inches.
	 * Note: all four sides share the same margin width. 
	 * @param printer
	 * @param margin
	 * @return
	 */
	static PrintMargin getPrintMargin(Printer printer, double margin) {
		return getPrintMargin(printer, margin, margin, margin, margin);
	}

	/**
	 * Returns a PrintMargin object containing the true border margins for the
	 * specified printer with the given margin width (in inches) for each side.
	 */
	static PrintMargin getPrintMargin(
		Printer printer,
		double marginLeft,
		double marginRight,
		double marginTop,
		double marginBottom) {
		Rectangle clientArea = printer.getClientArea();
		Rectangle trim = printer.computeTrim(0, 0, 0, 0);

		//System.out.println(printer.getBounds() + " - " + clientArea + "" +
		// trim);
		Point dpi = printer.getDPI();

		int leftMargin = (int) (marginLeft * dpi.x) - trim.x;
		int rightMargin =
			clientArea.width
				+ trim.width
				- (int) (marginRight * dpi.x)
				- trim.x;
		int topMargin = (int) (marginTop * dpi.y) - trim.y;
		int bottomMargin =
			clientArea.height
				+ trim.height
				- (int) (marginBottom * dpi.y)
				- trim.y;

		return new PrintMargin(
			leftMargin,
			rightMargin,
			topMargin,
			bottomMargin);
	}

	public String toString() {
		return "Margin { "
			+ left
			+ ", "
			+ right
			+ "; "
			+ top
			+ ", "
			+ bottom
			+ " }";
	}
}
