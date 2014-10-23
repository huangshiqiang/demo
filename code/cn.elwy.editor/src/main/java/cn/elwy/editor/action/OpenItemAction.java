package cn.elwy.editor.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import cn.elwy.editor.ui.view.OperationDatas;

public class OpenItemAction extends Action implements IWorkbenchWindowActionDelegate {
    private TableViewer tv;

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(IWorkbenchWindow window) {
        // TODO Auto-generated method stub

    }

    public OpenItemAction(TableViewer tv) {
        this.tv = tv;
        // this.setEnabled(false);
        setText("打开");
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub
        if (selection.isEmpty())
            action.setEnabled(false);
        else
            action.setEnabled(true);
    }

    @Override
    public void run(IAction action) {
        // TODO Auto-generated method stub
        IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
        OperationDatas o = (OperationDatas) selection.getFirstElement();
        if (o == null)
            MessageDialog.openInformation(null, null, "请先选择记录");
        else
            MessageDialog.openInformation(null, null, o.getFilename());
    }

}
