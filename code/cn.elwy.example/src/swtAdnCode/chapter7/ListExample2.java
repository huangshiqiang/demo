package chapter7;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ListExample2 {
    Display d;
    Shell s;

    ListExample2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A List Example");
        String items[] = { "Item One", "Item Two", "Item Three", "Item Four", "Item Five" };
        final List l = new List(s, SWT.SINGLE | SWT.BORDER);
        l.setBounds(50, 50, 75, 75);
        l.setItems(items);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
