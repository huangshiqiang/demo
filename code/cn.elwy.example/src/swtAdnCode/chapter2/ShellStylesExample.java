package chapter2;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class ShellStylesExample {
    public static void main(String[] args) {
        Display d = new Display();
        Shell s = new Shell(d, SWT.CLOSE | SWT.RESIZE);
        s.setSize(300, 300);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
