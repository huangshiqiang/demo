package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class GroupShellExample {
    Display d;
    Shell s;

    GroupShellExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(200, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Composite Example");
        final GroupExample ge = new GroupExample(s, SWT.SHADOW_ETCHED_IN);
        ge.setLocation(20, 20);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
