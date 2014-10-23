package cn.elwy.editor.ui.view;

import java.util.Date;

public class OperationDatas {

    private Date operatetime;
    private String operated;
    private String filename;

    public OperationDatas(String filename, Date time, String operat) {
        this.filename = filename;
        this.operatetime = time;
        this.operated = operat;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getOperated() {
        return operated;
    }

    public void setOperated(String operated) {
        this.operated = operated;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
