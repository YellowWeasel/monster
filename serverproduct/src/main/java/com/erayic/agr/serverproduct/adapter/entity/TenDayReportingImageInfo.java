package com.erayic.agr.serverproduct.adapter.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wxk on 2017/5/9.
 */

public class TenDayReportingImageInfo implements Parcelable {
    private String ImgTitle;
    private String ImgUrl;

    public TenDayReportingImageInfo(String imgTitle, String imgUrl) {
        ImgTitle = imgTitle;
        ImgUrl = imgUrl;
    }

    protected TenDayReportingImageInfo(Parcel in) {
        ImgTitle = in.readString();
        ImgUrl = in.readString();
    }

    public static final Creator<TenDayReportingImageInfo> CREATOR = new Creator<TenDayReportingImageInfo>() {
        @Override
        public TenDayReportingImageInfo createFromParcel(Parcel in) {
            return new TenDayReportingImageInfo(in);
        }

        @Override
        public TenDayReportingImageInfo[] newArray(int size) {
            return new TenDayReportingImageInfo[size];
        }
    };

    public String getImgTitle() {
        return ImgTitle;
    }

    public void setImgTitle(String imgTitle) {
        ImgTitle = imgTitle;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ImgTitle);
        dest.writeString(ImgUrl);
    }
}
