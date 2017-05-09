package com.erayic.agr.common.net.back.api;

import java.util.Date;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：得到未来24小时气象信息
 */

public class CommonFutureWeatherBean {
        private String ForecastDateTime;
        private String ForecastKey;
        private String Value;

    public CommonFutureWeatherBean(String forecastDateTime, String forecastKey, String value) {
        ForecastDateTime = forecastDateTime;
        ForecastKey = forecastKey;
        Value = value;
    }

    public CommonFutureWeatherBean() {
    }

    public String getForecastDateTime() {
        return ForecastDateTime;
    }

    public void setForecastDateTime(String forecastDateTime) {
        ForecastDateTime = forecastDateTime;
    }

    public String getForecastKey() {
        return ForecastKey;
    }

    public void setForecastKey(String forecastKey) {
        ForecastKey = forecastKey;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
