package cn.elwy.editor.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class AboutEditor extends Dialog {

    public AboutEditor(Shell parentShell) {
        super(parentShell);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        // TODO Auto-generated method stub
        Composite container = (Composite) super.createDialogArea(parent);
        return container;
    }

    @Override
    protected void okPressed() {
        // TODO Auto-generated method stub
        super.okPressed();
    }
}
