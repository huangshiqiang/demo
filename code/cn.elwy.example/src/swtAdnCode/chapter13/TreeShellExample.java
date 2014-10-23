package chapter13;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class TreeShellExample {
    Display d;
    Shell s;

    TreeShellExample() {
        d = new Display();
        s = new Shell(d);

        s.setSize(250, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Table Shell Example");
        s.setLayout(new FillLayout());
        Tree t = new Tree(s, SWT.SINGLE | SWT.BORDER);
        TreeItem child1 = new TreeItem(t, SWT.NONE, 0);
        child1.setText("1");
        TreeItem child2 = new TreeItem(t, SWT.NONE, 1);
        child2.setText("2");
        TreeItem child2a = new TreeItem(child2, SWT.NONE, 0);
        child2a.setText("2A");
        TreeItem child2b = new TreeItem(child2, SWT.NONE, 1);
        child2b.setText("2B");
        TreeItem child3 = new TreeItem(t, SWT.NONE, 2);
        child3.setText("3");
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
