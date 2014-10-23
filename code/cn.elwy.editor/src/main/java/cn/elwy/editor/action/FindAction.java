package cn.elwy.editor.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import cn.elwy.editor.ui.dialog.FindDialog;

public class FindAction implements IWorkbenchWindowActionDelegate {
    private IWorkbenchWindow window;

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
        FindDialog dialog = new FindDialog(window.getShell(), window);
        dialog.open();
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

}
