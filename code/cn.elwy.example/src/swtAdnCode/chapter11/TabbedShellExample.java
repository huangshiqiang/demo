package chapter11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import chapter10.GridComposite;
import chapter10.GroupExample;

public class TabbedShellExample {

    Display d;
    Shell s;

    TabbedShellExample() {
        d = new Display();
        s = new Shell(d);

        s.setSize(250, 200);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Tabbed Shell Example");
        s.setLayout(new FillLayout());

        TabFolder tf = new TabFolder(s, SWT.BORDER);

        TabItem ti1 = new TabItem(tf, SWT.BORDER);
        ti1.setText("Option Group");
        ti1.setControl(new GroupExample(tf, SWT.SHADOW_ETCHED_IN));

        TabItem ti2 = new TabItem(tf, SWT.BORDER);
        ti2.setText("Grid");
        ti2.setControl(new GridComposite(tf));

        TabItem ti3 = new TabItem(tf, SWT.BORDER);
        ti3.setText("Text");
        Composite c1 = new Composite(tf, SWT.BORDER);
        c1.setLayout(new FillLayout());
        Text t = new Text(c1, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        ti3.setControl(c1);

        TabItem ti4 = new TabItem(tf, SWT.BORDER);
        ti4.setText("Settings");
        Composite c2 = new Composite(tf, SWT.BORDER);
        c2.setLayout(new RowLayout());
        Text t2 = new Text(c2, SWT.BORDER | SWT.SINGLE | SWT.WRAP | SWT.V_SCROLL);
        Button b = new Button(c2, SWT.PUSH | SWT.BORDER);
        b.setText("Save");
        ti4.setControl(c2);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
