package com.erayic.agr.common.net.back.api;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：得到气象实况信息
 */

public class CommonRealTimeWeatherBean {

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
    private List<HistoryInfo> HistoryData;//历史数据

    public static class HistoryInfo {
        private String RecordTime;//记录时间
        private double Rain;//雨量
        private double Wind_Max;//最大风
        private double Temp_Max;//最高温
        private double Temp_Min;//最低温
        private double Temp_Avg;//平均温

        public String getRecordTime() {
            return RecordTime;
        }

        public void setRecordTime(String recordTime) {
            RecordTime = recordTime;
        }

        public double getRain() {
            return Rain;
        }

        public void setRain(double rain) {
            Rain = rain;
        }

        public double getWind_Max() {
            return Wind_Max;
        }

        public void setWind_Max(double wind_Max) {
            Wind_Max = wind_Max;
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

        public double getTemp_Avg() {
            return Temp_Avg;
        }

        public void setTemp_Avg(double temp_Avg) {
            Temp_Avg = temp_Avg;
        }
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

    public List<HistoryInfo> getHistoryData() {
        return HistoryData;
    }

    public void setHistoryData(List<HistoryInfo> historyData) {
        HistoryData = historyData;
    }

}
