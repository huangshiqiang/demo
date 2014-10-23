package chapter9;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class RowLayoutExample {
    Display d;
    Shell s;

    RowLayoutExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A RowLayout Example");
        s.setLayout(new RowLayout());
        final Text t = new Text(s, SWT.SINGLE | SWT.BORDER);
        final Button b = new Button(s, SWT.BORDER);
        final Button b1 = new Button(s, SWT.BORDER);
        b.setText("OK");
        b1.setText("Cancel");
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
