package chapter3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class MenuShell4 {
    Display d;
    Shell s;

    MenuShell4() {
        d = new Display();
        s = new Shell(d);
        s.setSize(300, 300);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Menu Example");

        Menu m = new Menu(s, SWT.BAR);

        // create a file menu and add an exit item
        final MenuItem file = new MenuItem(m, SWT.CASCADE);
        file.setText("File");
        final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
        file.setMenu(filemenu);

        final MenuItem openItem = new MenuItem(filemenu, SWT.CASCADE);
        openItem.setText("Open");
        final Menu submenu = new Menu(s, SWT.DROP_DOWN);
        openItem.setMenu(submenu);
        final MenuItem childItem = new MenuItem(submenu, SWT.PUSH);
        childItem.setText("Child");
        final MenuItem dialogItem = new MenuItem(submenu, SWT.PUSH);
        dialogItem.setText("Dialog");

        final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
        final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
        exitItem.setText("Exit");

        // create an edit menu and add cut copy and paste items
        final MenuItem edit = new MenuItem(m, SWT.CASCADE);
        edit.setText("Edit");
        final Menu editmenu = new Menu(s, SWT.DROP_DOWN);
        edit.setMenu(editmenu);
        final MenuItem cutItem = new MenuItem(editmenu, SWT.PUSH);
        cutItem.setText("Cut");
        final MenuItem copyItem = new MenuItem(editmenu, SWT.PUSH);
        copyItem.setText("Copy");
        final MenuItem pasteItem = new MenuItem(editmenu, SWT.PUSH);
        pasteItem.setText("Paste");

        // create a Window menu and add Child item
        final MenuItem window = new MenuItem(m, SWT.CASCADE);
        window.setText("Window");
        final Menu windowmenu = new Menu(s, SWT.DROP_DOWN);
        window.setMenu(windowmenu);
        final MenuItem maxItem = new MenuItem(windowmenu, SWT.PUSH);
        maxItem.setText("Maximize");
        final MenuItem minItem = new MenuItem(windowmenu, SWT.PUSH);
        minItem.setText("Minimize");

        // create a Help menu and add an about item
        final MenuItem help = new MenuItem(m, SWT.CASCADE);
        help.setText("Help");
        final Menu helpmenu = new Menu(s, SWT.DROP_DOWN);
        help.setMenu(helpmenu);
        final MenuItem aboutItem = new MenuItem(helpmenu, SWT.PUSH);
        aboutItem.setText("About");

        s.setMenuBar(m);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
