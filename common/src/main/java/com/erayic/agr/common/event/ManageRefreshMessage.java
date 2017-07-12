package com.erayic.agr.common.event;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageRefreshMessage {

    public static final int MANAGE_MASTER_BASE_INFO = 0;//基地详情
    public static final int MANAGE_MASTER_PERSONNEL_LIST = 1;//人员详情
    public static final int MANAGE_MASTER_WORK_LIST = 2;//预设作业
    public static final int MANAGE_MASTER_FERTILIZER_LIST = 3;//化肥
    public static final int MANAGE_MASTER_PESTICIDE_LIST = 4;//农药
    public static final int MANAGE_MASTER_SEED_LIST = 5;//种子
    public static final int MANAGE_MASTER_PRODUCE_LIST = 6;//产品
    public static final int MANAGE_MASTER_BASE_LIST = 7;//基地列表

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
