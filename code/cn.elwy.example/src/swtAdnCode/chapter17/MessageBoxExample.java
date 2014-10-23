package chapter17;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class MessageBoxExample {

    Display d;
    Shell s;

    MessageBoxExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(400, 400);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A MessageBox Example");
        // create the menu system
        Menu m = new Menu(s, SWT.BAR);
        // create a file menu and add an exit item
        final MenuItem file = new MenuItem(m, SWT.CASCADE);
        file.setText("&File");
        final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
        file.setMenu(filemenu);
        final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
        exitItem.setText("E&xit");
        exitItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                MessageBox messageBox = new MessageBox(s, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
                messageBox.setMessage("Do you really want to exit?");
                messageBox.setText("Exiting Application");
                int response = messageBox.open();
                if (response == SWT.YES)
                    System.exit(0);
            }
        });
        s.setMenuBar(m);
        s.open();

        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
