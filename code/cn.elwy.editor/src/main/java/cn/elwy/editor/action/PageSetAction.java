package cn.elwy.editor.action;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.dialog.PageSetDialog;
import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class PageSetAction implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow window;

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(IWorkbenchWindow window) {
        this.window = window;
    }

    @Override
    public void run(IAction action) {
        // TODO Auto-generated method stub

        FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();
        // Shell sh=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        // PageSetDialog dialog=new PageSetDialog(sh);
        // dialog.open();
        DetailView.data.setFactoryDate(editor.getPartName(), "页面设置");
        DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView(DetailView.ID);
        view.getTv().refresh();

        PrinterJob p = PrinterJob.getPrinterJob();
        p.pageDialog(new PageFormat());
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub
    }

}
