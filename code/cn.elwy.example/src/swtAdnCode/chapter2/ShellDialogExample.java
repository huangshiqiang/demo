package chapter2;

import org.eclipse.swt.widgets.*;

public class ShellDialogExample {
    ShellDialogExample() {
        Display d = new Display();
        Shell s = new Shell(d);
        s.setSize(300, 300);
        s.open();
        DialogExample de = new DialogExample(s);
        String result = de.open();
        System.out.println(result);
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();

    }
}
