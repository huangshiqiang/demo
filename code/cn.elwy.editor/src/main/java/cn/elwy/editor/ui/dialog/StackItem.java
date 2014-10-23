package cn.elwy.editor.ui.dialog;

public class StackItem {
    private String text;
    private int caretPosition;
    private int type;

    public StackItem(String text, int caretPosition, int type) {
        this.text = text;
        this.caretPosition = caretPosition;
        this.type = type;

    }

    public String getText() {
        return text;
    }

    public int getCaretPosition() {
        return caretPosition;
    }

    public int getType() {
        return type;
    }

}
