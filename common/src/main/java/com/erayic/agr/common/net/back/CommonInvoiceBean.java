package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：发票信息
 */

public class CommonInvoiceBean {

    private String Title;//发票抬头
    private String Receiver;//接收人
    private String Tel;//电话
    private String Region;//区域
    private String Address;//详细地址

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
