package cn.elwy.editor.ui.provider;

import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileTreeContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getChildren(Object parentElement) {
        return ((File) parentElement).listFiles();
    }

    @Override
    public Object getParent(Object element) {
        return ((File) element).getParentFile();
    }

    @Override
    public boolean hasChildren(Object element) {
        Object[] child = getChildren(element);
        return child == null ? false : child.length > 0;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof List) {
            return ((List<?>) inputElement).toArray();
        } else if (inputElement instanceof Array) {
            return ((Object[]) inputElement);
        } else if (inputElement instanceof Map) {
            return ((Map<?, ?>) inputElement).values().toArray();
        } else if (inputElement instanceof Set) {
            return ((Set<?>) inputElement).toArray(new Object[] {});
        } else if (inputElement instanceof File[]) {
            return (File[]) inputElement;
        } else if (inputElement instanceof File) {
            return ((File) inputElement).listFiles();
        }
        return null;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

}
