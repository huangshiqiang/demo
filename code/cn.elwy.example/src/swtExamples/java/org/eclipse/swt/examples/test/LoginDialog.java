package org.eclipse.swt.examples.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Link;

public class LoginDialog extends Dialog {

    protected Object result;
    protected Shell shell;
    private Text text;
    private Text text_1;

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public LoginDialog(Shell parent, int style) {
        super(parent, style);
        setText("SWT Dialog");
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public Object open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return result;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), getStyle());
        shell.setSize(480, 320);
        shell.setText("\u767B\u5F55\u7A97\u4F53");
        shell.setLayout(new GridLayout(1, false));

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Group group = new Group(composite, SWT.NONE);
        group.setBounds(0, 0, 464, 56);

        Label lblNewLabel = new Label(group, SWT.NONE);
        lblNewLabel.setFont(SWTResourceManager.getFont("΢���ź�", 16, SWT.NORMAL));
        lblNewLabel.setAlignment(SWT.CENTER);
        lblNewLabel.setBounds(100, 10, 250, 36);
        lblNewLabel.setText("\u6B22\u8FCE\u767B\u5F55EAM\u7CFB\u7EDF");

        Group group_1 = new Group(composite, SWT.NONE);
        group_1.setBounds(0, 62, 464, 220);

        Group group_2 = new Group(group_1, SWT.NONE);
        group_2.setLocation(0, 10);
        group_2.setSize(320, 200);

        Group group_4 = new Group(group_2, SWT.NONE);
        group_4.setBounds(0, 0, 320, 133);

        Label label = new Label(group_4, SWT.NONE);
        label.setAlignment(SWT.CENTER);
        label.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
        label.setBounds(10, 24, 88, 23);
        label.setText("\u7528\u6237\u540D:");

        text = new Text(group_4, SWT.BORDER);
        text.setBounds(104, 24, 206, 23);

        Label label_1 = new Label(group_4, SWT.NONE);
        label_1.setText("\u5BC6   \u7801:");
        label_1.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
        label_1.setAlignment(SWT.CENTER);
        label_1.setBounds(10, 73, 88, 23);

        text_1 = new Text(group_4, SWT.BORDER | SWT.PASSWORD);
        text_1.setBounds(104, 73, 206, 23);

        Button button = new Button(group_2, SWT.NONE);
        button.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
        button.setBounds(82, 150, 80, 27);
        button.setText("\u91CD\u7F6E");

        Button button_1 = new Button(group_2, SWT.NONE);
        button_1.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
        button_1.setText("\u767B\u5F55");
        button_1.setBounds(230, 150, 80, 27);

        Group group_3 = new Group(group_1, SWT.NONE);
        group_3.setBounds(319, 10, 145, 200);

        Label lblNewLabel_1 = new Label(group_3, SWT.NONE);
        lblNewLabel_1.setFont(SWTResourceManager.getFont("΢���ź�", 10, SWT.NORMAL));
        lblNewLabel_1.setBounds(28, 106, 61, 17);
        lblNewLabel_1.setText("\u6CA1\u6709\u5E10\u53F7?");

        Link link = new Link(group_3, SWT.NONE);
        link.setFont(SWTResourceManager.getFont("΢���ź�", 10, SWT.NORMAL));
        link.setBounds(95, 106, 40, 17);
        link.setText("<a>\u6CE8\u518C</a>");

        Link link_1 = new Link(group_3, 0);
        link_1.setText("<a>\u6CE8\u518C\u670D\u52A1\u5668</a>");
        link_1.setFont(SWTResourceManager.getFont("΢���ź�", 10, SWT.NORMAL));
        link_1.setBounds(10, 158, 65, 17);

        Link link_2 = new Link(group_3, 0);
        link_2.setText("<a>\u627E\u56DE\u5BC6\u7801</a>");
        link_2.setFont(SWTResourceManager.getFont("΢���ź�", 10, SWT.NORMAL));
        link_2.setBounds(80, 158, 55, 17);

    }
}
