package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.service.presenter.IServiceMarketPresenter;
import com.erayic.agr.service.view.IServiceMarketView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceMarketPresenterImpl implements IServiceMarketPresenter {

    private IServiceMarketView marketView;
    @Autowired
    IServerModel serverModel;

    public ServiceMarketPresenterImpl(IServiceMarketView marketView) {
        this.marketView = marketView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllSystemServiceByEnt() {
        marketView.openRefresh();
        serverModel.getAllSystemServiceByEnt(new OnDataListener<ServiceSystemBean>() {
            @Override
            public void success(ServiceSystemBean response) {
                marketView.clearRefresh();
                marketView.refreshServiceView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                marketView.clearRefresh();
                marketView.refreshServiceView(null);
            }
        });
    }
}
