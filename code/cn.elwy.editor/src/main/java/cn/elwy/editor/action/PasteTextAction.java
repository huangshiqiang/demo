package cn.elwy.editor.action;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class PasteTextAction extends Action implements IWorkbenchWindowActionDelegate {
    public static final String ID = "Editor.PasteTextAddtion";
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

        IUndoableOperation operation = new ActionOperation(window.getShell(), 1);

        IWorkbench workbench = window.getWorkbench();

        IOperationHistory operationHistory = workbench.getOperationSupport().getOperationHistory();

        operationHistory = OperationHistoryFactory.getOperationHistory();

        IUndoContext undoContext = workbench.getOperationSupport().getUndoContext();

        operation.addContext(undoContext);

        try {
            operationHistory.execute(operation, null, null);
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub
    }
}
