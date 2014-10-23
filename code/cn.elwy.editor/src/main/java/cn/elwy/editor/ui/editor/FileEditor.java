package cn.elwy.editor.ui.editor;

import java.io.*;

import java.util.Stack;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.eclipse.ui.part.EditorPart;

import cn.elwy.editor.ui.dialog.StackItem;
import cn.elwy.editor.ui.view.DetailView;
import cn.elwy.editor.ui.view.FileListView;

@SuppressWarnings({ "restriction" })
public class FileEditor extends EditorPart {

    // ================================================================
    public static final String ID = "cn.elwy.editor.ui.editor.FileEditor"; //$NON-NLS-1$
    private boolean dirty = false;
    private File textfile;// editor显示的文件
    private Text text;// editor的内容
    private String textstr;// text内容转换成的字符串
    // 读取文件内容的buffer
    private StringBuffer buffer = new StringBuffer("");
    private char[] readBuffer = new char[1];
    // 标志
    private static boolean wrap = false;
    private boolean save = false;
    private WorkbenchWindow workbenchWindow;
    public static String temptext = "";
    private String coord = "所在行数：";
    private UndoActionHandler undoAction;
    private RedoActionHandler redoAction;
    public Stack<StackItem> stack = new Stack<StackItem>();
    public Stack<StackItem> redoStack = new Stack<StackItem>();

    // ===============================================================
    public FileEditor() {
    }

    public void createPartControl(Composite parent) {
        Composite top = new Composite(parent, SWT.NONE);
        top.setLayout(new FillLayout());
        if (!wrap)
            text = new Text(top, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
        else
            text = new Text(top, SWT.BORDER | SWT.WRAP | SWT.CANCEL | SWT.V_SCROLL | SWT.MULTI);
        text.setText(new String(buffer));
        // 添加侦听
        text.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                setDirty(true);
                firePropertyChange(PROP_DIRTY);
            }
        });
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

                showStatusMessage("总行数:" + text.getLineCount(), " 字符数:" + text.getCharCount(),
                        coord + (text.getCaretLineNumber() + 1));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

                showStatusMessage("总行数:" + text.getLineCount(), " 字符数：" + text.getCharCount(),
                        coord + (text.getCaretLineNumber() + 1));
            }

        });
        // 侦听鼠标左键
        Listener mouseListener = new Listener() {
            public void handleEvent(Event e) {
                // 修改状态行
                showStatusMessage("总行数:" + text.getLineCount(), " 字符数：" + text.getCharCount(),
                        coord + (text.getCaretLineNumber() + 1));
            }
        };
        text.addListener(SWT.MouseDown, mouseListener);
        text.addListener(SWT.MouseUp, mouseListener);
        createGlobalActionHandlers();
        EditorActionGroup actionGroup = new EditorActionGroup(text);
        actionGroup.fillContextMenu(new MenuManager());
    }

    private void createGlobalActionHandlers() {
        IUndoContext undoContext = workbenchWindow.getWorkbench().getOperationSupport().getUndoContext();
        ;
        // set up action handlers that operate on the current context
        undoAction = new UndoActionHandler(this.getSite(), undoContext);
        redoAction = new RedoActionHandler(this.getSite(), undoContext);
        IActionBars actionBars = getEditorSite().getActionBars();
        actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
        actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);

    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // 保存文件
        textstr = getText().getText();
        if (textfile == null) {
            // 调用另存为
            doSaveAs();
        } else {
            try {

                FileWriter fw = new FileWriter(textfile);
                fw.write(textstr);
                fw.close();
                // 向操作结果视图中添加项目
                DetailView.data.setFactoryDate(textfile.getName(), "保存文件");
                setDirty(false);
                DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(DetailView.ID);
                view.getTv().refresh();
                FileListView fileview = (FileListView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage().findView(FileListView.ID);
                fileview.getTv().refresh();
                change();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void change() {
        firePropertyChange(PROP_DIRTY);
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean getDirty() {
        return dirty;
    }

    @Override
    public void setFocus() {
        // Set the focus
    }

    public File getTextfile() {
        return textfile;
    }

    public void setTextfile(File textfile) {
        this.textfile = textfile;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void doSaveAs() {
        // 另存为
        File file = queryFile();
        if (file != null) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    textfile = file;
                } catch (IOException e) {
                    e.printStackTrace();
                }// endcatch
            }// endif
            else {
                Shell sh = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                MessageBox md = new MessageBox(sh, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION);
                md.setMessage(file.getName() + "已经存在，要替换它吗？");
                if (md.open() == SWT.OK) {
                    textfile = file;
                    setPartName(file.getName());
                } else
                    return;
            }// endelse
            try {
                setDirty(false);
                FileWriter fw = new FileWriter(textfile);
                fw.write(textstr);
                fw.close();
                change();
                DetailView.data.setFactoryDate(textfile.getName(), "保存文件");
                DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(DetailView.ID);
                view.getTv().refresh();
                FileListView fileview = (FileListView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage().findView(FileListView.ID);
                fileview.getTv().refresh();
                setTextfile(textfile);
                setPartName(textfile.getName());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }// endif
        else
            return;

    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        workbenchWindow = (WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (input instanceof EditorInput) {
            this.save = true;
            String string;
            textfile = ((EditorInput) input).getFile();
            string = ((EditorInput) input).getTextstr();
            if (textfile != null && string == null) {
                try {
                    setPartName(textfile.getName());
                    FileReader fileReader = new FileReader(textfile);
                    while (fileReader.read(readBuffer) != -1) {
                        buffer.append(readBuffer);
                    }
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } // endcatch
            } else if (textfile != null && string != null) {
                setPartName(textfile.getName());
                buffer.append(string);
                // setDirty(true);
            } else if (string != null && textfile == null)
                buffer.append(string);

        }// endif

        this.setSite(site);
        this.setInput(input);

    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public boolean isSaveAsAllowed() {

        return true;
    }

    public void setPartName(String partName) {
        // TODO Auto-generated method stub
        super.setPartName(partName);
    }

    @Override
    public boolean isSaveOnCloseNeeded() {
        // TODO Auto-generated method stub
        return super.isSaveOnCloseNeeded();
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public boolean hasSaved() {

        return this.save;

    }

    // 文件保存对话框
    private File queryFile() {
        Shell sh = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        sh.open();
        FileDialog dialog = new FileDialog(sh, SWT.SAVE);
        dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
        dialog.setText("保存文件");
        String path = dialog.open();
        if (path != null && path.length() > 0)
            return new File(path);
        return null;
    }

    // 修改状态栏信息
    public void showStatusMessage(String LineCount, String CharCount, String Coordinate) {
        IStatusLineManager statusLine = workbenchWindow.getStatusLineManager();
        for (IContributionItem item : statusLine.getItems()) {
            System.out.println(item.getId());
            if (item instanceof StatusLineContributionItem) {
                StatusLineContributionItem statusItem = (StatusLineContributionItem) item;
                if ("LineCount".equals(item.getId())) {
                    statusItem.setText(LineCount);
                } else if ("CharCount".equals(item.getId())) {
                    statusItem.setText(CharCount);
                } else if ("Coordinate".equals(item.getId())) {
                    statusItem.setText(Coordinate);
                }
            }
        }
        // statusLine.removeAll();
        // StatusLineContributionItem statusItem1 = new StatusLineContributionItem("LineCount", 15);
        // StatusLineContributionItem statusItem2 = new StatusLineContributionItem("CharCount", 15);
        // StatusLineContributionItem statusItem3 = new StatusLineContributionItem("Coordinate", 50);
        // statusItem1.setText(LineCount);
        // statusItem2.setText(CharCount);
        // statusItem3.setText(Coordinate);
        // statusLine.add(statusItem1);
        // statusLine.add(statusItem2);
        // statusLine.add(statusItem3);
        statusLine.update(true);
    }

    public boolean isWrap() {
        return wrap;
    }

    public void setWrap(boolean wrap) {
        FileEditor.wrap = wrap;
    }

    public void setfirePropertyChange() {
        firePropertyChange(PROP_DIRTY);
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        DetailView.data.setFactoryDate(getPartName(), "关闭");
    }

}
