package chapter9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FormLayoutExample {
    Display d;
    Shell s;

    FormLayoutExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A FormLayout Example");
        s.setLayout(new FormLayout());

        final Label l1 = new Label(s, SWT.RIGHT);
        l1.setText("First Name");
        FormData fd = new FormData();
        fd.top = new FormAttachment(10, 10);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(30, 0);
        fd.right = new FormAttachment(40, 0);
        l1.setLayoutData(fd);

        final Label l2 = new Label(s, SWT.RIGHT);
        l2.setText("Last Name");
        fd = new FormData();
        fd.top = new FormAttachment(l1, 5);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(40, 0);
        fd.right = new FormAttachment(40, 0);
        l2.setLayoutData(fd);

        final Text t1 = new Text(s, SWT.BORDER | SWT.SINGLE);
        fd = new FormData();
        fd.top = new FormAttachment(l1, 0, SWT.TOP);
        fd.left = new FormAttachment(l1, 10);
        t1.setLayoutData(fd);

        final Text t2 = new Text(s, SWT.BORDER | SWT.SINGLE);
        fd = new FormData();
        fd.top = new FormAttachment(l2, 0, SWT.TOP);
        fd.left = new FormAttachment(l2, 10);
        t2.setLayoutData(fd);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
