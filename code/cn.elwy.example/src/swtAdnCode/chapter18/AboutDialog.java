package chapter18;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AboutDialog extends Dialog {
    AboutDialog(Shell parent) {
        super(parent);
    }

    public void open() {
        Shell parent = getParent();
        final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setSize(200, 100);
        dialog.setText("About");
        final Label l = new Label(dialog, SWT.NONE);
        l.setText("An SWT Text Editor");
        l.setBounds(43, 20, 100, 20);
        Button b = new Button(dialog, SWT.PUSH | SWT.BORDER);
        b.setText("OK");
        b.setBounds(80, 45, 40, 25);
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                dialog.dispose();
            }
        });
        dialog.open();
        Display display = parent.getDisplay();
        while (!dialog.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
}
