package cn.elwy.editor.intro;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.elwy.editor.AppID;

public class Perspective implements IPerspectiveFactory {

    protected final String LEFT_TOP = "leftTop";
    protected final String LEFT_BOTTOM = "leftBottom";
    protected final String MIDDLE_BOTTOM = "middleBottom";
    protected final String RIGHT_TOP = "rightTop";
    protected final String RIGHT_BOTTOM = "rightBottom";

    public void createInitialLayout(IPageLayout layout) {
        // defineActions(layout);
        customLayout(layout);
    }

    protected void defineActions(IPageLayout layout) {
        // layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
        layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
    }

    /**
     * 自定义视图的布局
     * @param layout
     */
    protected void customLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();// 得到编辑区域
        layout.setEditorAreaVisible(true);// 设置是否显示编辑区域
        // layout.setFixed(true);// 设置当前布局是否固定

        // 定义一个文件夹形式的视图, IPageLayout.ID_EDITOR_AREA和editorArea作用是一样的
        IFolderLayout leftTop = layout.createFolder(LEFT_TOP, IPageLayout.LEFT, 0.27f, editorArea);
        leftTop.addView(AppID.FILE_LIST_VIEW);// 向文件夹中添加导航视图

        // 定义一个文件夹形式的消息面板
        IFolderLayout middleBottom = layout.createFolder(MIDDLE_BOTTOM, IPageLayout.BOTTOM, 0.73f, editorArea);
        middleBottom.addView(AppID.DETAIL_VIEW_ID); // 预设详细信息视图

        // // 定义一个文件夹形式的大纲面板
        // IFolderLayout rightTop = layout.createFolder(RIGHT_TOP, IPageLayout.RIGHT, 0.70f, editorArea);
        // rightTop.addView(AppID.OUTLINE_VIEW_ID); // 向文件夹中添加大纲视图
        // // 定义一个文件夹形式的属性视图
        // IFolderLayout rightBottom = layout.createFolder(RIGHT_BOTTOM, IPageLayout.BOTTOM, 0.50f, RIGHT_TOP);
        // rightBottom.addView(AppID.PROPERTY_VIEW_ID); // 向文件夹中添加属性视图

    }

}
