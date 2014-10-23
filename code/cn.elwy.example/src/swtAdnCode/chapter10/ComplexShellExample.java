package chapter10;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ComplexShellExample {
    Display d;
    Shell s;

    ComplexShellExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 275);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Composite Example");

        GridLayout gl = new GridLayout();
        gl.numColumns = 4;
        s.setLayout(gl);
        s.setLayout(gl);

        GridComposite gc = new GridComposite(s);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 4;
        gc.setLayoutData(gd);
        gd = new GridData();

        RowComposite rc = new RowComposite(s);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        rc.setLayoutData(gd);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
