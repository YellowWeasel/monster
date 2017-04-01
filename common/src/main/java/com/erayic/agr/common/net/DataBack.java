package com.erayic.agr.common.net;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：数据解析统一入口
 */

public class DataBack<T> {

    private boolean isSucess;//是否成功（bool）
    private int errCode;//错误代码
    private String errMsg;//错误消息
    private T result;//结果

    public boolean isSucess() {
        return isSucess;
    }

    public void setSucess(boolean sucess) {
        isSucess = sucess;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
