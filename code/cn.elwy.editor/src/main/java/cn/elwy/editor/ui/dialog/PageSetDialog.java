package cn.elwy.editor.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.custom.CLabel;
import com.swtdesigner.ResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PageSetDialog extends Dialog {
    private Text txtf;
    private Text txtp;
    private Text text_2;
    private Text text_3;
    private Text text_4;
    private Text text_5;
    private String[][] page = { { "A2", "images/pageA2.bmp", "images/pageA2h.bmp" },
            { "A3", "images/pageA3.bmp", "images/pageA3h.bmp" },
            { "A3 Extra", "images/pageA3e.bmp", "images/pageA3eh.bmp" },
            { "A3 Extra Transverse", "images/pageA3e.bmp", "images/pageA3eh.bmp" },
            { "A4", "images/pageA4.bmp", "images/pageA4h.bmp" },
            { "A4 Extra", "images/pageA4e.bmp", "images/pageA4eh.bmp" },
            { "A4 Extra Transverse", "images/pageA4e.bmp", "images/pageA4eh.bmp" },
            { "A5", "images/pageA5.bmp", "images/pageA5h.bmp" },
            { "A5 Extra", "images/pageA5e.bmp", "images/pageA5eh.bmp" },
            { "A5 Extra Transverse", "images/pageA5e.bmp", "images/pageA5eh.bmp" } };
    protected Combo combo;
    protected CLabel label;
    protected Button btnh, btnv;

    // private IWorkbenchWindow window;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public PageSetDialog(Shell parentShell) {
        super(parentShell);
        // this.window=window;
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout) container.getLayout();
        gridLayout.numColumns = 2;
        {
            Group group1 = new Group(container, SWT.NONE);
            group1.setText("\u7EB8\u5F20");
            {
                GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
                gridData.heightHint = 69;
                gridData.widthHint = 325;
                group1.setLayoutData(gridData);
            }
            {
                combo = new Combo(group1, SWT.NONE);
                combo.setItems(new String[] { "A2", "A3", "A3 Extra", "A3 Extra Transverse", "A4", "A4 Extra",
                        "A4 Extra Transverse", "A5", "A5 Extra", "A5 Extra Transverse" });
                combo.setBounds(83, 23, 238, 20);
                combo.select(4);
                combo.addModifyListener(new ModifyListener() {

                    @Override
                    public void modifyText(ModifyEvent e) {
                        // TODO Auto-generated method stub
                        for (int i = 0; i < page.length; i++) {
                            if (combo.getText().equals(page[i][0]))
                                if (btnh.getSelection())
                                    label.setImage(ResourceManager.getPluginImage("Editor", page[i][2]));
                                else
                                    label.setImage(ResourceManager.getPluginImage("Editor", page[i][1]));
                        }

                    }
                });

            }
            {
                Combo combo = new Combo(group1, SWT.NONE);
                combo.setItems(new String[] { "\u81EA\u52A8\u9009\u62E9" });
                combo.setBounds(83, 49, 238, 20);
                combo.select(0);
            }
            {
                Label lblz = new Label(group1, SWT.NONE);
                lblz.setBounds(10, 31, 54, 12);
                lblz.setText("\u5927\u5C0F(&Z):");
            }
            {
                Label lbls = new Label(group1, SWT.NONE);
                lbls.setBounds(10, 57, 54, 12);
                lbls.setText("\u6765\u6E90(&S):");
            }
        }
        {
            Group group2 = new Group(container, SWT.NONE);
            group2.setText("\u9884\u89C8");
            {
                GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
                gridData.heightHint = 268;
                gridData.widthHint = 149;
                group2.setLayoutData(gridData);
            }
            {
                label = new CLabel(group2, SWT.NONE);
                label.setAlignment(SWT.CENTER);
                label.setImage(ResourceManager.getPluginImage("Editor", "images/pageA4.bmp"));
                label.setBounds(10, 39, 135, 195);
                label.setText("");
            }
        }
        {
            Composite composite = new Composite(container, SWT.NONE);
            {
                GridData gridData = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
                gridData.widthHint = 332;
                gridData.heightHint = 123;
                composite.setLayoutData(gridData);
            }
            composite.setLayout(new RowLayout(SWT.HORIZONTAL));
            {
                Group group3 = new Group(composite, SWT.NONE);
                group3.setText("\u65B9\u5411");
                group3.setLayoutData(new RowData(100, 110));
                {
                    btnv = new Button(group3, SWT.RADIO);
                    btnv.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                        }
                    });
                    btnv.setSelection(true);
                    btnv.setBounds(18, 38, 75, 16);
                    btnv.setText("\u7EB5\u5411(&O)");
                }
                {
                    btnh = new Button(group3, SWT.RADIO);
                    btnh.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            if (btnh.getSelection())
                                for (int i = 0; i < page.length; i++) {
                                    if (combo.getText().equals(page[i][0]))
                                        label.setImage(ResourceManager.getPluginImage("Editor", page[i][2]));
                                }
                            else
                                for (int i = 0; i < page.length; i++) {
                                    if (combo.getText().equals(page[i][0]))
                                        label.setImage(ResourceManager.getPluginImage("Editor", page[i][1]));
                                }

                        }
                    });
                    btnh.setBounds(18, 77, 75, 16);
                    btnh.setText("\u6A2A\u5411(&A)");
                }
            }
            {
                Group group4 = new Group(composite, SWT.NONE);
                group4.setText("\u9875\u8FB9\u8DDD(\u6BEB\u7C73)");
                group4.setLayout(new GridLayout(4, false));
                group4.setLayoutData(new RowData(210, 110));
                {
                    Label lblt = new Label(group4, SWT.NONE);
                    {
                        GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
                        gridData.widthHint = 54;
                        lblt.setLayoutData(gridData);
                    }
                    lblt.setText("\u4E0A(&T):");
                }
                {
                    text_2 = new Text(group4, SWT.BORDER);
                    text_2.setText("25");
                    {
                        GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
                        gridData.widthHint = 140;
                        text_2.setLayoutData(gridData);
                    }
                }
                Label label = new Label(group4, SWT.NONE);
                label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
                {
                    Label lblb = new Label(group4, SWT.NONE);
                    lblb.setText("\u4E0B(&B):");
                }
                {
                    text_3 = new Text(group4, SWT.BORDER);
                    text_3.setText("25");
                    text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
                }
                Label label_1 = new Label(group4, SWT.NONE);
                label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
                {
                    Label lbll = new Label(group4, SWT.NONE);
                    lbll.setText("\u5DE6(&L):");
                }
                {
                    text_4 = new Text(group4, SWT.BORDER);
                    text_4.setText("20");
                    text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
                }
                Label label_2 = new Label(group4, SWT.NONE);
                label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
                {
                    Label lblr = new Label(group4, SWT.NONE);
                    lblr.setText("\u53F3(&R):");
                }
                {
                    text_5 = new Text(group4, SWT.BORDER);
                    text_5.setText("20");
                    text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
                }
                new Label(group4, SWT.NONE);
                new Label(group4, SWT.NONE);
                new Label(group4, SWT.NONE);
                new Label(group4, SWT.NONE);

                Label label_3 = new Label(group4, SWT.NONE);
                label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
            }
            {
                Composite composite_1 = new Composite(composite, SWT.NONE);
                composite_1.setLayout(new GridLayout(3, false));
                composite_1.setLayoutData(new RowData(323, 64));
                {
                    Label lblh = new Label(composite_1, SWT.NONE);
                    lblh.setText("\u6807\u5934(&H):");
                }
                Label label = new Label(composite_1, SWT.NONE);
                label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
                {
                    txtf = new Text(composite_1, SWT.BORDER);
                    txtf.setText("&f");
                    txtf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                }
                {
                    Label lblf = new Label(composite_1, SWT.NONE);
                    lblf.setText("\u9875\u811A(&F):");
                }
                Label label_2 = new Label(composite_1, SWT.NONE);
                label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
                {
                    txtp = new Text(composite_1, SWT.BORDER);
                    txtp.setText("\u7B2C&p\u9875");
                    txtp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                }
                new Label(composite_1, SWT.NONE);
                Label label_1 = new Label(composite_1, SWT.NONE);
                label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
                new Label(composite_1, SWT.NONE);
            }
        }

        return container;
    }

    @Override
    protected void okPressed() {
        // TODO Auto-generated method stub
        super.okPressed();
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        button.setText("\u786E\u5B9A");

        Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        button_1.setText("\u53D6\u6D88");
    }

    @Override
    protected void configureShell(Shell newShell) {
        // TODO Auto-generated method stub
        super.configureShell(newShell);
        newShell.setText("页面设置");

    }
}
