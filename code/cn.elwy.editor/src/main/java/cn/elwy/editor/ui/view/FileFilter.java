package cn.elwy.editor.ui.view;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class FileFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        File file = (File) element;
        Pattern p = Pattern.compile(".txt$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(file.getName());
        if (m.find() || file.isDirectory()) {
            return true;
        }
        return false;
    }

}
