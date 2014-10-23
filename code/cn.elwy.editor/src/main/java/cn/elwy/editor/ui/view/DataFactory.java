package cn.elwy.editor.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
//存储结果视图中的数据
public class DataFactory  {
	private List<OperationDatas> list;
	
	public DataFactory()
	{
		list= new ArrayList<OperationDatas>();
	}
	public void setFactoryDate(String filename ,String operat)
	{
	   list.add(new OperationDatas(filename,new Date(),operat));
	}
	
	public   List<OperationDatas> getFactoryData(){
		return list;
	}

}
