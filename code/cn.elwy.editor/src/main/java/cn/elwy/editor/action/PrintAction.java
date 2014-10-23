package cn.elwy.editor.action;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class PrintAction extends Action implements IWorkbenchWindowActionDelegate {

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
        FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();
        String text = editor.getText().getText();
        String fileName = editor.getPartName();
        PrintDialog pd = new PrintDialog(window.getShell());
        PrinterData printerData = pd.open();

        if (printerData != null) {
            Printer printer = new Printer(printerData);
            // 打印文件
            try {

                EditorPrinter p = new EditorPrinter(printer, fileName, text);
                p.print();
            } catch (java.lang.Exception e) {
                e.printStackTrace();
            }
            printer.dispose();// printer对象没用后要手动dispose掉
        }

    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

}
