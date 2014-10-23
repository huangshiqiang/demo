package chapter14;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class MouseListenerExample {
    final Display d;
    final Shell s;

    public MouseListenerExample() {
        d = new Display();
        s = new Shell(d);

        s.setSize(250, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A MouseListener Example");
        s.open();

        s.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
                Label l = new Label(s, SWT.FLAT);
                l.setText("Mouse Button Down at:" + e.x + " " + e.y);
                l.setBounds(e.x, e.y, 150, 15);

            }

            public void mouseUp(MouseEvent e) {
                Label l = new Label(s, SWT.FLAT);
                l.setText("Mouse Button up at:" + e.x + " " + e.y);
                l.setBounds(e.x, e.y, 150, 15);
            }

            public void mouseDoubleClick(MouseEvent e) {

            }
        });

        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
