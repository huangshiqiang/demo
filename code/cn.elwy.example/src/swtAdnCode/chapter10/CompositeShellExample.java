package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class CompositeShellExample {
    Display d;
    Shell s;

    CompositeShellExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(500, 500);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Composite Example");
        s.setLayout(new FillLayout());
        final TextPaneComposite tpc = new TextPaneComposite(s);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
