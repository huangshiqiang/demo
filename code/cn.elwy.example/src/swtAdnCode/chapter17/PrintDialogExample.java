package chapter17;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.printing.*;

public class PrintDialogExample {
    Display d;
    Shell s;

    public static void main(String[] args) {
        new PrintDialogExample();
    }
    PrintDialogExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(400, 400);
//        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A PrintDialog Example");
        s.setLayout(new FillLayout(SWT.VERTICAL));
        final Text t = new Text(s, SWT.BORDER | SWT.MULTI);
        final Button b = new Button(s, SWT.PUSH | SWT.BORDER);
        b.setText("Print");
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                PrintDialog printDialog = new PrintDialog(s, SWT.NONE);
                printDialog.setText("Print");
                PrinterData printerData = printDialog.open();
                if (!(printerData == null)) {
                    Printer p = new Printer(printerData);
                    p.startJob("PrintJob");
                    p.startPage();
                    Rectangle trim = p.computeTrim(0, 0, 0, 0);
                    Point dpi = p.getDPI();
                    int leftMargin = dpi.x + trim.x;
                    int topMargin = dpi.y / 2 + trim.y;
                    GC gc = new GC(p);
                    Font font = gc.getFont();
                    String printText = t.getText();
                    Point extent = gc.stringExtent(printText);
                    gc.drawString(printText, leftMargin, topMargin + font.getFontData()[0].getHeight());
                    p.endPage();
                    gc.dispose();
                    p.endJob();
                    p.dispose();
                }

            }
        });
        s.open();

        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
