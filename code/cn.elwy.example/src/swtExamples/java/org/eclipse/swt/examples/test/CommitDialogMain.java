package org.eclipse.swt.examples.test;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CommitDialogMain {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

        CommitDialog cd = new CommitDialog(shell, Dialog.OK | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        cd.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
