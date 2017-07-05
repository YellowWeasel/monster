package com.erayic.agr.common.net.back.unit;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUnitBatchSuggestBean {

    private FarmingSuggestionResult Suggest;// 生产建议
    private RealTimeInfo RealTime;//实况天气
    private List<FeaturesInfo> Features;//未来24小时天气情况

    public static class FarmingSuggestionResult {
        private List<SuggestionResult> SuggestionResultList;//建议集合

        public List<SuggestionResult> getSuggestionResultList() {
            return SuggestionResultList;
        }

        public void setSuggestionResultList(List<SuggestionResult> suggestionResultList) {
            SuggestionResultList = suggestionResultList;
        }
    }

    public static class SuggestionResult {
        private int ResultIndex;//结果指数
        private String SuggestionContent;//结果内容
        private int ErrorCode;//错误代码？
        private int Key;//操作类型

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

    public static class RealTimeInfo {
        private String AppearTime;//生成时间
        private double Rain_1H;
        private String Rain_Desc;//雨量描述
        private double Rain_3H;
        private double Rain_6H;
        private double Wind_Max;
        private String Wind_Desc;//风描述
        private double WindDirect_Max;
        private double Temp_Max;
        private double Temp_Min;

        public String getAppearTime() {
            return AppearTime;
        }

        public void setAppearTime(String appearTime) {
            AppearTime = appearTime;
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
    }

    public static class FeaturesInfo {
        private String ForecastDateTime;//时间
        private int Aging;
        private int Key;
        private double Value;

        public String getForecastDateTime() {
            return ForecastDateTime;
        }

        public void setForecastDateTime(String forecastDateTime) {
            ForecastDateTime = forecastDateTime;
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

    public FarmingSuggestionResult getSuggest() {
        return Suggest;
    }

    public void setSuggest(FarmingSuggestionResult suggest) {
        Suggest = suggest;
    }

    public RealTimeInfo getRealTime() {
        return RealTime;
    }

    public void setRealTime(RealTimeInfo realTime) {
        RealTime = realTime;
    }

    public List<FeaturesInfo> getFeatures() {
        return Features;
    }

    public void setFeatures(List<FeaturesInfo> features) {
        Features = features;
    }
}
