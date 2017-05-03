package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：企业信息
 */

public class CommonEntInfoBean {

    private String entID;//企业ID
    private String name;//企业名称
    private boolean isLicense;//是否有营业执照
    private String License;//营业执照地址
    private boolean isCredentials;//是否有组织机构代码证
    private String Credentials;//组织机构代码证地址
    private int kenrel;//核心服务剩余多少天
    private String contactID;//企业联系人ID
    private String contacter;//企业联系人姓名
    private String descript;//企业描述

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLicense() {
        return isLicense;
    }

    public void setLicense(boolean license) {
        isLicense = license;
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public boolean isCredentials() {
        return isCredentials;
    }

    public void setCredentials(boolean credentials) {
        isCredentials = credentials;
    }

    public String getCredentials() {
        return Credentials;
    }

    public void setCredentials(String credentials) {
        Credentials = credentials;
    }

    public int getKenrel() {
        return kenrel;
    }

    public void setKenrel(int kenrel) {
        this.kenrel = kenrel;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
