package cn.ucai.fulicenter.controller.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9 0009.
 */
public class Result {

    /**
     * retCode : 0
     * retMsg : true
     * retData : []
     */

    private int retCode;
    private boolean retMsg;
    private List<?> retData;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public boolean isRetMsg() {
        return retMsg;
    }

    public void setRetMsg(boolean retMsg) {
        this.retMsg = retMsg;
    }

    public List<?> getRetData() {
        return retData;
    }

    public void setRetData(List<?> retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "Result{" +
                "retCode=" + retCode +
                ", retMsg=" + retMsg +
                ", retData=" + retData +
                '}';
    }
}
