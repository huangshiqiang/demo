package cn.elwy.editor.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class SaveFileAction implements IWorkbenchWindowActionDelegate {
    private IWorkbenchWindow fWindow;
    private File textfile;
    private String textstr;

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(IWorkbenchWindow window) {
        // TODO Auto-generated method stub
        this.fWindow = window;
    }

    @Override
    public void run(IAction action) {
        // TODO Auto-generated method stub
        FileEditor editor = (FileEditor) fWindow.getActivePage().getActiveEditor();
        textfile = editor.getTextfile();
        textstr = editor.getText().getText();
        if (!editor.isDirty()) {
            DetailView.data.setFactoryDate(textfile.getName(), "文件未改变，无需保存！");
            DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .findView(DetailView.ID);
            view.getTv().refresh();
            return;
        }
        if (textfile == null) {
            File file = queryFile();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    textfile = file;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                MessageBox md = new MessageBox(fWindow.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION);
                md.setMessage(file.getName() + "已经存在，要替换它吗？");
                if (md.open() == SWT.OK)
                    textfile = file;
                else
                    return;
            }
            try {
                editor.setDirty(false);
                FileWriter fw = new FileWriter(textfile);
                fw.write(textstr);
                fw.close();
                editor.change();
                DetailView.data.setFactoryDate(textfile.getName(), "保存文件");
                editor.setTextfile(textfile);
                editor.setPartName(textfile.getName());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter fw = new FileWriter(textfile);
                fw.write(textstr);
                fw.close();
                DetailView.data.setFactoryDate(textfile.getName(), "保存文件");

                editor.setDirty(false);
                editor.change();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // ResultView.data.setFactoryDate(textfile.getName(),"保存文件");
    }

    private File queryFile() {
        FileDialog dialog = new FileDialog(fWindow.getShell(), SWT.SAVE);
        dialog.setFilterExtensions(new String[] { "*.txt" });
        dialog.setText("保存文件"); //$NON-NLS-1$
        String path = dialog.open();
        if (path != null && path.length() > 0)
            return new File(path);
        return null;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

}
