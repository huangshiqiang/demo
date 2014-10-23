package cn.elwy.editor.action;

import java.util.Date;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * 动态菜单
 * @author huangsq
 * @version 1.0, 2013-12-27
 * @since 1.0, 2013-12-27
 */
public class ActionDynamicMenu extends ContributionItem {
    public ActionDynamicMenu(String name) {

    }

    @Override
    public void fill(Menu menu, int index) {
        MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
        menuItem.setText("My menu item (" + new Date() + ")");
        menuItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.err.println("Dynamic menu selected");
            }
        });
        System.err.println("Fill Dynamic Menu");
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

}