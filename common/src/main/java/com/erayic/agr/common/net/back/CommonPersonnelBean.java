package com.erayic.agr.common.net.back;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonPersonnelBean implements Parcelable {

    private String UserID;
    private String Icon;
    private String Name;
    private String Tel;
    private int Role;

    private boolean isCheck;//UI选择用


    protected CommonPersonnelBean(Parcel in) {
        UserID = in.readString();
        Icon = in.readString();
        Name = in.readString();
        Tel = in.readString();
        Role = in.readInt();
        isCheck = in.readByte() != 0;
    }

    public static final Creator<CommonPersonnelBean> CREATOR = new Creator<CommonPersonnelBean>() {
        @Override
        public CommonPersonnelBean createFromParcel(Parcel in) {
            return new CommonPersonnelBean(in);
        }

        @Override
        public CommonPersonnelBean[] newArray(int size) {
            return new CommonPersonnelBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UserID);
        dest.writeString(Icon);
        dest.writeString(Name);
        dest.writeString(Tel);
        dest.writeInt(Role);
        dest.writeByte((byte) (isCheck ? 1 : 0));
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
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

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
