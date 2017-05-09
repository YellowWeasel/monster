package com.erayic.agr.serverproduct.adapter.entity;

/**
 * Created by wxk on 2017/5/6.
 */

public class RealTimeForecastInfo {
    private String AppearTime;//生成时间
    private double Rain_10M;//10分钟降水 单位：ml
    private double Rain_1H;//1小时降水
    private double Rain_3H;//3小时降水
    private double Rain_6H;//6小时降水
    private double Wind_Max;//最大风 单位：m/s
    private double WindDirect_Max;//风向 0-360
    private double Temp_Max;//最大温
    private double Temp_Min;//最小温
    private String Suggest;//农事建议
    private double Humi;//湿度

    public RealTimeForecastInfo() {
    }

    public RealTimeForecastInfo(String appearTime,
                                double rain_10M,
                                double wind_Max,
                                double windDirect_Max,
                                double temp_Max,
                                double temp_Min,
                                double humi) {
        AppearTime = appearTime;
        Rain_10M = rain_10M;
        Wind_Max = wind_Max;
        WindDirect_Max = windDirect_Max;
        Temp_Max = temp_Max;
        Temp_Min = temp_Min;
        Humi = humi;
    }

    public RealTimeForecastInfo(String appearTime,
                                double rain_10M,
                                double rain_1H,
                                double rain_3H,
                                double rain_6H,
                                double wind_Max,
                                double windDirect_Max,
                                double temp_Max,
                                double temp_Min,
                                String suggest,
                                double humi) {
        AppearTime = appearTime;
        Rain_10M = rain_10M;
        Rain_1H = rain_1H;
        Rain_3H = rain_3H;
        Rain_6H = rain_6H;
        Wind_Max = wind_Max;
        WindDirect_Max = windDirect_Max;
        Temp_Max = temp_Max;
        Temp_Min = temp_Min;
        Suggest = suggest;
        Humi = humi;
    }

    public double getHumi() {
        return Humi;
    }

    public void setHumi(double humi) {
        Humi = humi;
    }

    public String getAppearTime() {
        return AppearTime;
    }

    public void setAppearTime(String appearTime) {
        AppearTime = appearTime;
    }

    public double getRain_10M() {
        return Rain_10M;
    }

    public void setRain_10M(double rain_10M) {
        Rain_10M = rain_10M;
    }

    public double getRain_1H() {
        return Rain_1H;
    }

    public void setRain_1H(double rain_1H) {
        Rain_1H = rain_1H;
    }

    public double getRain_3H() {
        return Rain_3H;
    }

    public void setRain_3H(double rain_3H) {
        Rain_3H = rain_3H;
    }

    public double getRain_6H() {
        return Rain_6H;
    }

    public void setRain_6H(double rain_6H) {
        Rain_6H = rain_6H;
    }

    public double getWind_Max() {
        return Wind_Max;
    }

    public void setWind_Max(double wind_Max) {
        Wind_Max = wind_Max;
    }

    public double getWindDirect_Max() {
        return WindDirect_Max;
    }

    public void setWindDirect_Max(double windDirect_Max) {
        WindDirect_Max = windDirect_Max;
    }

    public double getTemp_Max() {
        return Temp_Max;
    }

    public void setTemp_Max(double temp_Max) {
        Temp_Max = temp_Max;
    }

    public double getTemp_Min() {
        return Temp_Min;
    }

    public void setTemp_Min(double temp_Min) {
        Temp_Min = temp_Min;
    }

    public String getSuggest() {
        return Suggest;
    }

    public void setSuggest(String suggest) {
        Suggest = suggest;
    }
}
