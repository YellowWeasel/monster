package com.erayic.agr.serverproduct.view;

import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;

import java.util.Date;
import java.util.List;

/**
 * Created by 23060 on 2017/5/8.
 */

public interface ITenDayReportingView {
    void refreshTenDayReportingDatas(List<WeatherTendayReportingData> beans);
    void updateTenDayReportingDatas(Date date);
    void showLoading();
    void dismissLoading();
    void showToast();
}
