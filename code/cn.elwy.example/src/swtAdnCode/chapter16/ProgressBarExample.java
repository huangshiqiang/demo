package chapter16;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ProgressBarExample {
    Display d;
    Shell s;

    ProgressBarExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A ProgressBar Example");

        final ProgressBar pb = new ProgressBar(s, SWT.HORIZONTAL);
        pb.setMinimum(0);
        pb.setMaximum(100);
        pb.setSelection(50);
        pb.setBounds(10, 10, 200, 20);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
