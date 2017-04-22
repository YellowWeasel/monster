package com.erayic.agr.common.net.back;


import java.util.List;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByUserBean  {

    private String Icon;//服务图标
    private String ServiceName;//服务名称
    private String ServiceID;//服务ID
    private int Type;//服务类型
    private int Classify;//服务分类
    private int JumpType;//跳转类型
    private String JumpUrl;//跳转链接
    private boolean IsOwner;//是否关注
    private List<SpecifysInfo> Specifys;//子服务信息


    public static class SpecifysInfo{
        private int JumpType;//跳转类型
        private String Sepcify;//子服务名称
        private String ServiceID;//服务ID
        private String Url;//跳转链接
        private boolean IsOwner;//是否关注

        public int getJumpType() {
            return JumpType;
        }

        public void setJumpType(int jumpType) {
            JumpType = jumpType;
        }

        public String getSepcify() {
            return Sepcify;
        }

        public void setSepcify(String sepcify) {
            Sepcify = sepcify;
        }

        public String getServiceID() {
            return ServiceID;
        }

        public void setServiceID(String serviceID) {
            ServiceID = serviceID;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public boolean isOwner() {
            return IsOwner;
        }

        public void setOwner(boolean owner) {
            IsOwner = owner;
        }
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

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
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

    public int getJumpType() {
        return JumpType;
    }

    public void setJumpType(int jumpType) {
        JumpType = jumpType;
    }

    public String getJumpUrl() {
        return JumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        JumpUrl = jumpUrl;
    }

    public boolean isOwner() {
        return IsOwner;
    }

    public void setOwner(boolean owner) {
        IsOwner = owner;
    }

    public List<SpecifysInfo> getSpecifys() {
        return Specifys;
    }

    public void setSpecifys(List<SpecifysInfo> specifys) {
        Specifys = specifys;
    }
}
