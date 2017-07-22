package com.erayic.agr.serverproduct.presenter.impl;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonReportsByMonthBean;
import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;
import com.erayic.agr.serverproduct.presenter.IWeatherTenDayReportingPresenter;
import com.erayic.agr.serverproduct.view.ITenDayReportingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxk on 2017/5/9.
 */

public class WeatherTenDayReportingPresenterImpl implements IWeatherTenDayReportingPresenter {
    private ITenDayReportingView context;
    @Autowired
    IApiModel apiModel;

    public WeatherTenDayReportingPresenterImpl(ITenDayReportingView mContext) {
        this.context = mContext;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getWeatherTenDayReportingData(int year, int month) {
        context.showLoading();
        apiModel.getWeatherTenDayReportsByMonth(year, month, new OnDataListener<List<CommonReportsByMonthBean>>() {
            @Override
            public void success(final List<CommonReportsByMonthBean> response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<WeatherTendayReportingData> reportingDatas = new ArrayList<WeatherTendayReportingData>();
                        if (response != null)
                            for (CommonReportsByMonthBean bean : response)
                                reportingDatas.add(new WeatherTendayReportingData(bean));
                        else
                            context.showToast("未检测到数据");
                        context.refreshTenDayReportingDatas(reportingDatas);
                        context.dismissLoading();
                    }
                });
            }

            @Override
            public void fail(final int errCode, final String msg) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.dismissLoading();
                        context.showToast(msg);
                    }
                });
            }
        });
    }
}
