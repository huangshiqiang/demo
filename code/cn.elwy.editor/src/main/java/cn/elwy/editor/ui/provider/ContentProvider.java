package cn.elwy.editor.ui.provider;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ContentProvider implements IStructuredContentProvider {

    @SuppressWarnings("unchecked")
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof List) {
            return ((List) inputElement).toArray();
        } else if (inputElement instanceof Array) {
            return ((Object[]) inputElement);
        } else if (inputElement instanceof Map) {
            return ((Map) inputElement).values().toArray();
        } else if (inputElement instanceof Set) {
            return ((Set) inputElement).toArray(new Object[] {});
        }
        return null;
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

}
