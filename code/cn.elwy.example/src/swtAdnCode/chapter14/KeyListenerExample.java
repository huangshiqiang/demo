package chapter14;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class KeyListenerExample {

    Display d;
    Shell s;

    KeyListenerExample() {

        d = new Display();
        s = new Shell(d);

        s.setSize(250, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A KeyListener Example");
        s.setLayout(new RowLayout());

        final Combo c = new Combo(s, SWT.DROP_DOWN | SWT.BORDER);
        c.add("Lions");
        c.add("Tigers");
        c.add("Bears");
        c.add("Oh My!");

        c.addKeyListener(new KeyListener() {
            String selectedItem = "";

            public void keyPressed(KeyEvent e) {
                if (c.getText().length() > 0) {
                    return;
                }
                String key = Character.toString(e.character);
                String[] items = c.getItems();
                for (int i = 0; i < items.length; i++) {
                    if (items[i].toLowerCase().startsWith(key.toLowerCase())) {
                        c.select(i);
                        selectedItem = items[i];
                        return;
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                if (selectedItem.length() > 0)
                    c.setText(selectedItem);
                selectedItem = "";
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
