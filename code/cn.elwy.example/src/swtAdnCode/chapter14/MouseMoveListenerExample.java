package chapter14;

import java.util.Random;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

public class MouseMoveListenerExample {
    final Display d;
    final Shell s;

    public MouseMoveListenerExample() {
        d = new Display();
        s = new Shell(d);

        s.setSize(250, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A MouseListener Example");
        final Button b = new Button(s, SWT.PUSH);
        b.setText("Push Me");
        b.setBounds(20, 50, 55, 25);
        s.open();

        b.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                Random r = new Random(System.currentTimeMillis());
                Point p = s.getSize();
                int newX = r.nextInt(p.y);
                int newY = r.nextInt(p.x);
                b.setBounds(newX - 55, newY - 25, 55, 25);
            }

        });

        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
