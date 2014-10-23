package cn.elwy.editor.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class fontAction implements IWorkbenchWindowActionDelegate {

    private Font font;
    private IWorkbenchWindow window;
    private Color color;
    static private Text text;

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
        FontDialog fd = new FontDialog(window.getShell(), SWT.NONE);
        fd.open();
        try {
            FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();
            Font bfont = editor.getText().getFont();
            Color bcolor = editor.getText().getForeground();
            text = editor.getText();
            editor.getText().getFont();
            if (fd.getFontList() == null)
                return;
            font = new Font(Display.getCurrent(), fd.getFontList());
            text.setFont(font);
            color = new Color(null, fd.getRGB());
            text.setForeground(color);
            if (!bfont.equals(font)) {
                if (!bcolor.equals(color)) {
                    DetailView.data.setFactoryDate(editor.getPartName(), "颜色改变");
                    DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .findView(DetailView.ID);
                    view.getTv().refresh();
                    return;
                }
                DetailView.data.setFactoryDate(editor.getPartName(), "字体改变");
                DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(DetailView.ID);
                view.getTv().refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }
}
