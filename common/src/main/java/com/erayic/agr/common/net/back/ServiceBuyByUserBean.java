package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByUserBean {

    private String ServiceID;//服务ID
    private String ServiceName;//服务名称
    private int Classify;//分类
    private int Type;//类别
    private String OpenTime;//开通时间
    private String EndTime;//截止时间
    private int Status;//当前服务状态
    private List<ServiceBuyByEntBean.SpecifysInfo> Specifys;//子服务信息

    public static class SpecifysInfo {
        private String ServiceID;//服务ID
        private String Sepcify;//服务名称

        public String getServiceID() {
            return ServiceID;
        }

        public void setServiceID(String serviceID) {
            ServiceID = serviceID;
        }

        public String getSepcify() {
            return Sepcify;
        }

        public void setSepcify(String sepcify) {
            Sepcify = sepcify;
        }
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public int getClassify() {
        return Classify;
    }

    public void setClassify(int classify) {
        Classify = classify;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(String openTime) {
        OpenTime = openTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public List<ServiceBuyByEntBean.SpecifysInfo> getSpecifys() {
        return Specifys;
    }

    public void setSpecifys(List<ServiceBuyByEntBean.SpecifysInfo> specifys) {
        Specifys = specifys;
    }


}
