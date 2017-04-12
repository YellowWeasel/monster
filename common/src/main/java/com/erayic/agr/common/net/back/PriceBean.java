package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：价格Bean
 */

public class PriceBean {
    private int PriceID;//价格ID
    private double Price;//价格
    private String Unit;//价格单位
    private boolean IsOffers;//是否优惠
    private String VaildStart;//有效开始时间
    private String VaildEnd;//有效截止时间
    private boolean IsMaster;//是否主价格

    public int getPriceID() {
        return PriceID;
    }

    public void setPriceID(int priceID) {
        PriceID = priceID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public boolean isOffers() {
        return IsOffers;
    }

    public void setOffers(boolean offers) {
        IsOffers = offers;
    }

    public String getVaildStart() {
        return VaildStart;
    }

    public void setVaildStart(String vaildStart) {
        VaildStart = vaildStart;
    }

    public String getVaildEnd() {
        return VaildEnd;
    }

    public void setVaildEnd(String vaildEnd) {
        VaildEnd = vaildEnd;
    }

    public boolean isMaster() {
        return IsMaster;
    }

    public void setMaster(boolean master) {
        IsMaster = master;
    }
}
