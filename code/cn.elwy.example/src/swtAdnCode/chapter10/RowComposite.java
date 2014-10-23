package chapter10;

import org.eclipse.swt.events.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class RowComposite extends Composite {

    final Button okBtn;
    final Button cancelBtn;

    public RowComposite(Composite c) {
        super(c, SWT.NO_FOCUS);
        RowLayout rl = new RowLayout();
        rl.wrap = false;
        rl.pack = false;
        this.setLayout(rl);
        okBtn = new Button(this, SWT.BORDER | SWT.PUSH);
        okBtn.setText("OK");
        okBtn.setSize(30, 20);
        cancelBtn = new Button(this, SWT.BORDER | SWT.PUSH);
        cancelBtn.setText("Cancel");
        cancelBtn.setSize(30, 20);
        cancelBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Cancel was clicked");
            }
        });
    }
}
