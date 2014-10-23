package cn.elwy.example.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class 表格复选框 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Display display = new Display();
            Shell shell = new Shell(display);

            GridLayout gridLayout7 = new GridLayout();
            gridLayout7.horizontalSpacing = 0; // Generated
            gridLayout7.marginWidth = 0; // Generated
            gridLayout7.marginHeight = 0; // Generated
            gridLayout7.verticalSpacing = 0; // Generated

            if (shell == null)
                shell = new Shell();

            shell.setText("Test");
            shell.setFont(new Font(Display.getDefault(), "\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 9, SWT.NORMAL));

            shell.setBackground(new Color(Display.getCurrent(), 254, 250, 232));
            shell.setLayout(gridLayout7); // Generated
            shell.setSize(new Point(854, 600));
            shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

            GridLayout gridLayout32 = new GridLayout();
            gridLayout32.marginWidth = 10; // Generated
            GridData gridData11 = new GridData();
            gridData11.horizontalAlignment = GridData.FILL;
            gridData11.grabExcessHorizontalSpace = true;
            gridData11.grabExcessVerticalSpace = true;
            gridData11.verticalAlignment = GridData.FILL;
            GridData gridData10 = new GridData();
            gridData10.horizontalAlignment = GridData.FILL;
            gridData10.grabExcessHorizontalSpace = true;
            gridData10.grabExcessVerticalSpace = true;
            gridData10.verticalAlignment = GridData.FILL;
            Composite composite5 = new Composite(shell, SWT.NONE);
            composite5.setLayoutData(gridData11);
            composite5.setLayout(gridLayout32); // Generated
            composite5.setFont(new Font(Display.getDefault(), "\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 9, SWT.NORMAL));
            final Table table = new Table(composite5, SWT.BORDER | SWT.FULL_SELECTION);
            table.setHeaderVisible(true);
            table.setFont(new Font(Display.getDefault(), "\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 9, SWT.NORMAL));
            table.setLayoutData(gridData10);
            table.setLinesVisible(true);

            TableColumn tableColumn0 = new TableColumn(table, SWT.CENTER);
            tableColumn0.setWidth(50); // Generated

            TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
            tableColumn1.setWidth(60); // Generated
            tableColumn1.setText("1"); // Generated

            TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
            tableColumn2.setWidth(60); // Generated
            tableColumn2.setText("2"); // Generated

            TableColumn tableColumn3 = new TableColumn(table, SWT.NONE);
            tableColumn3.setWidth(140); // Generated
            tableColumn3.setText("3"); // Generated

            TableColumn tableColumn4 = new TableColumn(table, SWT.NONE);
            tableColumn4.setWidth(250); // Generated
            tableColumn4.setText("4"); // Generated

            TableColumn tableColumn5 = new TableColumn(table, SWT.NONE);
            tableColumn5.setWidth(250); // Generated
            tableColumn5.setText("5"); // Generated

            TableColumn tableColumn51 = new TableColumn(table, SWT.RIGHT);
            tableColumn51.setWidth(120); // Generated
            tableColumn51.setText("6"); // Generated

            TableColumn tableColumn6 = new TableColumn(table, SWT.NONE);
            tableColumn6.setWidth(80); // Generated
            tableColumn6.setText("7"); // Generated

            
            TableItem ti = new TableItem(table, SWT.NONE);
            TableEditor teApproval = new TableEditor(table);
            teApproval.grabHorizontal = true;
            // chkboxApp.setBackground(Const.WHITE_COLOR);

//            Button chkboxApp = new Button(table, SWT.CHECK | SWT.LEFT);
//            chkboxApp.setText("00");
//            teApproval.setEditor(chkboxApp, ti, 0);

            Button chkboxApp2 = new Button(table, SWT.CHECK | SWT.LEFT);
            teApproval.setEditor(chkboxApp2, ti, 1);
            chkboxApp2.setText("111");

            Button chkboxApp3 = new Button(table, SWT.CHECK | SWT.LEFT);
            teApproval.setEditor(chkboxApp3, ti, 2);
            chkboxApp3.setText("222");

            ti.setText(3, "sss");
            ti.setText(4, "sss");
            ti.setText(5, "sss");
            ti.setText(6, "sss");
            ti.setText(7, "sss");
            
            
            
            GridLayout gridLayoutBottom = new GridLayout();
            gridLayoutBottom.marginWidth = 10; // Generated
            GridData gridDataBottom = new GridData();
            gridDataBottom.horizontalAlignment = GridData.FILL;
            gridDataBottom.grabExcessHorizontalSpace = true;
            gridDataBottom.grabExcessVerticalSpace = true;
            gridDataBottom.verticalAlignment = GridData.FILL;
            GridData gridDataBotto = new GridData();
            gridDataBotto.horizontalAlignment = GridData.FILL;
            gridDataBotto.grabExcessHorizontalSpace = true;
            gridDataBotto.grabExcessVerticalSpace = true;
            gridDataBotto.verticalAlignment = GridData.FILL;
            Composite compositeBottom = new Composite(shell, SWT.NONE);
            compositeBottom.setLayoutData(gridDataBottom);
            compositeBottom.setLayout(gridLayoutBottom); // Generated
            compositeBottom.setFont(new Font(Display.getDefault(), "\uff2d\uff33 \u30b4\u30b7\u30c3\u30af", 9,
                    SWT.NORMAL));
            Button buttonBottom = new Button(compositeBottom, SWT.NONE);
            buttonBottom.setText("add");
            buttonBottom.addMouseListener(new MouseListener() {
                public void mouseDoubleClick(MouseEvent e) {

                }

                public void mouseDown(MouseEvent e) {

                }

                public void mouseUp(MouseEvent e) {

                    TableItem ti = new TableItem(table, SWT.NONE);

                    TableEditor teApproval = new TableEditor(table);
                    teApproval.grabHorizontal = true;
                    // chkboxApp.setBackground(Const.WHITE_COLOR);

//                    Button chkboxApp = new Button(table, SWT.CHECK | SWT.LEFT);
//                    chkboxApp.setText("00");
//                    teApproval.setEditor(chkboxApp, ti, 0);

                    Button chkboxApp2 = new Button(table, SWT.CHECK | SWT.LEFT);
                    teApproval.setEditor(chkboxApp2, ti, 1);
                    chkboxApp2.setText("111");

                    Button chkboxApp3 = new Button(table, SWT.CHECK | SWT.LEFT);
                    teApproval.setEditor(chkboxApp3, ti, 2);
                    chkboxApp3.setText("222");

                    ti.setText(3, "sss");
                    ti.setText(4, "sss");
                    ti.setText(5, "sss");
                    ti.setText(6, "sss");
                    ti.setText(7, "sss");
                }
            });

            shell.open();

            while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }

            display.dispose();
        } catch (Exception e) {

        }
    }

}