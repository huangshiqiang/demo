package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class GroupShellExample3 {
    Display d;
    Shell s;

    GroupShellExample3() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Composite Example");
        final GroupExample ge1 = new GroupExample(s, SWT.SHADOW_ETCHED_IN);
        ge1.setLocation(10, 10);
        final GroupExample ge2 = new GroupExample(s, SWT.SHADOW_ETCHED_IN);
        // final GroupExample ge2 = new GroupExample(s, SWT.SHADOW_ETCHED_IN, "Option Group Two");
        ge2.setLocation(100, 100);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
