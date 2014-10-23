package org.eclipse.swt.examples.test;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

public class CommitWarningDialog extends Dialog {

    /**
     * Create the dialog.
     * @param parentShell
     */
    public CommitWarningDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        parent.setToolTipText("");
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(1, false));

        Label lblNewLabel = new Label(container, SWT.CENTER);
        lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 438;
        lblNewLabel.setLayoutData(gd_lblNewLabel);
        lblNewLabel.setAlignment(SWT.LEFT);
        lblNewLabel
                .setText("\u6CE8\u610F:\u4EE5\u4E0B\u6587\u4EF6\u60A8\u6CA1\u6709\u6743\u9650\u63D0\u4EA4,\u662F\u5426\u8DF3\u8FC7\u8FD9\u4E9B\u6587\u4EF6\u7EE7\u7EED\u63D0\u4EA4?");

        List list = new List(container, SWT.BORDER | SWT.V_SCROLL);
        list.setItems(new String[] { "\u6587\u4EF61.java", "\u6587\u4EF61.java", "\u6587\u4EF61.java",
                "\u6587\u4EF61.java", "\u6587\u4EF61.java", "\u6587\u4EF61.java", "\u6587\u4EF61.java" });
        GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
        gd_list.heightHint = 180;
        gd_list.widthHint = 419;
        list.setLayoutData(gd_list);

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        button.setText("\u7EE7\u7EED");
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
