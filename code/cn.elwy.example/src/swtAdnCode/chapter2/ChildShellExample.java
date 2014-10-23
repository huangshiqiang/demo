package chapter2;

import org.eclipse.swt.widgets.*;

public class ChildShellExample {
    Display d = new Display();

    ChildShellExample() {
        Shell s = new Shell(d);
        s.setSize(500, 500);
        s.open();
        ChildShell cs = new ChildShell(s);
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
