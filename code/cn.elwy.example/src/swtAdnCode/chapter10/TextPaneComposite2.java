package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class TextPaneComposite2 extends Composite {

    public TextPaneComposite2(Composite parent, int style) {
        super(parent, style);
        this.setLayout(new FillLayout());
        final Text t = new Text(this, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

    }
}
