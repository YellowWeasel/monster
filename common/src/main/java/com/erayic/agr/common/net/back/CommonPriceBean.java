package com.erayic.agr.common.net.back;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：价格Bean
 */

public class CommonPriceBean implements Parcelable {

    private int PriceID;//价格ID
    private float Price;//价格
    private String Unit;//价格单位
    private boolean IsOffers;//是否优惠
    private String VaildStart;//有效开始时间
    private String VaildEnd;//有效截止时间
    private boolean IsMaster;//是否主价格

    private boolean isCheck;//是否被选中  //UI呈现 和model数据无关

    public int getPriceID() {
        return PriceID;
    }

    public void setPriceID(int priceID) {
        PriceID = priceID;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PriceID);
        dest.writeFloat(Price);
        dest.writeString(Unit);
        dest.writeByte((byte) (IsOffers ? 1 : 0));
        dest.writeString(VaildStart);
        dest.writeString(VaildEnd);
        dest.writeByte((byte) (IsMaster ? 1 : 0));
    }

    protected CommonPriceBean(Parcel in) {
        PriceID = in.readInt();
        Price = in.readFloat();
        Unit = in.readString();
        IsOffers = in.readByte() != 0;
        VaildStart = in.readString();
        VaildEnd = in.readString();
        IsMaster = in.readByte() != 0;
    }


    public static final Creator<CommonPriceBean> CREATOR = new Creator<CommonPriceBean>() {
        @Override
        public CommonPriceBean createFromParcel(Parcel in) {
            return new CommonPriceBean(in);
        }

        @Override
        public CommonPriceBean[] newArray(int size) {
            return new CommonPriceBean[size];
        }
    };
}
