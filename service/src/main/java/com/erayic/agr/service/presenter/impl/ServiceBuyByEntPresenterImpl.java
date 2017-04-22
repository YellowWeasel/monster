package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.service.presenter.IServiceBuyByEntPresenter;
import com.erayic.agr.service.view.IServiceBuyView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByEntPresenterImpl implements IServiceBuyByEntPresenter {

    private IServiceBuyView buyView;
    @Autowired
    IServerModel serverModel;

    public ServiceBuyByEntPresenterImpl(IServiceBuyView buyView) {
        this.buyView = buyView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void orderServiceByBuyOfEnt(String serviceID, int priceID, List<CommonSubServiceBean> subServiceIDs, int payMode) {
        buyView.showLoading();
        serverModel.orderServiceByBuyOfEnt(serviceID, priceID, subServiceIDs, payMode, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                buyView.dismissLoading();
                buyView.submitSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                buyView.dismissLoading();
                buyView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
