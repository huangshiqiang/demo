package cn.elwy.editor.ui.view;

import java.io.File;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionGroup;

import cn.elwy.editor.action.DeleteFileUtil;
import cn.elwy.editor.ui.editor.EditorInput;

public class FileViewActionGroup extends ActionGroup {
    private TreeViewer tv;

    // 在Action要使用到TableViewer对象，所以通过构造函数把它传进来
    public FileViewActionGroup(TreeViewer tv) {
        this.tv = tv;
    }

    // 生成菜单Menu，并将两个Action传入
    public void fillContextMenu(IMenuManager mgr) {
        // 加入两个Action对象到菜单管理器
        MenuManager menuManager = (MenuManager) mgr;
        menuManager.add(new OpenAction());
        menuManager.add(new RefreshAction());
        menuManager.add(new DeleteAction());
        // 生成Menu并挂在表格table上。menu和table两个对象互为对方的参数
        Tree tree = tv.getTree();
        Menu menu = menuManager.createContextMenu(tree);
        tree.setMenu(menu);

    }

    private class OpenAction extends Action {
        public OpenAction() {
            setText("打开");
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            IStructuredSelection strsel = (IStructuredSelection) tv.getSelection();

            String filepath = strsel.getFirstElement().toString();

            File file = new File(filepath);
            if (!file.isDirectory()) {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    page.openEditor(createEditorInput(file), "Editor.Editor");
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
                DetailView.data.setFactoryDate(file.getName(), "打开文件");
                DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(DetailView.ID);
                view.getTv().refresh();
            } else {

                Object obj = strsel.getFirstElement();
                tv.expandToLevel(obj, 1);

            }
        }
    }

    private class DeleteAction extends Action {

        public DeleteAction() {
            setText("删除");
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {

            IStructuredSelection strsel = (IStructuredSelection) tv.getSelection();

            String filepath = strsel.getFirstElement().toString();
            File file = new File(filepath);
            MessageBox msgBox = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
            msgBox.setText("删除文件");
            msgBox.setMessage("确定要删除(无法恢复！)" + "\"" + filepath + "\"");
            if (msgBox.open() == SWT.YES) {
                DeleteFileUtil.delete(filepath);
                tv.refresh();
            }

        }

    }

    private class RefreshAction extends Action {
        public RefreshAction() {
            setText("刷新");
        }

        public void run() {
            tv.refresh(); // 树的刷新方法，界面会重新读取数据并显示
        }
    }

    private EditorInput createEditorInput(File file) {
        EditorInput input = new EditorInput(file);
        return input;
    }
}
