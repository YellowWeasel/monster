package com.erayic.agr.serverproduct.presenter.impl;

import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;
import com.erayic.agr.serverproduct.presenter.IWeatherTenDayReportingPresenter;
import com.erayic.agr.serverproduct.view.ITenDayReportingView;

/**
 * Created by 23060 on 2017/5/9.
 */

public class WeatherTenDayReportingPresenter implements IWeatherTenDayReportingPresenter {
    private ITenDayReportingView context;
    public WeatherTenDayReportingPresenter(ITenDayReportingView mContext) {
                this.context=mContext;
    }

    @Override
    public void getWeatherTenDayReportingData() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    context.refreshTenDayReportingDatas(new WeatherTendayReportingData(null));
            }
        });
    }
}
