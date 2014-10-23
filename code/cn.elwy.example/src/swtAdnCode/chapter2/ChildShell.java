package chapter2;

import org.eclipse.swt.widgets.*;

public class ChildShell {

    public ChildShell(Shell parent) {
        Shell child = new Shell(parent);
        child.setSize(200, 200);
        child.open();
    }
}
