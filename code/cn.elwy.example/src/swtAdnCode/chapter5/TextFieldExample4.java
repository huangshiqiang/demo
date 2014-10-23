package chapter5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class TextFieldExample4 {

    Display d;
    Shell s;

    TextFieldExample4() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Text Field Example");
        Text text1 = new Text(s, SWT.WRAP | SWT.BORDER);
        text1.setBounds(100, 50, 100, 20);
        text1.setTextLimit(5);
        text1.setText("12345");
        Text text2 = new Text(s, SWT.SINGLE | SWT.BORDER);
        text2.setBounds(100, 75, 100, 20);
        text2.setTextLimit(30);

        // add a focus listener
        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent e) {
                Text t = (Text) e.widget;
                t.selectAll();
            }

            public void focusLost(FocusEvent e) {
                Text t = (Text) e.widget;
                if (t.getSelectionCount() > 0) {
                    t.clearSelection();
                }
            }
        };
        text1.addFocusListener(focusListener);
        text2.addFocusListener(focusListener);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
