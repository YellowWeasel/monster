package com.erayic.agr.serverproduct.adapter.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wxk on 2017/5/16.
 */

public class MarketInfoParamter implements Parcelable {
    private int cropId;
    private String marketName;
    private String start;
    private String end;

    public MarketInfoParamter() {
    }

    protected MarketInfoParamter(Parcel in) {
        cropId = in.readInt();
        marketName = in.readString();
        start = in.readString();
        end = in.readString();
    }

    public static final Creator<MarketInfoParamter> CREATOR = new Creator<MarketInfoParamter>() {
        @Override
        public MarketInfoParamter createFromParcel(Parcel in) {
            return new MarketInfoParamter(in);
        }

        @Override
        public MarketInfoParamter[] newArray(int size) {
            return new MarketInfoParamter[size];
        }
    };

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public MarketInfoParamter(int cropId, String marketName, String start, String end) {
        this.cropId = cropId;
        this.marketName = marketName;
        this.start = start;
        this.end = end;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cropId);
        dest.writeString(marketName);
        dest.writeString(start);
        dest.writeString(end);
    }
}
