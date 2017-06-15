package com.erayic.agr.serverproduct.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.impl.ApiModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonEnvironmentParameterBean;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.serverproduct.adapter.entity.EnvironmentParamterDatas;
import com.erayic.agr.serverproduct.presenter.IReportingPresenter;
import com.erayic.agr.serverproduct.view.IReportingInfoView;

import java.util.List;

/**
 * Created by wxk on 2017/5/6
 */
public class ReportingPresenterImpl implements IReportingPresenter {
    private IReportingInfoView context;
    public ReportingPresenterImpl(IReportingInfoView mContext){
        this.context=mContext;
        ARouter.getInstance().inject(this);
    }
    @Autowired
    IApiModel apiModel;

    @Override
    public void getRealTimeWeather() {
        context.showLoading();
        apiModel.getRealTimeWeather(new OnDataListener<CommonEnvironmentParameterBean>() {
            @Override
            public void success(final CommonEnvironmentParameterBean response) {
                MainLooperManage.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.refreshReportingInfoView(new EnvironmentParamterDatas(response));
                        context.dismissLoading();
                    }
                });
            }
            @Override
            public void fail(int errCode, String msg) {
                context.dismissLoading();
                context.showToast(msg);
            }
        });
    }
}
