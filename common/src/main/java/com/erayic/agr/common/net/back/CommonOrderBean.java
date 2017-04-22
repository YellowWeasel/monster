package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonOrderBean {

    private String OrderID;//订单ID
    private String ServiceName;//服务名称
    private String Icon;//服务图标
    private float Price;//价格
    private int Period;//期限
    private int PeriodType;////期限类型
    private String OrderTime;//订单生成时间
    private String FinishTime;//订单完成时间
    private int Status;//订单状态
    private String StatusToString;//状态说明
    private int PayType;//支付方式
    private String PayID;//支付ID
    private String AccountInfo;//账户信息
    private int IsSend;//发票

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public String getPayID() {
        return PayID;
    }

    public void setPayID(String payID) {
        PayID = payID;
    }

    public String getAccountInfo() {
        return AccountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        AccountInfo = accountInfo;
    }

    public int getIsSend() {
        return IsSend;
    }

    public void setIsSend(int isSend) {
        IsSend = isSend;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }

    public int getPeriodType() {
        return PeriodType;
    }

    public void setPeriodType(int periodType) {
        PeriodType = periodType;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getStatusToString() {
        return StatusToString;
    }

    public void setStatusToString(String statusToString) {
        StatusToString = statusToString;
    }
}
