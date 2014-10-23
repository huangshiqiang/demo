package chapter15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class CoolbarShellExample2 {
    Display d;
    Shell s;

    CoolbarShellExample2() {
        d = new Display();
        s = new Shell(d);
        s.setSize(400, 300);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Coolbar Example");

        final CoolBar coolBar = new CoolBar(s, SWT.BORDER);
        coolBar.setSize(395, 70);
        coolBar.setLocation(0, 0);
        // create images for toolbar buttons
        final Image saveIcon = new Image(d, "c:\\icons\\save.jpg");
        final Image openIcon = new Image(d, "c:\\icons\\open.jpg");
        final Image childIcon = new Image(d, "c:\\icons\\userH.ico");
        final Image cutIcon = new Image(d, "c:\\icons\\cut.jpg");
        final Image copyIcon = new Image(d, "c:\\icons\\copy.jpg");
        final Image pasteIcon = new Image(d, "c:\\icons\\paste.jpg");

        // create and add the button for performing an open operation
        final CoolItem openCoolItem = new CoolItem(coolBar, SWT.NONE);

        final ToolBar fileToolBar = new ToolBar(coolBar, SWT.HORIZONTAL);
        final ToolItem openToolItem = new ToolItem(fileToolBar, SWT.PUSH);
        openToolItem.setImage(openIcon);
        openToolItem.setText("Open");
        openToolItem.setToolTipText("Open");

        final ToolItem saveToolItem = new ToolItem(fileToolBar, SWT.PUSH);
        saveToolItem.setImage(openIcon);
        saveToolItem.setText("Save");
        saveToolItem.setToolTipText("Save");

        fileToolBar.pack();
        Point size = fileToolBar.getSize();
        openCoolItem.setControl(fileToolBar);
        openCoolItem.setSize(openCoolItem.computeSize(size.x, size.y));

        final CoolItem editbarCoolItem = new CoolItem(coolBar, SWT.PUSH);
        final ToolBar editToolBar = new ToolBar(coolBar, SWT.HORIZONTAL);

        // create and add the button for performing a cut operation
        final ToolItem cutToolItem = new ToolItem(editToolBar, SWT.PUSH);
        cutToolItem.setImage(cutIcon);
        cutToolItem.setText("Cut");
        cutToolItem.setToolTipText("Cut");

        // create and add the button for performing a copy operation
        final ToolItem copyToolItem = new ToolItem(editToolBar, SWT.PUSH);
        copyToolItem.setImage(copyIcon);
        copyToolItem.setText("Copy");
        copyToolItem.setToolTipText("Copy");

        // create and add the button for performing a paste operation
        final ToolItem pasteToolItem = new ToolItem(editToolBar, SWT.PUSH);
        pasteToolItem.setImage(pasteIcon);
        pasteToolItem.setText("Paste");
        pasteToolItem.setToolTipText("Paste");
        editToolBar.pack();
        size = editToolBar.getSize();
        editbarCoolItem.setControl(editToolBar);
        editbarCoolItem.setSize(editbarCoolItem.computeSize(size.x, size.y));

        final CoolItem fontCoolItem = new CoolItem(coolBar, SWT.PUSH);
        final Combo fontCombo = new Combo(coolBar, SWT.READ_ONLY | SWT.BORDER);
        String[] items = { "Arial", "Courier", "Times New Roman" };
        fontCombo.setItems(items);
        fontCombo.pack();
        size = fontCombo.getSize();
        fontCoolItem.setControl(fontCombo);
        fontCoolItem.setSize(fontCoolItem.computeSize(size.x, size.y));
        fontCoolItem.setMinimumSize(size);

        openToolItem.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                System.out.println("Open");

            }
        });

        saveToolItem.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                System.out.println("Save");

            }
        });

        cutToolItem.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                System.out.println("Cut");

            }
        });

        copyToolItem.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                System.out.println("Copy");

            }
        });

        pasteToolItem.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                System.out.println("Paste");

            }
        });

        // create the menu
        Menu m = new Menu(s, SWT.BAR);

        // create a file menu and add an exit item
        final MenuItem file = new MenuItem(m, SWT.CASCADE);
        file.setText("&File");
        final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
        file.setMenu(filemenu);
        final MenuItem openMenuItem = new MenuItem(filemenu, SWT.PUSH);
        openMenuItem.setText("&Open\tCTRL+O");
        openMenuItem.setAccelerator(SWT.CTRL + 'O');
        final MenuItem saveMenuItem = new MenuItem(filemenu, SWT.PUSH);
        saveMenuItem.setText("&Save\tCTRL+S");
        saveMenuItem.setAccelerator(SWT.CTRL + 'S');
        final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
        final MenuItem exitMenuItem = new MenuItem(filemenu, SWT.PUSH);
        exitMenuItem.setText("E&xit");

        // create an edit menu and add cut copy and paste items
        final MenuItem edit = new MenuItem(m, SWT.CASCADE);
        edit.setText("&Edit");
        final Menu editmenu = new Menu(s, SWT.DROP_DOWN);
        edit.setMenu(editmenu);
        final MenuItem cutMenuItem = new MenuItem(editmenu, SWT.PUSH);
        cutMenuItem.setText("&Cut");
        final MenuItem copyMenuItem = new MenuItem(editmenu, SWT.PUSH);
        copyMenuItem.setText("Co&py");
        final MenuItem pasteMenuItem = new MenuItem(editmenu, SWT.PUSH);
        pasteMenuItem.setText("&Paste");

        // create a Window menu and add Child item
        final MenuItem window = new MenuItem(m, SWT.CASCADE);
        window.setText("&Window");
        final Menu windowmenu = new Menu(s, SWT.DROP_DOWN);
        window.setMenu(windowmenu);
        final MenuItem maxMenuItem = new MenuItem(windowmenu, SWT.PUSH);
        maxMenuItem.setText("Ma&ximize");
        final MenuItem minMenuItem = new MenuItem(windowmenu, SWT.PUSH);
        minMenuItem.setText("Mi&nimize");

        // create a Help menu and add an about item
        final MenuItem help = new MenuItem(m, SWT.CASCADE);
        help.setText("&Help");
        final Menu helpmenu = new Menu(s, SWT.DROP_DOWN);
        help.setMenu(helpmenu);
        final MenuItem aboutMenuItem = new MenuItem(helpmenu, SWT.PUSH);
        aboutMenuItem.setText("&About");

        // add action listeners for the menu items

        openMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Open");
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        saveMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Save");
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        exitMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.exit(0);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        cutMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Cut");
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        copyMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Copy");
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        pasteMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Paste");
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        maxMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                Shell parent = (Shell) maxMenuItem.getParent().getParent();
                parent.setMaximized(true);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        minMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                Shell parent = (Shell) minMenuItem.getParent().getParent();
                parent.setMaximized(false);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        aboutMenuItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Help Invoked");
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        s.setMenuBar(m);
        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch())
                d.sleep();
        }
        d.dispose();
    }
}
