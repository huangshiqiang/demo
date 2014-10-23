package chapter2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class DialogExample extends Dialog {
    public DialogExample(Shell parent) {
        super(parent);
    }

    public String open() {
        Shell parent = getParent();
        Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setSize(100, 100);
        dialog.setText("A Dialog");
        dialog.open();
        Display display = parent.getDisplay();
        while (!dialog.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return "After Dialog";
    }
}
