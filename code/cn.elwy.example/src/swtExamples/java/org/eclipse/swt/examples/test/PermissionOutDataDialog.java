package org.eclipse.swt.examples.test;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class PermissionOutDataDialog extends Dialog {

    /**
     * Create the dialog.
     * @param parentShell
     */
    public PermissionOutDataDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(1, false));
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);

        Label label = new Label(container, SWT.WRAP | SWT.CENTER);
        label.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
        label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        GridData gd_label = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
        gd_label.widthHint = 440;
        gd_label.heightHint = 116;
        label.setLayoutData(gd_label);
        label.setAlignment(SWT.CENTER);
        label.setText("\u670D\u52A1\u5668\u6743\u9650\u4FE1\u606F\u5DF2\u66F4\u65B0,\u8BF7\u9009\u62E9\u66F4\u65B0\u672C\u5730\u6743\u9650\u5E93,\u5426\u5219\u65E0\u6CD5\u64CD\u4F5C.\u662F\u5426\u66F4\u65B0\u672C\u5730\u6743\u9650\u5E93?");

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        button.setText("\u66F4\u65B0");
        Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        button_1.setText("\u53D6\u6D88");
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }

}
