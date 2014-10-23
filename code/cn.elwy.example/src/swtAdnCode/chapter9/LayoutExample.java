package chapter9;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class LayoutExample {
    Display d;
    Shell s;

    LayoutExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(500, 500);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Menu Example");
        s.setLayout(new FillLayout());
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
