package chapter2;

import org.eclipse.swt.widgets.*;

public class SimpleShell {

    SimpleShell() {
        Display d = new Display();
        Shell s = new Shell(d);
        s.setSize(500, 500);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
