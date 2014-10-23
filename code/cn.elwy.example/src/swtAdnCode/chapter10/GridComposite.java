package chapter10;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class GridComposite extends Composite {

    public GridComposite(Composite c) {
        super(c, SWT.BORDER);
        GridLayout gl = new GridLayout();
        gl.numColumns = 3;
        this.setLayout(gl);
        final Label l1 = new Label(this, SWT.BORDER);
        l1.setText("Column One");
        final Label l2 = new Label(this, SWT.BORDER);
        l2.setText("Column Two");
        final Label l3 = new Label(this, SWT.BORDER);
        l3.setText("Column Three");
        final Text t1 = new Text(this, SWT.SINGLE | SWT.BORDER);
        final Text t2 = new Text(this, SWT.SINGLE | SWT.BORDER);
        final Text t3 = new Text(this, SWT.SINGLE | SWT.BORDER);
        final Text t4 = new Text(this, SWT.SINGLE | SWT.BORDER);
        final Text t5 = new Text(this, SWT.SINGLE | SWT.BORDER);
        final Text t6 = new Text(this, SWT.SINGLE | SWT.BORDER);

        GridData gd = new GridData();
        gd.horizontalAlignment = GridData.CENTER;
        l1.setLayoutData(gd);

        gd = new GridData();
        gd.horizontalAlignment = GridData.CENTER;
        l2.setLayoutData(gd);

        gd = new GridData();
        gd.horizontalAlignment = GridData.CENTER;
        l3.setLayoutData(gd);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        t1.setLayoutData(gd);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        t2.setLayoutData(gd);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        t3.setLayoutData(gd);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        t4.setLayoutData(gd);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        t5.setLayoutData(gd);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        t6.setLayoutData(gd);
    }

}
