package cn.elwy.editor.ui.provider;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class WizardLabelProvider implements ITableLabelProvider {

    public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

    public void dispose() {
        // TODO Auto-generated method stub

    }

    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }/*
      * // private Image pkIcon = StudioImages.pkIcon; // 主键 // private Image fkIcon = StudioImages.fkIcon; // 外键 //
      * private Image checkedOnIcon = StudioImages.toDoIcon; // 复选框被选中 // private Image checkedOffIcon =
      * StudioImages.haveDoneIcon; // null; // 复选框不能取消选中 // private Image uncheckedOnIcon = StudioImages.canDoIcon; //
      * 复选框未被选中 // private Image uncheckedOffIcon = StudioImages.cannotDoIcon; // 复选框不能被选中
      */
    /** 设置各列显示的图标 */
    /*
     * public Image getColumnImage(Object element, int columnIndex) {
     *//** 根据输入对象，返回相应列的图标 */
    /*
     * if (element instanceof ViewItem) { ViewItem viewItem = (ViewItem) element; switch (viewItem.getWinzardType()) {
     * case ViewItem.TYPE_POLICY_CHANGE1: return getPolicyChange1Image(viewItem, columnIndex); case
     * ViewItem.TYPE_POLICY_CHANGE2: return getPolicyChange2Image(viewItem, columnIndex); default: return null; } }
     * return null; }
     *//** 设置各列显示的数据 */
    /*
     * public String getColumnText(Object element, int columnIndex) {
     *//** 根据输入对象，返回相应的数据值 */
    /*
     * if (element instanceof ViewItem) { ViewItem viewItem = (ViewItem) element; switch (viewItem.getWinzardType()) {
     * case ViewItem.TYPE_POLICY_CHANGE1: return getPolicyChange1Text(viewItem, columnIndex); case
     * ViewItem.TYPE_POLICY_CHANGE2: return getPolicyChange2Text(viewItem, columnIndex); default: return null; } }
     * return null; }
     *//**
     * 变更加密策略向导，返回相应列的图标
     * @param vi
     * @param columnIndex
     * @return
     */
    /*
     * public Image getPolicyChange1Image(ViewItem vi, int columnIndex) { switch (columnIndex) { case 0: return null;
     * case 1: if (vi.isPrimaryKey()) return pkIcon; if (vi.getPkOrfk() == Column.CONSTRAINT_FK) return fkIcon; return
     * null; case 2:// ................. if (vi.getColumnStatus() == OdcProcessManager.STATE_CANNOT_DECRYPT) // 是否
     * return null; if (vi.isPrimaryKey()) // 主键不可选择 return uncheckedOffIcon; if (vi.getPkOrfk() ==
     * Column.CONSTRAINT_FK) // 外键不可选择 return uncheckedOffIcon; if (vi.getIsUnique() == Column.INDEX_TYPE_UNIQUE) //
     * 唯一索引 return uncheckedOffIcon; if (vi.getColumnStatus() == Constant.STATUS_CHECKED_OFF) // 不可取消选中 return
     * checkedOffIcon; if (vi.getColumnStatus() == Constant.STATUS_UNCHECKED_ON) // 未选择 return uncheckedOnIcon; if
     * (vi.getColumnStatus() == Constant.STATUS_CHECKED_ON) // 选中的列 return checkedOnIcon; return null; default: return
     * null; } }
     *//**
     * 变更加密策略向导，返回各列显示的值
     * @param vi
     * @param columnIndex
     * @return
     */
    /*
     * public String getPolicyChange1Text(ViewItem vi, int columnIndex) { switch (columnIndex) { case 0: return
     * Integer.toString(vi.getNo()); case 1: return null; case 2: return null; case 3: return vi.getColumnName(); case
     * 4: return vi.getColumnDataType(); case 5: return vi.getColumnDescription(); default: return ""; } }
     *//**
     * 变更加密策略向导，返回相应列的图标
     * @param vi
     * @param columnIndex
     * @return
     */
    /*
     * public Image getPolicyChange2Image(ViewItem vi, int columnIndex) { switch (columnIndex) { case 0: return null;
     * case 1: if (vi.isPrimaryKey()) return pkIcon; if (vi.getPkOrfk() == Column.CONSTRAINT_FK) return fkIcon; return
     * null; case 2: return null; default: return null; } }
     *//**
     * 变更加密策略向导，返回各列显示的值
     * @param vi
     * @param columnIndex
     * @return
     */
    /*
     * public String getPolicyChange2Text(ViewItem vi, int columnIndex) { switch (columnIndex) { case 0: return
     * Integer.toString(vi.getNo()); case 1: return null; case 2: return vi.getColumnName(); case 3: return
     * vi.getColumnDataType(); case 4: return String.valueOf(vi.getPrefixPlaintLength()); case 5: return
     * vi.getDeviceName(); case 6: return vi.getAlgorithmName(); case 7: return vi.getSaltValueName(); default: return
     * ""; } } public void addListener(ILabelProviderListener listener) { } public void dispose() { } public boolean
     * isLabelProperty(Object element, String property) { return false; } public void
     * removeListener(ILabelProviderListener listener) { }
     */
}
