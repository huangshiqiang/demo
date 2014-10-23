package chapter7;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ListExample3 {

    Display d;
    Shell s;

    ListExample3() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A List Example");
        final List l = new List(s, SWT.MULTI | SWT.BORDER);
        l.setBounds(50, 50, 75, 75);
        l.add("Item One");
        l.add("Item Two");
        l.add("Item Three");
        l.add("Item Four");
        l.add("Item Five");
        final Button b1 = new Button(s, SWT.PUSH | SWT.BORDER);
        b1.setBounds(150, 150, 50, 25);
        b1.setText("Click Me");
        b1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String selected[] = l.getSelection();
                for (int i = 0; i < selected.length; i++) {
                    System.out.println(selected[i]);
                }
            }
        });
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
