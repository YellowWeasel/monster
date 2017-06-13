package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonEnvironmentParameterBean;

import java.util.List;

/**
 * Created by wxk on 2017/6/13.
 */

public class EnvironmentParamterDatas {
    public String BaseName;
    public CommonEnvironmentParameterBean.GPS Gps;
    public CommonEnvironmentParameterBean.RealTime RealTimeInfo;
    public List<CommonEnvironmentParameterBean.FeatureWeather> Features;
    public CommonEnvironmentParameterBean.FarmingSuggestionResult Suggest;
    public int ErrCode ;
    public static class GPS {

    }

    public static class RealTime {

    }

    public static class FeatureWeather {

    }

    public static class FarmingSuggestionResult{

    }

}
