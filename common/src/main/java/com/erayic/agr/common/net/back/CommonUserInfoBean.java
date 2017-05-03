package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUserInfoBean {

    private String UserID = "";//用户ID
    private String AppID = "";//应用ID
    private String ActiveBaseID = "";//当前活动基地ID
    private String BaseName = "";//基地名称
    private String EntID = "";//企业ID
    private String EntName = "";//企业名称
    private String Icon = "";//图片
    private String Name = "";//姓名
    private String TelNum = "";//电话号码
    private boolean IsWeixin;//是否绑定微信
    private int Role = -1;//角色
    private APPInfo APP;//APP信息


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String appID) {
        AppID = appID;
    }

    public String getActiveBaseID() {
        return ActiveBaseID;
    }

    public void setActiveBaseID(String activeBaseID) {
        ActiveBaseID = activeBaseID;
    }

    public String getBaseName() {
        return BaseName;
    }

    public void setBaseName(String baseName) {
        BaseName = baseName;
    }

    public String getEntID() {
        return EntID;
    }

    public void setEntID(String entID) {
        EntID = entID;
    }

    public String getEntName() {
        return EntName;
    }

    public void setEntName(String entName) {
        EntName = entName;
    }


    public APPInfo getAPP() {
        return APP;
    }

    public void setAPP(APPInfo APP) {
        this.APP = APP;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isWeixin() {
        return IsWeixin;
    }

    public void setWeixin(boolean weixin) {
        IsWeixin = weixin;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }


    public String getTelNum() {
        return TelNum;
    }

    public void setTelNum(String telNum) {
        TelNum = telNum;
    }

    public static class APPInfo {
        private String ExpireTime = "";//到期时间
        private String RegisterTime = "";//注册时间
        private int Status = -1;//状态

        public String getExpireTime() {
            return ExpireTime;
        }

        public void setExpireTime(String expireTime) {
            ExpireTime = expireTime;
        }

        public String getRegisterTime() {
            return RegisterTime;
        }

        public void setRegisterTime(String registerTime) {
            RegisterTime = registerTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }
    }

}
