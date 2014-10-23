package cn.elwy.editor.ui.dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import cn.elwy.editor.ui.editor.FileEditor;
import cn.elwy.editor.ui.view.DetailView;

public class FindDialog extends Dialog {

    private IWorkbenchWindow window;
    private Button check;
    private Button upBtn;
    private Button downBtn;
    private String editorcontext;
    private Text text;

    /**
     * 创建查找对话框
     * @param parentShell
     */
    public FindDialog(Shell parentShell, IWorkbenchWindow window) {
        super(parentShell);
        this.window = window;
    }

    /**
     * 创建对话框内容
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout) container.getLayout();
        gridLayout.numColumns = 11;
        {
            Label label = new Label(container, SWT.NONE);
            {
                GridData gridData = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
                gridData.widthHint = 71;
                label.setLayoutData(gridData);
            }
            label.setText("\u67E5\u627E\u5185\u5BB9(&N):");// 查找内容（N）
        }
        {
            text = new Text(container, SWT.BORDER);
            {
                GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
                gridData.widthHint = 173;
                text.setLayoutData(gridData);
            }
            FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();
            // 查找文本为编辑器中选择的文字
            text.setText(editor.getText().getSelectionText());
            text.setSelection(0, editor.getText().getSelectionText().length());
        }
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);

        Label label = new Label(container, SWT.NONE);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

        {
            check = new Button(container, SWT.CHECK);
            {
                GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
                gridData.heightHint = 24;
                check.setLayoutData(gridData);
            }
            check.setSelection(true);
            check.setText("\u533A\u5206\u5927\u5C0F\u5199");// 区分大小写
        }
        {
            Group directGroup = new Group(container, SWT.NONE);
            directGroup.setText("\u65B9\u5411");// Group组件----方向
            {
                GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
                gridData.widthHint = 164;
                gridData.heightHint = 40;
                directGroup.setLayoutData(gridData);
            }
            {
                upBtn = new Button(directGroup, SWT.RADIO);
                upBtn.setBounds(0, 20, 58, 16);
                upBtn.setText("\u5411\u4E0A");// 向上
            }
            {
                downBtn = new Button(directGroup, SWT.RADIO);
                downBtn.setSelection(true);
                downBtn.setBounds(103, 20, 51, 16);
                downBtn.setText("\u5411\u4E0B");// 向下
            }
        }
        return container;
    }

    @Override
    protected void okPressed() {
        // TODO Auto-generated method stub
        final String textstr = text.getText();

        Pattern p;
        Matcher m, m2;

        String result1 = "", result2 = "", result3 = "";
        String downSubString = "";
        int indexstart = 0;
        int index = 0;
        if (textstr.equals("")) {
            MessageBox messageBox = new MessageBox(window.getShell());

            messageBox.setMessage("请输入查找内容！");

            messageBox.open();

            text.setFocus();

            return;
        }
        if (window.getActivePage().getActiveEditor() instanceof FileEditor) {

            FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();

            editorcontext = editor.getText().getText();
            // 区分大小写
            if (!check.getSelection()) {

                p = Pattern.compile(textstr, Pattern.CASE_INSENSITIVE);

                result1 = "不区分大小写；";
            } else {
                result1 = "区分大小写；";

                p = Pattern.compile(textstr);
            }
            if (upBtn.getSelection()) {// 向上查找

                // 如果不减去text.getCaretPosition()这里又有一个很奇怪的错误，
                // 字串的结果与对话框中插入符位置竟然有关系！
                // 每次到了行头都会多截取几个字符，郁闷！
                int caret = editor.getText().getCaretPosition();

                if (caret != 0)
                    caret -= text.getCaretPosition();

                String s2 = editorcontext.substring(0, caret);

                m = p.matcher(s2);

                String temp = s2;

                m2 = p.matcher(temp);

                boolean bool = m2.find();

                while (bool) {

                    int a = temp.indexOf(m2.group(0)) + textstr.length();

                    temp = temp.substring(a);

                    m2 = p.matcher(temp);

                    bool = m2.find();

                    index += a;
                }

                index = index - textstr.length();

                result2 = "向上查找；";

            } else // 向下查找
            {
                indexstart = editor.getText().getCaretPosition() + editor.getText().getSelectionCount();

                downSubString = editorcontext.substring(indexstart);

                m = p.matcher(downSubString);

                result3 = "向下查找；";
                // index=downSubString.indexOf(m.group(0))+indexstart;//为什么添加这一句没有结果嘞？？？？

            }

            if (m.find()) {
                if (result3.equals("向下查找；"))

                    index = downSubString.indexOf(m.group(0)) + indexstart;// 在这里加就莫有问题！奇怪啊

                editor.getText().setSelection(index, index + textstr.length());

                DetailView.data.setFactoryDate(editor.getPartName(), "查找\"" + textstr + "\"成功！" + result1 + result2
                        + result3);

                DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(DetailView.ID);
                view.getTv().refresh();
            } else {

                DetailView.data.setFactoryDate(editor.getPartName(), "查找\"" + textstr + "\"失败！" + result1 + result2
                        + result3);

                DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(DetailView.ID);
                view.getTv().refresh();
                MessageBox messageBox = new MessageBox(window.getShell());

                messageBox.setText("查找");

                messageBox.setMessage("找不到\"" + textstr + "\"");

                messageBox.open();
            }
        }
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        // 点击查找按钮后，查找按钮文字变为“查找下一个”
        final Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);

        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                button.setText("查找下一个");
            }
        });

        button.setText("\u67E5\u627E");

        Button btnAbc = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        btnAbc.setText("\u53D6\u6D88");
    }

    @Override
    protected void configureShell(Shell newShell) {
        // TODO Auto-generated method stub
        super.configureShell(newShell);

        newShell.setText("查找");

        newShell.setSize(290, 170);

        Rectangle screenSize = Display.getDefault().getClientArea();

        Rectangle frameSize = newShell.getBounds();

        newShell.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

    }

}
