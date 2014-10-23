package org.eclipse.swt.examples.test;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class CommitDialog extends Dialog {

    protected Object result;
    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Table table;
    private Text text_3;

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public CommitDialog(Shell parent, int style) {
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
        shell.setSize(872, 543);
        shell.setText("\u63D0\u4EA4");
        shell.setLayout(new GridLayout(1, false));

        SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);
        sashForm_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        SashForm sashForm_3 = new SashForm(sashForm_1, SWT.HORIZONTAL);
        sashForm_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Group group = new Group(sashForm_3, SWT.NONE);

        Group group_2 = new Group(group, SWT.NONE);
        group_2.setBounds(10, 10, 406, 84);

        Label lblFilter = new Label(group_2, SWT.NONE);
        lblFilter.setAlignment(SWT.CENTER);
        lblFilter.setBounds(10, 25, 61, 17);
        lblFilter.setText("Filter");

        Label label = new Label(group_2, SWT.NONE);
        label.setText("Filter");
        label.setAlignment(SWT.CENTER);
        label.setBounds(10, 57, 61, 17);

        Combo combo = new Combo(group_2, SWT.NONE);
        combo.setBounds(126, 17, 270, 25);

        Combo combo_1 = new Combo(group_2, SWT.NONE);
        combo_1.setBounds(126, 48, 270, 25);

        Group group_3 = new Group(group, SWT.NONE);
        group_3.setBounds(10, 100, 406, 176);

        Tree tree = new Tree(group_3, SWT.BORDER);
        tree.setBounds(0, 10, 396, 156);

        TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
        trtmNewTreeitem.setText("New TreeItem");

        TreeItem trtmNewTreeitem_2 = new TreeItem(trtmNewTreeitem, SWT.NONE);
        trtmNewTreeitem_2.setText("New TreeItem");
        trtmNewTreeitem.setExpanded(true);

        TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
        trtmNewTreeitem_1.setText("New TreeItem");

        TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem_1, SWT.NONE);
        trtmNewTreeitem_3.setText("New TreeItem");
        trtmNewTreeitem_1.setExpanded(true);

        TreeItem treeItem = new TreeItem(tree, 0);
        treeItem.setText("New TreeItem");

        SashForm sashForm_4 = new SashForm(sashForm_1, SWT.NONE);

        Group group_1 = new Group(sashForm_4, SWT.NONE);

        Group group_4 = new Group(group_1, SWT.NONE);
        group_4.setBounds(10, 10, 406, 110);

        Label lblCurrentName = new Label(group_4, SWT.NONE);
        lblCurrentName.setAlignment(SWT.CENTER);
        lblCurrentName.setBounds(10, 21, 92, 17);
        lblCurrentName.setText("Current Name :");

        text = new Text(group_4, SWT.BORDER);
        text.setBounds(142, 15, 254, 23);

        Label label_1 = new Label(group_4, SWT.NONE);
        label_1.setText("Current Name :");
        label_1.setAlignment(SWT.CENTER);
        label_1.setBounds(10, 50, 92, 17);

        text_1 = new Text(group_4, SWT.BORDER);
        text_1.setBounds(142, 44, 254, 23);

        Label label_2 = new Label(group_4, SWT.NONE);
        label_2.setText("Current Name :");
        label_2.setAlignment(SWT.CENTER);
        label_2.setBounds(10, 79, 92, 17);

        text_2 = new Text(group_4, SWT.BORDER);
        text_2.setBounds(142, 73, 254, 23);

        Group group_5 = new Group(group_1, SWT.NONE);
        group_5.setBounds(10, 112, 406, 42);

        Button btnNewButton = new Button(group_5, SWT.NONE);
        btnNewButton.setBounds(200, 10, 80, 27);
        btnNewButton.setText("Over Write");

        Button btnRevert = new Button(group_5, SWT.NONE);
        btnRevert.setText("Revert");
        btnRevert.setBounds(316, 10, 80, 27);

        Group group_6 = new Group(group_1, SWT.NONE);
        group_6.setBounds(10, 142, 406, 134);

        table = new Table(group_6, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(0, 10, 406, 124);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("New Column");

        TableColumn tableColumn = new TableColumn(table, SWT.NONE);
        tableColumn.setWidth(100);
        tableColumn.setText("New Column");

        TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
        tableColumn_1.setWidth(100);
        tableColumn_1.setText("New Column");

        TableItem tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(new String[] { "1", "1", "1" });

        TableItem tableItem_1 = new TableItem(table, 0);
        tableItem_1.setText(new String[] { "2", "2", "2" });

        TableItem tableItem_2 = new TableItem(table, 0);
        tableItem_2.setText(new String[] { "3", "3", "3" });
        sashForm_1.setWeights(new int[] { 1, 1 });

        SashForm sashForm_2 = new SashForm(sashForm, SWT.NONE);

        TabFolder tabFolder = new TabFolder(sashForm_2, SWT.NONE);
        tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
        tbtmNewItem.setText("\u6CE8\u91CA");

        text_3 = new Text(tabFolder, SWT.BORDER | SWT.MULTI);
        tbtmNewItem.setControl(text_3);

        TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
        tbtmNewItem_1.setText("\u6BD4\u8F83");

        TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
        tbtmNewItem_2.setText("\u5408\u5E76");
        sashForm_2.setWeights(new int[] { 1 });
        sashForm.setWeights(new int[] { 287, 215 });

    }
}
