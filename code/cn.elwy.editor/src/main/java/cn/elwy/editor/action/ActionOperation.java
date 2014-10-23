package cn.elwy.editor.action;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.dialog.StackItem;
import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class ActionOperation extends AbstractOperation {

    private int type;

    public ActionOperation(Shell shell, int type) {

        super("Cut/Paste Operation");

        this.type = type;

    }

    @Override
    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        FileEditor editor = (FileEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        switch (this.type) {

        case 0:
            Cut(editor);
            break;

        case 1:
            Paste(editor);
            break;

        default:
            break;
        }
        return Status.OK_STATUS;
    }

    /**
     * 粘贴
     * @param editor
     */
    private void Paste(FileEditor editor) {
        // TODO Auto-generated method stub
        editor.getText().paste();

        Clipboard clipboard = new Clipboard(PlatformUI.getWorkbench().getDisplay());

        TextTransfer transfer = TextTransfer.getInstance();

        String temp = (String) clipboard.getContents(transfer);

        int tempPosition = editor.getText().getCaretPosition();

        StackItem item = new StackItem(temp, tempPosition, this.type);

        editor.stack.push(item);

        DetailView.data.setFactoryDate(editor.getPartName(), "粘贴");
        DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView(DetailView.ID);
        view.getTv().refresh();

    }

    /**
     * 剪切
     * @param editor
     */
    private void Cut(FileEditor editor) {
        // TODO Auto-generated method stub
        String temp = editor.getText().getSelectionText();

        editor.getText().cut();

        int tempPosition = editor.getText().getCaretPosition();

        StackItem item = new StackItem(temp, tempPosition, this.type);

        editor.stack.push(item);

        DetailView.data.setFactoryDate(editor.getPartName(), "剪切");
    }

    /**
     * 重做
     */
    @Override
    public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        FileEditor editor = (FileEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        if (!editor.redoStack.isEmpty()) {

            StackItem item = editor.redoStack.pop();

            switch (item.getType()) {
            // 确定栈中操作类型
            case 0:
                redoCut(editor, item);
                break;// 0表示剪切，调用剪切redo函数

            case 1:
                redoPaste(editor, item);
                break;// 1表示粘贴

            default:
                break;
            }
            return Status.OK_STATUS;
        }
        return Status.CANCEL_STATUS;
    }

    /**
     * 重新粘贴
     * @param editor
     * @param item
     */
    private void redoPaste(FileEditor editor, StackItem item) {
        // TODO Auto-generated method stub

        int index = item.getCaretPosition();

        editor.getText().setSelection(index, index);

        editor.getText().paste();

        editor.stack.push(item);

        DetailView.data.setFactoryDate(editor.getPartName(), "重新粘贴");
    }

    private void redoCut(FileEditor editor, StackItem item) {
        // TODO Auto-generated method stub
        editor.stack.push(item);

        int index = item.getCaretPosition();

        editor.getText().setSelection(index, index + item.getText().length());

        editor.getText().cut();

        DetailView.data.setFactoryDate(editor.getPartName(), "重新剪切");
    }

    @Override
    public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

        FileEditor editor = (FileEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();

        if (!editor.stack.isEmpty()) {

            StackItem item = editor.stack.pop();

            editor.redoStack.push(item);

            switch (item.getType()) {

            case 0:
                undoCut(editor, item);
                break;

            case 1:
                undoPaste(editor, item);
                break;

            default:
                break;
            }
            return Status.OK_STATUS;
        }
        return Status.CANCEL_STATUS;
    }

    private void undoCut(FileEditor editor, StackItem item) {
        // TODO Auto-generated method stub
        int index = item.getCaretPosition();

        editor.getText().setSelection(index, index);

        editor.getText().insert(item.getText());

        DetailView.data.setFactoryDate(editor.getPartName(), "撤销剪切");
    }

    private void undoPaste(FileEditor editor, StackItem item) {
        // TODO Auto-generated method stub
        int index = item.getCaretPosition();

        editor.getText().setSelection(index - item.getText().length(), index);

        editor.getText().cut();

        DetailView.data.setFactoryDate(editor.getPartName(), "撤销粘贴");
    }

}
