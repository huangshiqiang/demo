package cn.elwy.editor.intro;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction deleteAction;
    private IWorkbenchAction introAction;
    private IWorkbenchAction redoAction;
    private IWorkbenchAction saveAction;
    private IWorkbenchAction saveAllAction;
    private IWorkbenchAction saveAsAction;
    private IWorkbenchAction undoAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
        deleteAction = ActionFactory.DELETE.create(window);
        register(deleteAction);
        introAction = ActionFactory.INTRO.create(window);
        register(introAction);
        redoAction = ActionFactory.REDO.create(window);
        register(redoAction);
        saveAction = ActionFactory.SAVE.create(window);
        register(saveAction);
        saveAllAction = ActionFactory.SAVE_ALL.create(window);
        register(saveAllAction);
        saveAsAction = ActionFactory.SAVE_AS.create(window);
        register(saveAsAction);
        undoAction = ActionFactory.UNDO.create(window);
        register(undoAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
        super.fillMenuBar(menuBar);
        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        menuBar.add(helpMenu);
        // Help
        helpMenu.add(introAction);
    }

    protected void fillCoolBar(ICoolBarManager coolBar) {
        ToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        toolbar.add(saveAction);
        toolbar.add(saveAsAction);
        toolbar.add(undoAction);
        toolbar.add(redoAction);

        // coolBar.add(new ToolBarContributionItem(toolbar, "main"));
        coolBar.add(toolbar);
    }

    protected void fillStatusLine(IStatusLineManager statusLine) {
        // 状态栏
        super.fillStatusLine(statusLine);
        // 定义StatusLineContributionItem对象
        StatusLineContributionItem statusItem1 = new StatusLineContributionItem("LineCount", 15);
        StatusLineContributionItem statusItem2 = new StatusLineContributionItem("CharCount", 15);
        StatusLineContributionItem statusItem3 = new StatusLineContributionItem("Coordinate", 50);

        statusItem1.setText("总行数");
        statusItem2.setText("字符数");
        statusItem3.setText("所在行数");
        // 将statusItem注册到statusLine
        statusLine.add(statusItem1);
        statusLine.add(statusItem2);
        statusLine.add(statusItem3);
    }

}
