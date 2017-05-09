package com.erayic.agr.serverproduct.presenter;

/**
 * Created by wxk on 2017/5/4.
 */

public interface IReportingPresenter {
    /**
     * 得到气象实况信息
     */
    void getRealTimeWeather();

    /**
     * 得到未来24小时气象信息
     */
    void getFeatureWeather();
}
