

import java.io.File;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class FileTableLabelProvider implements ITableLabelProvider
{
  public String getColumnText(Object element, int column_index)
  {
    if (column_index == 0)
    {
      return ((File) element).getName();
    }

    if (column_index == 1)
    {
      return "" + ((File) element).length();
    }

    return "";
  }

  public void addListener(ILabelProviderListener ilabelproviderlistener)
  {
  }

  public void dispose()
  {
  }

  public boolean isLabelProperty(Object obj, String s)
  {
    return false;
  }

  public void removeListener(ILabelProviderListener ilabelproviderlistener)
  {
  }

  public Image getColumnImage(Object element, int column_index)
  {

    if (column_index != 0)
    {
      return null;
    }

    if (((File) element).isDirectory())
    {
      return Util.getImageRegistry().get("folder");
    }
    else
    {
      return Util.getImageRegistry().get("file");
    }
  }
}
