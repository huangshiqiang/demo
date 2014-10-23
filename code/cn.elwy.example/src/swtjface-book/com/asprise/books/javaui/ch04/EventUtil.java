/******************************************************************************
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * All right reserved. 
 * 
 * Created on Oct 28, 2003 12:29:41 AM by JACK
 * $Id: EventUtil.java,v 1.1 2003/12/22 12:07:54 jackwind Exp $
 * 
 * visit: http://www.asprise.com/swt
 *****************************************************************************/

package com.asprise.books.javaui.ch04;

import org.eclipse.swt.SWT;

/**
 * Utility class for event handling.
 */
public class EventUtil {
	
	public static String getEventName(int eventType) {
		switch(eventType) {
			case SWT.None:
				return "null";
			case SWT.KeyDown:
				return "key down";
			case SWT.KeyUp:
				return "key up";
			case SWT.MouseDown:
				return "mouse down";
			case SWT.MouseUp:
				return "mouse up";
			case SWT.MouseMove:
				return "mouse move";
			case SWT.MouseEnter:
				return "mouse enter";
			case SWT.MouseExit:
				return "mouse exit";
			case SWT.MouseDoubleClick:
				return "mouse double click";
			case SWT.Paint:
				return "paint";
			case SWT.Move:
				return "move";
			case SWT.Resize:
				return "resize";
			case SWT.Dispose:
				return "dispose";
			case SWT.Selection:
				return "selection";
			case SWT.DefaultSelection:
				return "default selection";
			case SWT.FocusIn:
				return "focus in";
			case SWT.FocusOut:
				return "focus out";
			case SWT.Expand:
				return "expand";
			case SWT.Collapse:
				return "collapse";
			case SWT.Iconify:
				return "iconify";
			case SWT.Deiconify:
				return "deiconify";
			case SWT.Close:
				return "close";
			case SWT.Show:
				return "show";
			case SWT.Hide:
				return "hide";
			case SWT.Modify:
				return "modify";
			case SWT.Verify:
				return "verify";
			case SWT.Activate:
				return "activate";
			case SWT.Deactivate:
				return "deactivate";
			case SWT.Help:
				return "help";
			case SWT.DragDetect:
				return "drag detect";
			case SWT.Arm:
				return "arm";
			case SWT.Traverse:
				return "traverse";
			case SWT.MouseHover:
				return "mouse hover";
			case SWT.HardKeyDown:
				return "hard key down";
			case SWT.HardKeyUp:
				return "hard key up";
			case SWT.MenuDetect:
				return "menu detect";
		}
		
		return "unkown ???";
	}

	public static void main(String[] args) {
	}
}
