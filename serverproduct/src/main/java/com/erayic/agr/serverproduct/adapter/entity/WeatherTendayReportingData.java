package com.erayic.agr.serverproduct.adapter.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wxk on 2017/5/8.
 */

public class WeatherTendayReportingData implements Parcelable {
    private String Title;
    private String SummaryTxt;
    private String ConentTxt;
    private String Source;
    private String PubTime;
    private List<WeatherTendayReportingData> ReportImg;

    public WeatherTendayReportingData(Parcel in) {
        Title = in.readString();
        SummaryTxt = in.readString();
        ConentTxt = in.readString();
        Source = in.readString();
        PubTime = in.readString();
        ReportImg = in.createTypedArrayList(WeatherTendayReportingData.CREATOR);
    }
    public static final Creator<WeatherTendayReportingData> CREATOR = new Creator<WeatherTendayReportingData>() {
        @Override
        public WeatherTendayReportingData createFromParcel(Parcel in) {
            return new WeatherTendayReportingData(in);
        }

        @Override
        public WeatherTendayReportingData[] newArray(int size) {
            return new WeatherTendayReportingData[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummaryTxt() {
        return SummaryTxt;
    }

    public void setSummaryTxt(String summaryTxt) {
        SummaryTxt = summaryTxt;
    }

    public String getConentTxt() {
        return ConentTxt;
    }

    public void setConentTxt(String conentTxt) {
        ConentTxt = conentTxt;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getPubTime() {
        return PubTime;
    }

    public void setPubTime(String pubTime) {
        PubTime = pubTime;
    }

    public List<WeatherTendayReportingData> getReportImg() {
        return ReportImg;
    }

    public void setReportImg(List<WeatherTendayReportingData> reportImg) {
        ReportImg = reportImg;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(SummaryTxt);
        dest.writeString(ConentTxt);
        dest.writeString(Source);
        dest.writeString(PubTime);
        dest.writeTypedList(ReportImg);
    }
}
