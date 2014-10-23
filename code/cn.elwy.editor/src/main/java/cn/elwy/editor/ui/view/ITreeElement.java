package cn.elwy.editor.ui.view;

import java.util.List;

public interface ITreeElement {

    // 得到树节点的名称
    public String getName();

    // 设置与子节点集合
    public void setChildren(List children);

    // 得到子节点集合
    public List getChildren();

    // 是否有子节点
    public boolean hasChildren();

    // 添加子节点
    public void addChild(ITreeElement treeElement);

}
