package com.erayic.agr.common.net.back;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务信息
 */

public class CommonServiceBean implements Parcelable {

    private String ServiceID;//服务ID
    private String ServiceName;//服务名称
    private int Type;//服务类型
    private String Descript;//服务描述
    private CommonPriceBean MasterPrice;//主价格
    private boolean IsTry;//是否试用
    private boolean IsExpire;//过去
    private boolean UserIsTry;//用户是否试用
    private boolean IsBuy;//是否购买
    private String DueDate;//到期时间
    private String Icon;//服务图标
    private boolean isCheck;//是否被选中  //UI呈现 和model数据无关

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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public CommonPriceBean getMasterPrice() {
        return MasterPrice;
    }

    public void setMasterPrice(CommonPriceBean masterPrice) {
        MasterPrice = masterPrice;
    }

    public boolean isTry() {
        return IsTry;
    }

    public void setTry(boolean aTry) {
        IsTry = aTry;
    }

    public boolean isExpire() {
        return IsExpire;
    }

    public void setExpire(boolean expire) {
        IsExpire = expire;
    }

    public boolean isUserIsTry() {
        return UserIsTry;
    }

    public void setUserIsTry(boolean userIsTry) {
        UserIsTry = userIsTry;
    }

    public boolean isBuy() {
        return IsBuy;
    }

    public void setBuy(boolean buy) {
        IsBuy = buy;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    protected CommonServiceBean(Parcel in) {
        ServiceID = in.readString();
        ServiceName = in.readString();
        Type = in.readInt();
        Descript = in.readString();
        IsTry = in.readByte() != 0;
        IsExpire = in.readByte() != 0;
        UserIsTry = in.readByte() != 0;
        IsBuy = in.readByte() != 0;
        DueDate = in.readString();
        Icon = in.readString();
        MasterPrice = in.readParcelable(CommonPriceBean.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ServiceID);
        dest.writeString(ServiceName);
        dest.writeInt(Type);
        dest.writeString(Descript);
        dest.writeParcelable(MasterPrice, flags);
        dest.writeByte((byte) (IsTry ? 1 : 0));
        dest.writeByte((byte) (IsExpire ? 1 : 0));
        dest.writeByte((byte) (UserIsTry ? 1 : 0));
        dest.writeByte((byte) (IsBuy ? 1 : 0));
        dest.writeString(DueDate);
        dest.writeString(Icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommonServiceBean> CREATOR = new Creator<CommonServiceBean>() {
        @Override
        public CommonServiceBean createFromParcel(Parcel in) {
            return new CommonServiceBean(in);
        }

        @Override
        public CommonServiceBean[] newArray(int size) {
            return new CommonServiceBean[size];
        }
    };
}
