package cn.elwy.editor.intro;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import cn.elwy.editor.ui.editor.EditorInput;
import cn.elwy.editor.ui.editor.FileEditor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }

    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600)); // 定义窗口初始化大小
        configurer.setTitle("文本编辑器");
        // configurer.setShellStyle(SWT.NO_TRIM); // 设置窗口样式

        configurer.setShowCoolBar(true); // 显示工具栏
        configurer.setShowMenuBar(true); // 显示菜单栏
        configurer.setShowStatusLine(true); // 显示状态栏
        configurer.setShowFastViewBars(true); // 显示快速查看视图按钮
        configurer.setShowProgressIndicator(true); // 显示进度指示器
        configurer.setShowPerspectiveBar(true); // 显示透视图按钮

        // IPreferenceStore store = PlatformUI.getPreferenceStore();
        // store.setDefault(IWorkbenchPreferenceConstants.ENABLE_ANIMATIONS, true);
        // // 设置系统显示风格为传统风格
        // store.setDefault(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, true);
        // // 设置透视图按钮在右上角显示
        // store.setDefault(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
        // IWorkbenchPreferenceConstants.TOP_RIGHT);
        // // 设置每次启动都打开欢迎界面
        // store.setDefault(IWorkbenchPreferenceConstants.SHOW_INTRO, true);
    }

    public void postWindowOpen() {
        IWorkbenchWindow window = getWindowConfigurer().getWindow();
        final Shell shell = window.getShell();
        // // shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
        // shell.setAlpha(0);
        // UiUtil.asyncExec(new Runnable() {
        // @Override
        // public void run() {
        // int alpha = shell.getAlpha();
        // while (shell.getAlpha() < 255) {
        // shell.setAlpha(alpha++);
        // try {
        // Thread.sleep(5);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        // }
        // });

        // Image imgBg = ImageUtil.getImage("/skin/blue/images/bodybag.png");
        // shell.setBackgroundImage(imgBg);
        // Button button = new Button(shell, SWT.ON_TOP);
        // button.setBounds(100, 100, 200, 100);
        // button.setText("Test");
        // shell.getMenuBar()..setBackgroundMode(SWT.INHERIT_DEFAULT);
        // 设置窗口自动居中
        // ShellUtil.shellCentre(shell);
        shell.setMaximized(true); // 设置窗口最大化
        shell.setMinimumSize(800, 600); // 设置窗口最小值
        // SkinUtil.skinSwitch(SkinStyle.BLUE);//.BLUE);
        // SkinUtil.applyStyles(shell);

        // IWorkbenchPage page = window.getActivePage();
        // page.showActionSet("org.eclipse.ui.actionSet.keyBindings");
        // page.hideActionSet("org.eclipse.ui.edit.text.actionSet.openExternalFile");
        // page.hideActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
        // page.hideActionSet("org.eclipse.ui.edit.text.actionSet.navigation");
        // page.hideActionSet("org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo");
        // page.hideActionSet("org.eclipse.ui.WorkingSetActionSet");
        //
        // // page.hideView(page.findView(AppID.LOG_VIEW_ID));
        //
        // IMenuManager menuBar = getWindowConfigurer().getActionBarConfigurer().getMenuManager();
        // // clean file menu
        // /*
        // * hideMenuItem(menuBar, IWorkbenchActionConstants.M_FILE, "converstLineDelimitersTo"); hideMenuItem(menuBar,
        // * IWorkbenchActionConstants.M_FILE, "org.eclipse.ui.edit.text.openExternalFile");
        // */
        //
        // hideMenuItem(menuBar, "help", "org.eclipse.update.ui.softwareUpdates");
        // hideMenuItem(menuBar, "help", "org.eclipse.update.ui.updateMenu");
        // hideMenuItem(menuBar, "help", "org.eclipse.ui.actionSet.keyBindings");
        // hideMenuItem(menuBar, "help", "org.eclipse.ui.actions.showKeyAssistHandler");
        //
        // // refresh menubar
        // menuBar.updateAll(true);

        openPart(window);

        // IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        //
        // Shell shell = getWindowConfigurer().getWindow().getShell();
        //
        // Rectangle screenSize = Display.getDefault().getClientArea();
        //
        // Rectangle frameSize = shell.getBounds();
        //
        // shell.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        //
        // try {
        //
        //
        // } catch (PartInitException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    public boolean preWindowShellClose() {
        boolean result = false;
        MessageBox msgBox = new MessageBox(new Shell(), SWT.YES | SWT.NO | SWT.ICON_QUESTION);
        msgBox.setText("Confirm Exit");
        msgBox.setMessage("Do you want exit application system?");
        int open = msgBox.open();
        if (open == SWT.YES) {
            result = true;
        }
        return result;
    }

    /**
     * 打开Part
     * @param window
     */
    private void openPart(final IWorkbenchWindow window) {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                EditorInput input = new EditorInput();
                IWorkbenchPage page = window.getActivePage();
                try {
                    page.openEditor(input, FileEditor.ID);
                    // page.openEditor(input, AppID.DOC_LIST_EDITOR_ID, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
