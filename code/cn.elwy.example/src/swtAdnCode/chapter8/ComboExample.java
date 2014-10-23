package chapter8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ComboExample {
    Display d;
    Shell s;

    ComboExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Combo Example");
        final Combo c = new Combo(s, SWT.READ_ONLY);
        c.setBounds(50, 50, 150, 65);
        String items[] = { "Item One", "Item Two", "Item Three", "Item Four", "Item Five" };
        c.setItems(items);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
