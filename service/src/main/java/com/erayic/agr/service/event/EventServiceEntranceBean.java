package com.erayic.agr.service.event;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务首页数据传递更新
 */

public class EventServiceEntranceBean {

    private String serviceID;
    private boolean isOwner ;

    public EventServiceEntranceBean(String serviceID,boolean isOwner){
        this.serviceID = serviceID;
        this.isOwner = isOwner;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
