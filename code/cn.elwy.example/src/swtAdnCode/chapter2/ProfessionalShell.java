package chapter2;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ProfessionalShell {

    ProfessionalShell() {
        Display d = new Display();
        Shell s = new Shell(d);
        s.setSize(500, 500);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Example");
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
