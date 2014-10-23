package cn.elwy.editor.ui.view;

import java.io.File;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;

import cn.elwy.editor.ui.editor.EditorInput;
import cn.elwy.editor.ui.provider.FileTreeContentProvider;
import cn.elwy.editor.ui.provider.FileTreeLabelProvider;

import com.swtdesigner.ResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

public class FileListView extends ViewPart {

    public static final String ID = "cn.elwy.editor.ui.view.FileListView";
    private TreeViewer tv;

    public FileListView() {
        setTitleImage(ResourceManager.getPluginImage("Editor", "icons/hierarchicalLayout.gif"));
    }

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FormLayout());

        Button preserveCase_1 = new Button(parent, SWT.CHECK);
        FormData fd_preserveCase_1 = new FormData();
        fd_preserveCase_1.top = new FormAttachment(0, 5);
        fd_preserveCase_1.left = new FormAttachment(0, 5);
        preserveCase_1.setLayoutData(fd_preserveCase_1);

        preserveCase_1.setText("保留大小写(&P)");

        preserveCase_1.setSelection(true);

        tv = new TreeViewer(parent);
        Tree tree = tv.getTree();
        FormData fd_tree = new FormData();
        fd_tree.bottom = new FormAttachment(100, 1);
        fd_tree.right = new FormAttachment(100, 1);
        fd_tree.top = new FormAttachment(preserveCase_1, 5);
        fd_tree.left = new FormAttachment(0,-1);
        tree.setLayoutData(fd_tree);

        // 添加内容管理器
        tv.setContentProvider(new FileTreeContentProvider());

        // 添加标签管理器
        tv.setLabelProvider(new FileTreeLabelProvider());

        // 设置treeviewer的输入
        tv.setInput(File.listRoots());// root没什么作用
        // tv.setInput(new File("d:/"));//root没什么作用

        // 增加过滤器
        tv.addFilter(new FileFilter());

        // 增加排序，使文件夹显示在前
        tv.setComparator(new FileSorter());

        final FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv.getLabelProvider();

        FileViewActionGroup actionGroup = new FileViewActionGroup(tv);

        ftlp.setPreserveCase(preserveCase_1.getSelection());

        preserveCase_1.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {

                boolean preserveCase = ((Button) event.widget).getSelection();

                ftlp.setPreserveCase(preserveCase);
            }
        });

        actionGroup.fillContextMenu(new MenuManager());

        hookDoubleClickAction();
    }

    private void hookDoubleClickAction() {
        // TODO Auto-generated method stub
        tv.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                // TODO Auto-generated method stub
                IStructuredSelection strsel = (IStructuredSelection) event.getSelection();

                String filepath = strsel.getFirstElement().toString();

                File file = new File(filepath);

                if (!file.isDirectory()) {

                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                    try {

                        page.openEditor(createEditorInput(file), "Editor.Editor");

                    } catch (PartInitException e) {

                        e.printStackTrace();
                    }

                    DetailView.data.setFactoryDate(file.getName(), "打开文件");

                    DetailView view = (DetailView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .findView(DetailView.ID);

                    view.getTv().refresh();
                } else {

                    Object obj = strsel.getFirstElement();

                    tv.expandToLevel(obj, 1);
                }
            }

        });
    }

    private EditorInput createEditorInput(File file) {

        EditorInput input = new EditorInput(file);

        return input;
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }

    public TreeViewer getTv() {
        return tv;
    }

    public void setTv(TreeViewer tv) {
        this.tv = tv;
    }

}
