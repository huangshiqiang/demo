package cn.elwy.editor.ui.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class DetailView extends ViewPart {

    private TableViewer tv;
    private Table table;
    public static final String ID = "cn.elwy.editor.ui.view.DetailView";

    public TableViewer getTv() {
        return tv;
    }

    public void setTv(TableViewer tv) {
        this.tv = tv;
    }

    public static DataFactory data = new DataFactory();

    public DetailView() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent) {
        createTableViewer(parent);

        tv.setLabelProvider(new TableViewLabelProvider());

        tv.setContentProvider(new TableViewerContentProvider());

        tv.setInput(data.getFactoryData());

        ResultViewActionGroup actionGroup = new ResultViewActionGroup(tv);

        actionGroup.fillContextMenu(new MenuManager());

        getSite().setSelectionProvider(tv);
    }

    public void settext() {
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }

    public void createTableViewer(Composite prarent) {
        tv = new TableViewer(prarent, SWT.MULTI | SWT.FULL_SELECTION);
        table = tv.getTable();
        TableColumn c1 = new TableColumn(table, SWT.LEFT);
        c1.setText("文件名");
        c1.setWidth(200);
        TableColumn c2 = new TableColumn(table, SWT.LEFT);
        c2.setText("时间");
        c2.setWidth(200);
        TableColumn c3 = new TableColumn(table, SWT.LEFT);
        c3.setText("操作");
        c3.setWidth(300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }
}
