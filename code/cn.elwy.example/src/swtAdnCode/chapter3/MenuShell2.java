package chapter3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class MenuShell2 {
    Display d;
    Shell s;

    MenuShell2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(500, 500);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Menu Example");

        Menu m = new Menu(s, SWT.BAR);

        MenuItem file = new MenuItem(m, SWT.CASCADE);
        file.setText("File");
        Menu filemenu = new Menu(s, SWT.DROP_DOWN);
        file.setMenu(filemenu);
        MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
        openItem.setText("Open");
        MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
        exitItem.setText("Exit");

        s.setMenuBar(m);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
