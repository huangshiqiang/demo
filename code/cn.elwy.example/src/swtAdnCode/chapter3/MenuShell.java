package chapter3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class MenuShell {
    Display d;
    Shell s;

    MenuShell() {
        d = new Display();
        s = new Shell(d);
        s.setSize(500, 500);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Menu Example");

        Menu m = new Menu(s, SWT.BAR);
        s.setMenuBar(m);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
