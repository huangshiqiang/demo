package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class CompositeShellExample2 {
    Display d;
    Shell s;

    CompositeShellExample2() {
        d = new Display();
        s = new Shell(d);
        GridLayout gl = new GridLayout();
        gl.numColumns = 4;
        s.setLayout(gl);
        s.setSize(250, 275);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Composite Example");
        s.setLayout(gl);
        GridComposite gc = new GridComposite(s);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 4;
        gc.setLayoutData(gd);
        gd = new GridData();

        Composite c1 = new Composite(s, SWT.NO_FOCUS);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        c1.setLayoutData(gd);
        Composite c2 = new Composite(s, SWT.NO_FOCUS);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        c2.setLayoutData(gd);

        Composite c = new Composite(s, SWT.NO_FOCUS);
        c.setLayout(new RowLayout());
        Button b1 = new Button(c, SWT.PUSH | SWT.BORDER);
        b1.setText("OK");
        Button b2 = new Button(c, SWT.PUSH | SWT.BORDER);
        b2.setText("Cancel");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        c.setLayoutData(gd);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
