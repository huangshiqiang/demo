package cn.elwy.editor.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.internal.dialogs.AboutDialog;

@SuppressWarnings("restriction")
public class AboutAction implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow workbenchWindow;

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(IWorkbenchWindow window) {
        // TODO Auto-generated method stub
        workbenchWindow = window;
    }

    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void run(IAction action) {
        // TODO Auto-generated method stub
        new AboutDialog(workbenchWindow.getShell()).open();
    }

}
