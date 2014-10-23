package chapter2;

import org.eclipse.swt.widgets.*;

public class ChildShellExample2 {
    Display d = new Display();

    ChildShellExample2() {
        Shell s = new Shell(d);
        s.setSize(500, 500);
        s.open();
        ChildShell cs1 = new ChildShell(s);
        ChildShell cs2 = new ChildShell(s);
        ChildShell cs3 = new ChildShell(s);
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();

    }
}
