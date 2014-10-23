package cn.elwy.editor.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.editor.EditorInput;
import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class WrapAction implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow window;
    private FileEditor editor;

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
        String string;
        String oper;
        boolean dirty;
        try {
            IWorkbenchPage[] iw;
            editor = (FileEditor) window.getActivePage().getActiveEditor();
            string = editor.getText().getText();
            dirty = editor.getDirty();
            if (!editor.isWrap()) {
                editor.setWrap(true);
                oper = "自动换行";
            } else {
                editor.setWrap(false);
                oper = "取消自动换行";
            }
            iw = window.getPages();
            iw[0].closeEditor(editor, false);
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .openEditor(new EditorInput(editor.getTextfile(), string), editor.ID);

            FileEditor editor2 = (FileEditor) window.getActivePage().getActiveEditor();
            editor2.setDirty(dirty);
            editor2.setfirePropertyChange();
            int index = DetailView.data.getFactoryData().size();
            DetailView.data.getFactoryData().remove(index - 1);
            DetailView.data.setFactoryDate(editor.getPartName(), oper);
            DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .findView(DetailView.ID);
            view.getTv().refresh();
            editor2.showStatusMessage("总行数:" + editor2.getText().getLineCount(), " 字符数："
                    + editor2.getText().getCharCount(), "所在行数：" + (editor2.getText().getCaretLineNumber() + 1));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

}
