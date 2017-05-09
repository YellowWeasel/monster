package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wxk on 2017/5/6.
 */

public class FeatureForecastDatas {
    private List<ForecastDatas> FeartureBeans;
    private String[] strDates;

    public FeatureForecastDatas() {
        FeartureBeans = new ArrayList<>();
        strDates=new String[24];
    }

    public String[] getStrDates() {
        return strDates;
    }

    public void setStrDates(String[] strDates) {
        this.strDates = strDates;
    }

    public FeatureForecastDatas InitFeature(List<CommonFutureWeatherBean> beans) {
        if (beans == null) return this;
        Calendar ca = Calendar.getInstance();
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), ca.get(Calendar.HOUR_OF_DAY), 0, 0);
        Date date = ca.getTime();
        ca.setTime(date);
        for (int i = 1; i <= 24; i++) {
            ca.add(Calendar.HOUR_OF_DAY, 1);
            strDates[i-1]=String.valueOf(ca.getTime().getHours())+"时";
            ForecastDatas foreDatas = new ForecastDatas();
            foreDatas.setAppearTime(ca.getTime().toString());
            foreDatas.setHumi(GetDatas(ca.getTime(), beans, "0"));
            foreDatas.setRain(GetDatas(ca.getTime(), beans, "1"));
            foreDatas.setTemperature(GetDatas(ca.getTime(), beans, "2"));
            foreDatas.setWindSpeed(GetDatas(ca.getTime(), beans, "3"));
            foreDatas.setWindDirect(GetDatas(ca.getTime(), beans, "4"));
            this.FeartureBeans.add(foreDatas);
        }
        return this;
    }

    public double GetDatas(Date targetDate, List<CommonFutureWeatherBean> beans, String sort) {

        for (CommonFutureWeatherBean bean : beans) {
            String strDate = bean.getForecastDateTime();
            String formatDate = strDate.substring(strDate.indexOf("(") + 1, strDate.indexOf(")"));
            Date date = new Date(Long.valueOf(formatDate));
            if (date.getYear()==targetDate.getYear()
                    &&date.getMonth()==targetDate.getMonth()
                    &&date.getDay()==targetDate.getDay()
                    &&date.getHours()==targetDate.getHours()
                    && bean.getForecastKey().equals(sort)) {
                return format2(Double.valueOf(bean.getValue()));
            }
        }
        return 0;
    }

    public  double format2(double value) {
        BigDecimal format=new BigDecimal(value);
        return format.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public List<ForecastDatas> getFeartureBeans() {
        return FeartureBeans;
    }

    public void setFeartureBeans(List<ForecastDatas> feartureBeans) {
        FeartureBeans = feartureBeans;
    }

    public class ForecastDatas {
        public String AppearTime;
        public double Humi;//湿度
        public double Temperature;//温度
        public double WindSpeed;//风速
        public double WindDirect;//风向
        public double Rain;//雨

        public ForecastDatas() {
        }

        public ForecastDatas(String appearTime, double humi, double temperature,
                             double windSpeed, double windDirect, double rain) {
            AppearTime = appearTime;
            Humi = humi;
            Temperature = temperature;
            WindSpeed = windSpeed;
            WindDirect = windDirect;
            Rain = rain;
        }

        public String getAppearTime() {
            return AppearTime;
        }

        public void setAppearTime(String appearTime) {
            AppearTime = appearTime;
        }

        public double getHumi() {
            return Humi;
        }

        public void setHumi(double humi) {
            Humi = humi;
        }

        public double getTemperature() {
            return Temperature;
        }

        public void setTemperature(double temperature) {
            Temperature = temperature;
        }

        public double getWindSpeed() {
            return WindSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            WindSpeed = windSpeed;
        }

        public double getWindDirect() {
            return WindDirect;
        }

        public void setWindDirect(double windDirect) {
            WindDirect = windDirect;
        }

        public double getRain() {
            return Rain;
        }

        public void setRain(double rain) {
            Rain = rain;
        }
    }
}
