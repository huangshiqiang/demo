package org.eclipse.swt.examples.test;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

public class CommitWizardPage extends WizardPage {
    private Text text;
    private Text text_1;
    private Text text_2;
    private Text text_3;
    private Table table;

    /**
     * Create the wizard.
     */
    public CommitWizardPage() {
        super("wizardPage");
        setPageComplete(false);
        setImageDescriptor(null);
        setTitle("\u63D0\u4EA4");
        setDescription("Commit Wizard Page Description");
    }

    /**
     * Create contents of the wizard.
     * @param parent
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        setControl(container);

        SashForm sashForm = new SashForm(container, SWT.VERTICAL);
        sashForm.setBounds(0, 0, 855, 505);

        SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);

        SashForm sashForm_3 = new SashForm(sashForm_1, SWT.NONE);

        Group group = new Group(sashForm_3, SWT.NONE);

        Group group_1 = new Group(group, SWT.NONE);
        group_1.setBounds(10, 10, 406, 84);

        Label label = new Label(group_1, SWT.NONE);
        label.setText("Filter");
        label.setAlignment(SWT.CENTER);
        label.setBounds(10, 25, 61, 17);

        Label label_1 = new Label(group_1, SWT.NONE);
        label_1.setText("Filter");
        label_1.setAlignment(SWT.CENTER);
        label_1.setBounds(10, 57, 61, 17);

        Combo combo = new Combo(group_1, SWT.NONE);
        combo.setBounds(126, 17, 270, 25);

        Combo combo_1 = new Combo(group_1, SWT.NONE);
        combo_1.setBounds(126, 48, 270, 25);

        Group group_2 = new Group(group, SWT.NONE);
        group_2.setBounds(10, 100, 406, 235);

        Tree tree = new Tree(group_2, SWT.BORDER);
        tree.setBounds(0, 10, 406, 223);

        TreeItem treeItem = new TreeItem(tree, 0);
        treeItem.setText("New TreeItem");

        TreeItem treeItem_3 = new TreeItem(treeItem, 0);
        treeItem_3.setText("New TreeItem");
        treeItem.setExpanded(true);

        TreeItem treeItem_1 = new TreeItem(tree, 0);
        treeItem_1.setText("New TreeItem");
        treeItem_1.setExpanded(true);

        TreeItem treeItem_2 = new TreeItem(tree, 0);
        treeItem_2.setText("New TreeItem");

        TreeItem treeItem_5 = new TreeItem(treeItem_2, 0);
        treeItem_5.setText("New TreeItem");

        TreeItem treeItem_4 = new TreeItem(treeItem_2, 0);
        treeItem_4.setText("New TreeItem");
        treeItem_2.setExpanded(true);
        sashForm_3.setWeights(new int[] { 1 });

        SashForm sashForm_4 = new SashForm(sashForm_1, SWT.NONE);

        Group group_3 = new Group(sashForm_4, SWT.NONE);

        Group group_4 = new Group(group_3, SWT.NONE);
        group_4.setBounds(10, 10, 406, 110);

        Label label_2 = new Label(group_4, SWT.NONE);
        label_2.setText("Current Name :");
        label_2.setAlignment(SWT.CENTER);
        label_2.setBounds(10, 21, 92, 17);

        text_1 = new Text(group_4, SWT.BORDER);
        text_1.setBounds(142, 15, 254, 23);

        Label label_3 = new Label(group_4, SWT.NONE);
        label_3.setText("Current Name :");
        label_3.setAlignment(SWT.CENTER);
        label_3.setBounds(10, 50, 92, 17);

        text_2 = new Text(group_4, SWT.BORDER);
        text_2.setBounds(142, 44, 254, 23);

        Label label_4 = new Label(group_4, SWT.NONE);
        label_4.setText("Current Name :");
        label_4.setAlignment(SWT.CENTER);
        label_4.setBounds(10, 79, 92, 17);

        text_3 = new Text(group_4, SWT.BORDER);
        text_3.setBounds(142, 73, 254, 23);

        Group group_5 = new Group(group_3, SWT.NONE);
        group_5.setBounds(10, 121, 406, 43);

        Button button = new Button(group_5, SWT.NONE);
        button.setText("Over Write");
        button.setBounds(200, 10, 80, 27);

        Button button_1 = new Button(group_5, SWT.NONE);
        button_1.setText("Revert");
        button_1.setBounds(316, 10, 80, 27);

        Group group_6 = new Group(group_3, SWT.NONE);
        group_6.setBounds(10, 170, 406, 156);

        table = new Table(group_6, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(0, 10, 406, 154);

        TableColumn tableColumn = new TableColumn(table, SWT.NONE);
        tableColumn.setWidth(100);
        tableColumn.setText("New Column");

        TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
        tableColumn_1.setWidth(100);
        tableColumn_1.setText("New Column");

        TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
        tableColumn_2.setWidth(100);
        tableColumn_2.setText("New Column");
        sashForm_4.setWeights(new int[] { 1 });
        sashForm_1.setWeights(new int[] { 1, 1 });

        SashForm sashForm_2 = new SashForm(sashForm, SWT.NONE);

        TabFolder tabFolder = new TabFolder(sashForm_2, SWT.NONE);

        TabItem tabItem = new TabItem(tabFolder, 0);
        tabItem.setText("\u6CE8\u91CA");

        text = new Text(tabFolder, SWT.FILL | SWT.MULTI);
        tabItem.setControl(text);

        TabItem tabItem_1 = new TabItem(tabFolder, 0);
        tabItem_1.setText("\u6BD4\u8F83");

        TabItem tabItem_2 = new TabItem(tabFolder, 0);
        tabItem_2.setText("\u5408\u5E76");
        sashForm_2.setWeights(new int[] { 1 });
        sashForm.setWeights(new int[] { 335, 167 });
    }

}
