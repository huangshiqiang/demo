package chapter14;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class MouseTrackExample {

    final Display d;
    final Shell s;

    public MouseTrackExample() {
        d = new Display();
        s = new Shell(d);

        s.setSize(250, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A MouseTrackListener Example");
        final Button b = new Button(s, SWT.PUSH);
        b.setText("Push Me");
        b.setBounds(20, 50, 55, 25);
        s.open();
        final Color oldColor = b.getBackground();

        b.addMouseTrackListener(new MouseTrackAdapter() {
            public void mouseEnter(MouseEvent e) {
                b.setBackground(new Color(d, 0, 153, 153));

            }

            public void mouseExit(MouseEvent e) {
                b.setBackground(oldColor);
            }
        });

        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
