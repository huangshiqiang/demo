package chapter9;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class FillLayoutExample {
    Display d;
    Shell s;

    FillLayoutExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A FillLayout Example");
        s.setLayout(new FillLayout());
        final Text t = new Text(s, SWT.MULTI | SWT.BORDER | SWT.WRAP);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
