package cn.elwy.editor.ui.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;

import cn.elwy.editor.action.CopyTextAction;
import cn.elwy.editor.action.CutTextAction;

public class EditorActionGroup extends ActionGroup {

    private Text text;

    // 在Action要使用到TableViewer对象，所以通过构造函数把它传进来
    public EditorActionGroup(Text text) {
        this.text = text;
    }

    // 生成菜单Menu，并将两个Action传入
    public void fillContextMenu(IMenuManager mgr) {
        // 加入两个Action对象到菜单管理器

        MenuManager menuManager = (MenuManager) mgr;
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IAction undoAction = ActionFactory.UNDO.create(window);
        IAction redoAction = ActionFactory.REDO.create(window);
        menuManager.add(undoAction);
        menuManager.add(redoAction);
        menuManager.add(ActionFactory.SAVE.create(window));
        // 生成Menu并挂在表格table上。menu和table两个对象互为对方的参数
        Menu menu = menuManager.createContextMenu(text);
        text.setMenu(menu);

    }

}
