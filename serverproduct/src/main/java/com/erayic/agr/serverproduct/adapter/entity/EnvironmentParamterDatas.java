package com.erayic.agr.serverproduct.adapter.entity;

import android.support.v4.util.ArrayMap;

import com.erayic.agr.common.net.back.api.CommonEnvironmentParameterBean;
import com.erayic.agr.serverproduct.TextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.http.Query;

/**
 * Created by wxk on 2017/6/13.
 */

public class EnvironmentParamterDatas {
    private String BaseName;
    private GPS Gps;
    private RealTime RealTimeInfo;
    private FeatureWeather Features;
    private FarmingSuggestionResult Suggest;
    private int ErrCode;

    /**
     * 获取页面显示数据
     *
     * @param beans
     */
    public EnvironmentParamterDatas(CommonEnvironmentParameterBean beans) {
        if (beans == null) return;
        this.BaseName = beans.getBaseName();
        this.Gps = new GPS(beans.getGps().getLat(), beans.getGps().getLon(), beans.getGps().getElv());
        this.Features = new FeatureWeather();
        CommonEnvironmentParameterBean.RealTime realTime = beans.getRealTimeInfo();
        this.RealTimeInfo = new RealTime(realTime.getAppearTime(), realTime.getRain_10M()
                , realTime.getWind_Max(), realTime.getTemp_Max(), realTime.getErrCode(),
                realTime.getRain_Desc(), realTime.getWind_Desc());
        //预测数据分类
        for (CommonEnvironmentParameterBean.FeatureWeather featureWeather : beans.getFeatures()) {
            switch (featureWeather.getKey()) {
                case 0:
                    break;
                case 1://rain
                    Features.setRainForecastWeathers(parseStrDateToLong(featureWeather.getForecastDateTime()), featureWeather.getValue());
                    break;
                case 2://temp
                    Features.setTmpForecastWeathers(parseStrDateToLong(featureWeather.getForecastDateTime()), featureWeather.getValue());
                    break;
                case 3://windspeed
                    Features.setWindForecastWeathers(parseStrDateToLong(featureWeather.getForecastDateTime()), featureWeather.getValue());
                    break;
            }
        }
        //预测数据排序
        this.Features.sortFutureDatas();

        this.Suggest = new FarmingSuggestionResult();
        for (CommonEnvironmentParameterBean.SuggestionResult suggestionResult :
                beans.getSuggest().getSuggestionResultList()) {
            switch (suggestionResult.getKey()) {
                case 0:
                    this.Suggest.setSprayInsecticideSuggestion(new SuggestionResult(suggestionResult
                            .getResultIndex(), suggestionResult.getSuggestionContent()
                            , suggestionResult.getErrorCode(), suggestionResult.getKey()));
                    break;
                case 1:
                    this.Suggest.setFertilizationSuggestion(new SuggestionResult(suggestionResult
                            .getResultIndex(), suggestionResult.getSuggestionContent()
                            , suggestionResult.getErrorCode(), suggestionResult.getKey()));
                    break;
                case 2:
                    this.Suggest.setIrrigationSuggestion(new SuggestionResult(suggestionResult
                            .getResultIndex(), suggestionResult.getSuggestionContent()
                            , suggestionResult.getErrorCode(), suggestionResult.getKey()));
                    break;
                case 3:
                    this.Suggest.setHarvestSuggestion(new SuggestionResult(suggestionResult
                            .getResultIndex(), suggestionResult.getSuggestionContent()
                            , suggestionResult.getErrorCode(), suggestionResult.getKey()));
                    break;
            }
        }
        this.ErrCode = beans.getErrCode();
    }
    public long parseStrDateToLong(String strDate){
        String formatDate=strDate.substring(strDate.indexOf("(")+1,strDate.indexOf(")"));
        return Long.parseLong(formatDate);
    }


    public FeatureWeather getFeatures() {
        return Features;
    }

    public void setFeatures(FeatureWeather features) {
        Features = features;
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

    public static class GPS {
        private double Lat;
        private double Lon;
        private double Evl;

        public GPS() {

        }

        public GPS(double lat, double lon, double evl) {
            Lat = Double.parseDouble(TextUtils.FormatNumber(lat,4));
            Lon = Double.parseDouble(TextUtils.FormatNumber(lon,4));
            Evl = Double.parseDouble(TextUtils.FormatNumber(evl,4));;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double lat) {
            Lat = lat;
        }

        public double getLon() {
            return Lon;
        }

        public void setLon(double lon) {
            Lon = lon;
        }

        public double getEvl() {
            return Evl;
        }

        public void setEvl(double evl) {
            Evl = evl;
        }
    }

    public static class RealTime {
        private String AppearTime;
        private double Rain_10M;
        private String RainDesc;
        private double Wind_Max;
        private String WindDesc;
        private double Temp_Max;
        private int errCode;

        public RealTime(String appearTime, double rain_10M, double wind_Max, double temp_Max, int errCode, String rainDesc, String windDesc) {
            AppearTime = appearTime;
            Rain_10M =  Double.parseDouble(TextUtils.FormatNumber(Math.abs(rain_10M),1));
            Wind_Max = Double.parseDouble(TextUtils.FormatNumber(Math.abs(wind_Max),1));
            Temp_Max = Double.parseDouble(TextUtils.FormatNumber(Math.abs(temp_Max),1));
            this.errCode = errCode;
            this.RainDesc = rainDesc;
            this.WindDesc = windDesc;
        }

        public String getRainDesc() {
            return RainDesc;
        }

        public void setRainDesc(String rainDesc) {
            RainDesc = rainDesc;
        }

        public String getWindDesc() {
            return WindDesc;
        }

        public void setWindDesc(String windDesc) {
            WindDesc = windDesc;
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

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }
    }

    public static class FeatureWeather {
        private List<WeatherMap> TmpForecastWeathers;
        private List<WeatherMap> WindForecastWeathers;
        private List<WeatherMap> RainForecastWeathers;
        private List<String> DateList;//日期集
        private double[] rainDatas;
        private double[] tmpDatas;
        private double[] windDatas;

        private double maxRainLabel;
        private double minRainLabel;
        private double rainYInterval;
        private double maxTmpLabel;
        private double minTmpLabel;
        private double tmpYInterval;
        private double maxWindLabel;
        private double minWindLabel;
        private double windYInterval;

        public List<String> getDateList() {
            return DateList;
        }

        public void setDateList(List<String> dateList) {
            DateList = dateList;
        }

        private Comparator<WeatherMap> weatherComparator = new Comparator<WeatherMap>() {
            @Override
            public int compare(WeatherMap weatherPre, WeatherMap weatherNext) {
                if (weatherPre.getDate() > weatherNext.getDate()) {
                    return 1;
                } else if (weatherPre.getDate() < weatherNext.getDate()) {
                    return -1;
                }
                return 0;
            }
        };

        public double getMaxRainLabel() {
            return maxRainLabel;
        }

        public double getMinRainLabel() {
            return minRainLabel;
        }

        public double getRainYInterval() {
            return rainYInterval;
        }

        public double getMaxTmpLabel() {
            return maxTmpLabel;
        }

        public double getMinTmpLabel() {
            return minTmpLabel;
        }

        public double getTmpYInterval() {
            return tmpYInterval;
        }

        public double getMaxWindLabel() {
            return maxWindLabel;
        }
        public double getMinWindLabel() {
            return minWindLabel;
        }
        public double getWindYInterval() {
            return windYInterval;
        }

        public List<WeatherMap> getTmpForecastWeathers() {
            return TmpForecastWeathers;
        }

        public void setTmpForecastWeathers(long date, double tmpVal) {

            TmpForecastWeathers.add(new WeatherMap(date, Double.parseDouble(TextUtils.FormatNumber(Math.abs(tmpVal),1))));
        }

        public List<WeatherMap> getWindForecastWeathers() {
            return WindForecastWeathers;
        }

        public void setWindForecastWeathers(long date, double windVal) {
            WindForecastWeathers.add(new WeatherMap(date, Double.parseDouble(TextUtils.FormatNumber(Math.abs(windVal),1))));
        }

        public List<WeatherMap> getRainForecastWeathers() {
            return RainForecastWeathers;
        }

        public void setRainForecastWeathers(long date, double rainVal) {
            RainForecastWeathers.add(new WeatherMap(date, Double.parseDouble(TextUtils.FormatNumber(Math.abs(rainVal),1))));
        }

        public void sortFutureDatas() {
            Collections.sort(TmpForecastWeathers, weatherComparator);
            Collections.sort(RainForecastWeathers, weatherComparator);
            Collections.sort(WindForecastWeathers, weatherComparator);

            if (TmpForecastWeathers.size()>0)
            TmpForecastWeathers.remove(0);
            if (RainForecastWeathers.size()>0)
            RainForecastWeathers.remove(0);
            if (WindForecastWeathers.size()>0)
            WindForecastWeathers.remove(0);

            tmpDatas=new double[TmpForecastWeathers.size()];
            rainDatas=new double[RainForecastWeathers.size()];
            windDatas=new double[WindForecastWeathers.size()];

            for (int i=0;i<TmpForecastWeathers.size();i++){

                if (Math.abs(TmpForecastWeathers.get(i).getValue())>50){
                    tmpDatas[i]=getAverageForList(TmpForecastWeathers,i);
                }else {
                    tmpDatas[i] = TmpForecastWeathers.get(i).getValue();
                }
                TmpForecastWeathers.get(i).setValue(tmpDatas[i]);
                if (tmpDatas[i]>maxTmpLabel)maxTmpLabel=tmpDatas[i];
                if (i==0){
                    minTmpLabel=tmpDatas[i];
                }else
                if (tmpDatas[i]<minTmpLabel)minTmpLabel=tmpDatas[i];
            }
            for (int i=0;i<RainForecastWeathers.size();i++){
                if (RainForecastWeathers.get(i).getValue()<0){
                    rainDatas[i]=0;
                }else {
                    if (Math.abs(RainForecastWeathers.get(i).getValue())>30){
                        rainDatas[i]=getAverageForList(RainForecastWeathers,i);

                    }else{
                        rainDatas[i] = RainForecastWeathers.get(i).getValue();
                    }
                }
                RainForecastWeathers.get(i).setValue(rainDatas[i]);
                if (rainDatas[i]>maxRainLabel)maxRainLabel=rainDatas[i];
                if (i==0){
                    minRainLabel=rainDatas[i];
                }else
                if (rainDatas[i]<minRainLabel)minRainLabel=rainDatas[i];
            }

            for (int i=0;i<WindForecastWeathers.size();i++){
                if (WindForecastWeathers.get(i).getValue()<0){
                    windDatas[i]=0;
                }else {
                    if (Math.abs(WindForecastWeathers.get(i).getValue())>50){
                        windDatas[i]=getAverageForList(WindForecastWeathers,i);
                    }else {
                        windDatas[i] = WindForecastWeathers.get(i).getValue();
                    }
                }
                WindForecastWeathers.get(i).setValue(windDatas[i]);
                if (windDatas[i]>maxWindLabel)maxWindLabel=windDatas[i];
                if (i==0){
                    minWindLabel=windDatas[i];
                }else
                if (windDatas[i]<minWindLabel)minWindLabel=windDatas[i];

            }
            maxTmpLabel=maxTmpLabel+5;
            maxRainLabel=maxRainLabel+3;
            maxWindLabel=maxWindLabel+3;

            if (minTmpLabel-5>=0)minTmpLabel=minTmpLabel-5;
            if (minRainLabel-3>=0)minRainLabel=minRainLabel-3;
            if (minWindLabel-3>=0)minWindLabel=minWindLabel-3;
            windYInterval=(maxWindLabel-minWindLabel)/6;//windY轴间隔
            rainYInterval=(maxRainLabel-minRainLabel)/6;//rainY轴间隔
            tmpYInterval=(maxTmpLabel-minTmpLabel)/6;//tmpY轴间隔
            getDates();
        }

        public double getAverageForList(List<WeatherMap> values,int index){
                if (index==0)return 0;
                double sum=0;
                for (int i=0;i<index;i++){
                    sum=sum+values.get(i).getValue();
                }
                return Double.parseDouble(TextUtils.FormatNumber(sum/index,1));
        }

        public void getDates(){
            Calendar ca = Calendar.getInstance();
            ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), ca.get(Calendar.HOUR_OF_DAY), 0, 0);
            Date date = ca.getTime();
            ca.setTime(date);
            for (int i = 1; i <= 24; i++) {
                ca.add(Calendar.HOUR_OF_DAY, 1);
                DateList.add(new SimpleDateFormat("H:00").format(ca.getTime()));

            }
        }

        public FeatureWeather() {

            TmpForecastWeathers = new ArrayList<>();
            WindForecastWeathers = new ArrayList<>();
            RainForecastWeathers = new ArrayList<>();
            DateList = new ArrayList<>();
        }
    }

    public static class WeatherMap {
        private long Date;
        private double Value;

        public WeatherMap(long date, double value) {
            Date = date;
            Value = value;
        }

        public long getDate() {
            return Date;
        }

        public void setDate(long date) {
            Date = date;
        }

        public double getValue() {
            return Value;
        }

        public void setValue(double value) {
            Value = value;
        }
    }


    public static class FarmingSuggestionResult {
        private SuggestionResult SprayInsecticideSuggestion;
        private SuggestionResult FertilizationSuggestion;
        private SuggestionResult IrrigationSuggestion;
        private SuggestionResult HarvestSuggestion;

        public FarmingSuggestionResult() {
        }

        public FarmingSuggestionResult(SuggestionResult sprayInsecticideSuggestion, SuggestionResult fertilizationSuggestion,
                                       SuggestionResult irrigationSuggestion, SuggestionResult harvestSuggestion) {
            SprayInsecticideSuggestion = sprayInsecticideSuggestion;
            FertilizationSuggestion = fertilizationSuggestion;
            IrrigationSuggestion = irrigationSuggestion;
            HarvestSuggestion = harvestSuggestion;
        }

        public SuggestionResult getSprayInsecticideSuggestion() {
            return SprayInsecticideSuggestion;
        }

        public void setSprayInsecticideSuggestion(SuggestionResult sprayInsecticideSuggestion) {
            SprayInsecticideSuggestion = sprayInsecticideSuggestion;
        }

        public SuggestionResult getFertilizationSuggestion() {
            return FertilizationSuggestion;
        }

        public void setFertilizationSuggestion(SuggestionResult fertilizationSuggestion) {
            FertilizationSuggestion = fertilizationSuggestion;
        }

        public SuggestionResult getIrrigationSuggestion() {
            return IrrigationSuggestion;
        }

        public void setIrrigationSuggestion(SuggestionResult irrigationSuggestion) {
            IrrigationSuggestion = irrigationSuggestion;
        }

        public SuggestionResult getHarvestSuggestion() {
            return HarvestSuggestion;
        }

        public void setHarvestSuggestion(SuggestionResult harvestSuggestion) {
            HarvestSuggestion = harvestSuggestion;
        }
    }

    public static class SuggestionResult {
        /**
         * 评价指数
         */
        private int ResultIndex;
        /**
         * 建议内容
         */
        private String SuggestionContent;
        /**
         * 错误码
         */
        private int ErrorCode;
        /**
         * 操作类型
         */
        private int Key;

        public SuggestionResult(int resultIndex, String suggestionContent, int errorCode, int key) {
            ResultIndex = resultIndex;
            SuggestionContent = suggestionContent;
            ErrorCode = errorCode;
            Key = key;
        }

        public SuggestionResult() {
        }

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
