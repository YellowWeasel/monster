package com.erayic.agr.other_1.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者:LiJiang
 * 邮箱:1755036940@qq.com
 * 作用:(实体)监控数据
 */

public class MonitoringData implements MultiItemEntity {


    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR=4;
    public static final int TYPE_FIVE=5;
    public static final int TYPE_SIX=6;

    public String  theMoment;//远程控制
    public String  operation;//运行模式
    public String  monitoring;//控制温度

    public String  temp;//温度
    public String  motion;//自动

    public String reboot;//重启

    public int itemType;

    public String  uri;//跳转地址


    public String getReboot() {
        return reboot;
    }

    public void setReboot(String reboot) {
        this.reboot = reboot;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }




    public void setTheMoment(String theMoment) {
        this.theMoment = theMoment;
    }

    public void setMonitoring(String monitoring) {
        this.monitoring = monitoring;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }


    public String getTheMoment() {
        return theMoment;
    }

    public String getMonitoring() {
        return monitoring;
    }

    public String getOperation() {
        return operation;
    }


    public String getTemp() {
        return temp;
    }

    public String getMotion() {
        return motion;
    }


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
