package chapter5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextFieldExample2 {

    Display d;
    Shell s;

    TextFieldExample2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Text Field Example");
        final Text text1 = new Text(s, SWT.SINGLE | SWT.BORDER);
        text1.setBounds(100, 50, 100, 20);
        final Text text2 = new Text(s, SWT.SINGLE | SWT.BORDER);
        text2.setBounds(100, 75, 100, 20);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
