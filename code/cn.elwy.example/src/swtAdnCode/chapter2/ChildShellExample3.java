package chapter2;

import org.eclipse.swt.widgets.*;

public class ChildShellExample3 {
    Display d = new Display();

    ChildShellExample3() {
        Shell s = new Shell(d);
        s.setSize(500, 500);
        s.open();
        ChildShell cs1 = new ChildShell(s);
        System.out.println("Execution Continues");
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();

    }
}
