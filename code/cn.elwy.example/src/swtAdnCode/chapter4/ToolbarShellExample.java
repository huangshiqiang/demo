package chapter4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class ToolbarShellExample {
    Display d;
    Shell s;

    ToolbarShellExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(300, 300);
        s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
        s.setText("A Shell Toolbar Example");

        final ToolBar bar = new ToolBar(s, SWT.HORIZONTAL);
        bar.setSize(300, 70);
        bar.setLocation(0, 0);

        // create images for toolbar buttons
        final Image saveIcon = new Image(d, "c:\\icons\\save.jpg");
        final Image openIcon = new Image(d, "c:\\icons\\open.jpg");
        final Image cutIcon = new Image(d, "c:\\icons\\cut.jpg");
        final Image copyIcon = new Image(d, "c:\\icons\\copy.jpg");
        final Image pasteIcon = new Image(d, "c:\\icons\\paste.jpg");

        // create and add the button for performing an open operation
        final ToolItem openToolItem = new ToolItem(bar, SWT.PUSH);
        openToolItem.setImage(openIcon);
        openToolItem.setText("Open");
        openToolItem.setToolTipText("Open File");

        // create and add the button for performing a save operation
        final ToolItem saveToolItem = new ToolItem(bar, SWT.PUSH);
        saveToolItem.setImage(saveIcon);
        saveToolItem.setText("Save");
        saveToolItem.setToolTipText("Save File");

        // create and add the button for performing a cut operation
        final ToolItem cutToolItem = new ToolItem(bar, SWT.PUSH);
        cutToolItem.setImage(cutIcon);
        cutToolItem.setText("Cut");
        cutToolItem.setToolTipText("Cut");

        // create and add the button for performing a copy operation
        final ToolItem copyToolItem = new ToolItem(bar, SWT.PUSH);
        copyToolItem.setImage(copyIcon);
        copyToolItem.setText("Copy");
        copyToolItem.setToolTipText("Copy");

        // create and add the button for performing a paste operation
        final ToolItem pasteToolItem = new ToolItem(bar, SWT.PUSH);
        pasteToolItem.setImage(pasteIcon);
        pasteToolItem.setText("Paste");
        pasteToolItem.setToolTipText("Paste");

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
