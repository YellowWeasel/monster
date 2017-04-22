package com.erayic.agr.common.net.back;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：主题子服务信息
 */

public class CommonSubServiceBean implements Parcelable {

    private String ServiceID;//子服务ID
    private String crop;//子服务名称
    private int Status;//子服务状态
    private String EndTime;//截止时间

    private boolean isCheck;//UI选择。本身不具备此属性

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    protected CommonSubServiceBean(Parcel in) {
        ServiceID = in.readString();
        crop = in.readString();
        Status = in.readInt();
        EndTime = in.readString();
    }


    public static final Creator<CommonSubServiceBean> CREATOR = new Creator<CommonSubServiceBean>() {
        @Override
        public CommonSubServiceBean createFromParcel(Parcel in) {
            return new CommonSubServiceBean(in);
        }

        @Override
        public CommonSubServiceBean[] newArray(int size) {
            return new CommonSubServiceBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ServiceID);
        dest.writeString(crop);
        dest.writeInt(Status);
        dest.writeString(EndTime);
    }
}
