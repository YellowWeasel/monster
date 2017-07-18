package com.erayic.agr.common.event;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：管理单元刷新
 */

public class UnitRefreshMessage {

    public static final int UNIT_MASTER_LOG = 0;//工作日志
    public static final int UNIT_MASTER_STATUE = 1;//作物生长情况

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
