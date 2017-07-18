package com.erayic.agr.common.net.back;


import java.util.List;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByUserBean {

    private String ServiceID;//服务ID
    private String Icon;//服务图标
    private String ServiceName;//服务名称
    private int Type;//服务类型
    private int Classify;//服务分类

    private boolean IsOwner;//是否拥有
    private boolean IsFree;//是否免费
    private boolean IsTry;//是否试用
    private boolean IsOrder;//是否订购
    private String DueDate;//到期时间

    private List<SpecifysInfo> Specifys;//子服务信息


    public static class SpecifysInfo {
        private String ServiceID;//服务ID
        private String Sepcify;//子服务名称
        private int CropID;//作物ID
        private boolean IsOwner;//是否拥有
        private boolean IsFree;//是否免费
        private boolean IsTry;//是否试用
        private boolean IsOrder;//是否订购
        private String DueDate;//到期时间

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

        public int getCropID() {
            return CropID;
        }

        public void setCropID(int cropID) {
            CropID = cropID;
        }

        public boolean isOwner() {
            return IsOwner;
        }

        public void setOwner(boolean owner) {
            IsOwner = owner;
        }

        public boolean isFree() {
            return IsFree;
        }

        public void setFree(boolean free) {
            IsFree = free;
        }

        public boolean isTry() {
            return IsTry;
        }

        public void setTry(boolean aTry) {
            IsTry = aTry;
        }

        public boolean isOrder() {
            return IsOrder;
        }

        public void setOrder(boolean order) {
            IsOrder = order;
        }

        public String getDueDate() {
            return DueDate;
        }

        public void setDueDate(String dueDate) {
            DueDate = dueDate;
        }
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getClassify() {
        return Classify;
    }

    public void setClassify(int classify) {
        Classify = classify;
    }

    public boolean isOwner() {
        return IsOwner;
    }

    public void setOwner(boolean owner) {
        IsOwner = owner;
    }

    public boolean isFree() {
        return IsFree;
    }

    public void setFree(boolean free) {
        IsFree = free;
    }

    public boolean isTry() {
        return IsTry;
    }

    public void setTry(boolean aTry) {
        IsTry = aTry;
    }

    public boolean isOrder() {
        return IsOrder;
    }

    public void setOrder(boolean order) {
        IsOrder = order;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public List<SpecifysInfo> getSpecifys() {
        return Specifys;
    }

    public void setSpecifys(List<SpecifysInfo> specifys) {
        Specifys = specifys;
    }
}
