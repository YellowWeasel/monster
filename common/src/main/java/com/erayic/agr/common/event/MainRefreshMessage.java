package com.erayic.agr.common.event;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：主页面刷新
 */

public class MainRefreshMessage {

    public static final int MAIN_MASTER_UNIT = 0;//管理单元页面
    public static final int MAIN_MASTER_JOB = 1;//作业页面

    private int msgType;//消息类型
    private int subType;//消息副类型
    private Object data;//数据

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
