package cn.elwy.editor.ui.editor;

import java.io.File;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class EditorInput implements IEditorInput {

    private File file;
    private String textstr;

    public EditorInput(File file) {
        this.file = file;
    }

    public EditorInput() {
    }

    public EditorInput(File file, String textstr) {
        this.file = file;
        this.textstr = textstr;
    }

    @Override
    public boolean exists() {
        // TODO Auto-generated method stub
        // return file.exists();
        return false;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        if (file == null)
            return "text";
        else
            return file.getName();

    }

    @Override
    public IPersistableElement getPersistable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getToolTipText() {
        // TODO Auto-generated method stub
        return "Editor";
    }

    @Override
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getTextstr() {
        return textstr;
    }

    public void setTextstr(String textstr) {
        this.textstr = textstr;
    }
}
