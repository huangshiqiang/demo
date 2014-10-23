package chapter6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ButtonExample {

    Display d;
    Shell s;

    ButtonExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(150, 150);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Button Example");
        final Button b1 = new Button(s, SWT.PUSH);
        b1.setBounds(50, 50, 75, 40);
        b1.setText("Push Me");
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
