package cn.elwy.editor.action;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.editor.EditorInput;
import cn.elwy.editor.ui.view.DetailView;

/**
 * @since 3.0
 */
public class OpenFileAciton extends Action implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow fWindow;

    public OpenFileAciton() {
        setId("editor.openFileAction");
        setEnabled(true);
    }

    /*
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    public void dispose() {
        fWindow = null;
    }

    /*
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    public void init(IWorkbenchWindow window) {
        fWindow = window;
    }

    /*
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        run();
    }

    /*
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     * org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }

    private File queryFile() {
        FileDialog dialog = new FileDialog(fWindow.getShell(), SWT.OPEN);
        dialog.setText("打开文件"); //$NON-NLS-1$		
        dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
        String path = dialog.open();
        if (path != null && path.length() > 0)
            return new File(path);
        else
            return null;
    }

    /*
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {

        File file = queryFile();

        if (file != null) {
            IWorkbenchPage page = fWindow.getActivePage();
            try {
                page.openEditor(createEditorInput(file), getEditorId());
            } catch (PartInitException e) {
                e.printStackTrace();
            }
            DetailView.data.setFactoryDate(file.getName(), "打开文件");
            DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .findView(DetailView.ID);
            view.getTv().refresh();
        } else {
            return;
            //					String msg = "File is null: {0}"; //$NON-NLS-1$
            //					MessageDialog.openWarning(fWindow.getShell(), "Problem", msg); //$NON-NLS-1$
        }

    }

    private String getEditorId() {

        return "Editor.Editor"; //$NON-NLS-1$
    }

    private EditorInput createEditorInput(File file) {
        EditorInput input = new EditorInput(file);
        return input;
    }
}