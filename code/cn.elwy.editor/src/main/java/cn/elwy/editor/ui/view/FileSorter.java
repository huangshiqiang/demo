package cn.elwy.editor.ui.view;

import java.io.*;
import org.eclipse.jface.viewers.*;

public class FileSorter extends ViewerComparator {
    public int category(Object element) {
        return ((File) element).isDirectory() ? 0 : 1;
    }
}