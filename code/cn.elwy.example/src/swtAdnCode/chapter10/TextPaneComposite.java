package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class TextPaneComposite extends Composite {

    public TextPaneComposite(Composite parent) {
        super(parent, SWT.BORDER);
        this.setLayout(new FillLayout());
        final Text t = new Text(this, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

    }
}
