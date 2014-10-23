package chapter9;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class GridLayoutExample2 {
    Display d;
    Shell s;

    GridLayoutExample2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A GridLayout Example");
        GridLayout gl = new GridLayout();
        gl.numColumns = 3;
        s.setLayout(gl);
        final Label l1 = new Label(s, SWT.BORDER);
        l1.setText("Column One");
        final Label l2 = new Label(s, SWT.BORDER);
        l2.setText("Column Two");
        final Label l3 = new Label(s, SWT.BORDER);
        l3.setText("Column Three");
        final Text t1 = new Text(s, SWT.SINGLE | SWT.BORDER);
        final Text t2 = new Text(s, SWT.SINGLE | SWT.BORDER);
        final Text t3 = new Text(s, SWT.SINGLE | SWT.BORDER);
        final Text t4 = new Text(s, SWT.SINGLE | SWT.BORDER);
        final Text t5 = new Text(s, SWT.SINGLE | SWT.BORDER);
        final Text t6 = new Text(s, SWT.SINGLE | SWT.BORDER);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
