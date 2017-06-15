package com.erayic.agr.common.net.back.api;

/**
 * Created by wxk on 2017/6/13.
 */

import com.erayic.agr.common.util.ErayicNetDate;

import org.joda.time.DateTime;

import java.util.List;

/**
 * 环境参数类
 */
public class CommonEnvironmentParameterBean {
    private String BaseName;
    private GPS Gps;
    private RealTime RealTimeInfo;
    private List<FeatureWeather> Features;
    private FarmingSuggestionResult Suggest;
    private int ErrCode;

    public CommonEnvironmentParameterBean(String baseName, GPS gps, RealTime realTimeInfo,
                                          List<FeatureWeather> features, FarmingSuggestionResult suggest,
                                          int errCode) {
        BaseName = baseName;
        Gps = gps;
        RealTimeInfo = realTimeInfo;
        Features = features;
        Suggest = suggest;
        ErrCode = errCode;
    }

    public CommonEnvironmentParameterBean() {
    }

    public String getBaseName() {
        return BaseName;
    }

    public void setBaseName(String baseName) {
        BaseName = baseName;
    }

    public GPS getGps() {
        return Gps;
    }

    public void setGps(GPS gps) {
        Gps = gps;
    }

    public RealTime getRealTimeInfo() {
        return RealTimeInfo;
    }

    public void setRealTimeInfo(RealTime realTimeInfo) {
        RealTimeInfo = realTimeInfo;
    }

    public List<FeatureWeather> getFeatures() {
        return Features;
    }

    public void setFeatures(List<FeatureWeather> features) {
        Features = features;
    }

    public FarmingSuggestionResult getSuggest() {
        return Suggest;
    }

    public void setSuggest(FarmingSuggestionResult suggest) {
        Suggest = suggest;
    }

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int errCode) {
        ErrCode = errCode;
    }

    /**
     * 地理位置
     */
    public static class GPS {

        private double Lon;
        private double Lat;
        private double Elv;

        public double getLon() {
            return Lon;
        }

        public void setLon(double lon) {
            Lon = lon;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double lat) {
            Lat = lat;
        }

        public double getElv() {
            return Elv;
        }

        public void setElv(double elv) {
            Elv = elv;
        }
    }

    /**
     * 实时天气
     */
    public static class RealTime {
        private String AppearTime;
        private double Rain_10M;
        private double Rain_1H;
        private String Rain_Desc;
        private double Rain_3Hl;
        private double Rain_6H;
        private double Wind_Max;
        private String Wind_Desc;
        private double WindDirect_Max;
        private double Temp_Max;
        private double Temp_Min;
        private int errCode;

        public String getAppearTime() {
            return AppearTime;
        }

        public void setAppearTime(String appearTime) {
            DateTime dateTime = new DateTime(ErayicNetDate.getLongDates(appearTime));
            AppearTime = dateTime.toString();
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

        public String getRain_Desc() {
            return Rain_Desc;
        }

        public void setRain_Desc(String rain_Desc) {
            Rain_Desc = rain_Desc;
        }

        public double getRain_3Hl() {
            return Rain_3Hl;
        }

        public void setRain_3Hl(double rain_3Hl) {
            Rain_3Hl = rain_3Hl;
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

        public String getWind_Desc() {
            return Wind_Desc;
        }

        public void setWind_Desc(String wind_Desc) {
            Wind_Desc = wind_Desc;
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

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }
    }

    /**
     * 未来天气预测
     */
    public static class FeatureWeather {
        private String ForecastDateTime;
        private int Aging;
        private int Key;
        private double  Value;

        public String getForecastDateTime() {
            return ForecastDateTime;
        }

        public void setForecastDateTime(String forecastDateTime) {
            DateTime dateTime = new DateTime(ErayicNetDate.getLongDates(forecastDateTime));
            ForecastDateTime = dateTime.toString();
        }

        public int getAging() {
            return Aging;
        }

        public void setAging(int aging) {
            Aging = aging;
        }

        public int getKey() {
            return Key;
        }

        public void setKey(int key) {
            Key = key;
        }

        public double getValue() {
            return Value;
        }

        public void setValue(double value) {
            Value = value;
        }
    }

    /**
     * 农事建议评价集
     */

    public static class FarmingSuggestionResult {
        public List<SuggestionResult> SuggestionResultList;

        public List<SuggestionResult> getSuggestionResultList() {
            return SuggestionResultList;
        }

        public void setSuggestionResultList(List<SuggestionResult> suggestionResultList) {
            SuggestionResultList = suggestionResultList;
        }
    }

    /**
     *农事建议评价
     */
    public static class SuggestionResult {
        /**
         * 评价指数
         */
        private int ResultIndex;
        /**
         * 评价详情
         */
        private String SuggestionContent;
        /**
         * 错误代码
         */
        private int ErrorCode;
        /**
         * 操作类型
         */
        private int Key;

        public int getResultIndex() {
            return ResultIndex;
        }

        public void setResultIndex(int resultIndex) {
            ResultIndex = resultIndex;
        }

        public String getSuggestionContent() {
            return SuggestionContent;
        }

        public void setSuggestionContent(String suggestionContent) {
            SuggestionContent = suggestionContent;
        }

        public int getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(int errorCode) {
            ErrorCode = errorCode;
        }

        public int getKey() {
            return Key;
        }

        public void setKey(int key) {
            Key = key;
        }
    }
}
