package cn.elwy.editor.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.editor.EditorInput;
import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class NewFileAction extends Action implements IWorkbenchWindowActionDelegate {
    private IWorkbenchWindow Ifwindow;

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(IWorkbenchWindow window) {
        // TODO Auto-generated method stub
        this.Ifwindow = window;
    }

    @Override
    public void run(IAction action) {
        // TODO Auto-generated method stub
        try {

            Ifwindow.getActivePage().openEditor(new EditorInput(), FileEditor.ID);
            DetailView.data.setFactoryDate("text", "新建");
            DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .findView(DetailView.ID);
            view.getTv().refresh();
        } catch (PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

}
