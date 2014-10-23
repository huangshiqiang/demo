package cn.elwy.editor.action;

import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

@SuppressWarnings("unused")
public class CopyTextAction extends Action implements IWorkbenchWindowActionDelegate {
    public static final String ID = "Editor.CopyTextAddtion";

    private IWorkbenchWindow window;

    private FileEditor editor;

    public CopyTextAction() {
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(IWorkbenchWindow window) {
        // TODO Auto-generated method stub
        this.window = window;
    }

    @Override
    public void run(IAction action) {
        // TODO Auto-generated method stub
        FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();

        editor.getText().copy();

        DetailView.data.setFactoryDate(editor.getPartName(), "复制");
        DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView(DetailView.ID);
        view.getTv().refresh();
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }
}
