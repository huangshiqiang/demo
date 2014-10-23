package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class GroupShellExample2 {
    Display d;
    Shell s;

    GroupShellExample2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(200, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Group Example");
        final Group g = new Group(s, SWT.SHADOW_ETCHED_IN);
        g.setSize(110, 75);
        g.setText("Options Group");
        final Button b1;
        final Button b2;
        final Button b3;
        b1 = new Button(g, SWT.RADIO);
        b1.setBounds(10, 20, 75, 15);
        b1.setText("Option One");
        b2 = new Button(g, SWT.RADIO);
        b2.setBounds(10, 35, 75, 15);
        b2.setText("Option Two");
        b3 = new Button(g, SWT.RADIO);
        b3.setBounds(10, 50, 80, 15);
        b3.setText("Option Three");
        g.pack();
        g.setLocation(20, 20);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
