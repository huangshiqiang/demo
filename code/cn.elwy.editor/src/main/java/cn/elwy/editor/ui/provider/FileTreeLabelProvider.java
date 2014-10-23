package cn.elwy.editor.ui.provider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class FileTreeLabelProvider implements ILabelProvider {

    private List<ILabelProviderListener> listeners;

    private Image file;

    private Image dir;

    boolean preserveCase;

    public FileTreeLabelProvider() {
        listeners = new ArrayList<ILabelProviderListener>();
        dir = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        file = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);

    }

    public void setPreserveCase(boolean preserveCase) {

        this.preserveCase = preserveCase;

        // 触发事件，重新得到显示标签

        LabelProviderChangedEvent event = new LabelProviderChangedEvent(this);

        for (int i = 0, n = listeners.size(); i < n; i++) {
            ILabelProviderListener ilpl = (ILabelProviderListener) listeners.get(i);
            ilpl.labelProviderChanged(event);
        }
    }

    public Image getImage(Object arg0) {
        // 返回目录或文件的图标
        return ((File) arg0).isDirectory() ? dir : file;
    }

    public String getText(Object arg0) {

        String text = ((File) arg0).getName();

        if (text.length() == 0) {

            text = ((File) arg0).getPath();

        }

        // 返回目录或文件的文件名

        return preserveCase ? text : text.toUpperCase();

    }

    public void addListener(ILabelProviderListener arg0) {
        // 添加监听器
        listeners.add(arg0);
    }

    public void dispose() {
        if (dir != null)
            dir.dispose();
        if (file != null)
            file.dispose();
    }

    public boolean isLabelProperty(Object arg0, String arg1) {
        return false;
    }

    public void removeListener(ILabelProviderListener arg0) {
        // 删除监听器
        listeners.remove(arg0);
    }

}
