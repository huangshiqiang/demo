package cn.elwy.editor.ui.view;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import java.util.List;

public class TableViewerContentProvider implements IStructuredContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        // TODO Auto-generated method stub
        return ((List) inputElement).toArray();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub

    }

}
