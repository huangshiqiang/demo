package chapter8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ComboExample2 {
    Display d;
    Shell s;

    ComboExample2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Combo Example");
        final Combo c1 = new Combo(s, SWT.READ_ONLY);
        c1.setBounds(50, 50, 150, 65);
        final Combo c2 = new Combo(s, SWT.READ_ONLY);
        c2.setBounds(50, 85, 150, 65);
        c2.setEnabled(false);
        String items[] = { "Item One", "Item Two", "Item Three", "Item Four", "Item Five" };
        c1.setItems(items);
        c1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (c1.getText().equals("Item One")) {
                    String newItems[] = { "Item One A", "Item One B", "Item One C" };
                    c2.setItems(newItems);
                    c2.setEnabled(true);
                } else if (c1.getText().equals("Item Two")) {
                    String newItems[] = { "Item Two A", "Item Two B", "Item Two C" };
                    c2.setItems(newItems);
                    c2.setEnabled(true);
                } else {
                    c2.add("Not Applicable");
                    c2.setText("Not Applicable");
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
