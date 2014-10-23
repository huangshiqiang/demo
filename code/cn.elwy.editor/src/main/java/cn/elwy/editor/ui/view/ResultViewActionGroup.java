package cn.elwy.editor.ui.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionGroup;

public class ResultViewActionGroup extends ActionGroup {
    private TableViewer tv;

    // 在Action要使用到TableViewer对象，所以通过构造函数把它传进来
    public ResultViewActionGroup(TableViewer tv) {
        this.tv = tv;
    }

    // 生成菜单Menu，并将两个Action传入
    public void fillContextMenu(IMenuManager mgr) {
        // 加入两个Action对象到菜单管理器
        MenuManager menuManager = (MenuManager) mgr;
        menuManager.add(new OpenAction());
        menuManager.add(new ClearAction());
        // 生成Menu并挂在表格table上。menu和table两个对象互为对方的参数
        Table table = tv.getTable();
        Menu menu = menuManager.createContextMenu(table);
        table.setMenu(menu);

    }

    // “打开”的Action类
    private class OpenAction extends Action {
        public OpenAction() {
            setText("打开");
        }

        public void run() {// 继承自Action的方法，动作代码写在此方法中
            IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
            OperationDatas o = (OperationDatas) selection.getFirstElement();
            if (o == null)
                MessageDialog.openInformation(null, null, "请先选择记录");
            else {
                MessageDialog.openInformation(null, o.getFilename(),
                        "对文件\"" + o.getFilename() + "\"进行操作：" + o.getOperated());
            }
        }
    }

    // “清空”的Action类
    private class ClearAction extends Action {
        public ClearAction() {
            setText("清空");
        }

        public void run() {
            DetailView.data.getFactoryData().clear();

            DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .findView(DetailView.ID);

            view.getTv().refresh();
        }
    }
}
