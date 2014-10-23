package chapter15;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class CoolbarShellExample {
    Display d;
    Shell s;

    CoolbarShellExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(300, 300);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Coolbar Example");

        final CoolBar bar = new CoolBar(s, SWT.BORDER);
        bar.setSize(280, 70);
        bar.setLocation(0, 0);
        // create images for coolbar buttons
        final Image saveIcon = new Image(d, "c:\\icons\\save.jpg");
        final Image openIcon = new Image(d, "c:\\icons\\open.jpg");
        final Image cutIcon = new Image(d, "c:\\icons\\cut.jpg");
        final Image copyIcon = new Image(d, "c:\\icons\\copy.jpg");
        final Image pasteIcon = new Image(d, "c:\\icons\\paste.jpg");

        // create and add the button for performing an open operation
        final CoolItem openCoolItem = new CoolItem(bar, SWT.NONE);
        final Button openBtn = new Button(bar, SWT.PUSH);
        openBtn.setImage(openIcon);
        openBtn.pack();
        Point size = openBtn.getSize();
        openCoolItem.setControl(openBtn);
        openCoolItem.setSize(openCoolItem.computeSize(size.x, size.y));

        // create and add the button for performing a save operation
        final CoolItem saveCoolItem = new CoolItem(bar, SWT.PUSH);
        final Button saveBtn = new Button(bar, SWT.PUSH);
        saveBtn.setImage(saveIcon);
        saveBtn.pack();
        size = saveBtn.getSize();
        saveCoolItem.setControl(saveBtn);
        saveCoolItem.setSize(saveCoolItem.computeSize(size.x, size.y));

        // create and add the button for performing a cut operation
        final CoolItem cutCoolItem = new CoolItem(bar, SWT.PUSH);
        final Button cutBtn = new Button(bar, SWT.PUSH);
        cutBtn.setImage(cutIcon);
        cutBtn.pack();
        size = cutBtn.getSize();
        cutCoolItem.setControl(cutBtn);
        cutCoolItem.setSize(cutCoolItem.computeSize(size.x, size.y));

        // create and add the button for performing a copy operation
        final CoolItem copyCoolItem = new CoolItem(bar, SWT.PUSH);
        final Button copyBtn = new Button(bar, SWT.PUSH);
        copyBtn.setImage(copyIcon);
        copyBtn.pack();
        size = copyBtn.getSize();
        copyCoolItem.setControl(copyBtn);
        copyCoolItem.setSize(copyCoolItem.computeSize(size.x, size.y));

        // create and add the button for performing a paste operation
        final CoolItem pasteCoolItem = new CoolItem(bar, SWT.PUSH);
        final Button pasteBtn = new Button(bar, SWT.PUSH);
        pasteBtn.setImage(pasteIcon);
        pasteBtn.pack();
        size = pasteBtn.getSize();
        pasteCoolItem.setControl(pasteBtn);
        pasteCoolItem.setSize(pasteCoolItem.computeSize(size.x, size.y));
        pasteCoolItem.setMinimumSize(size);

        openBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                System.out.println("Open");
            }
        });

        saveBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                System.out.println("Save");
            }
        });

        cutBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                System.out.println("Cut");
            }
        });

        copyBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                System.out.println("Copy");
            }
        });

        pasteBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
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
