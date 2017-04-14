package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.service.presenter.IServicePriceByEntPresenter;
import com.erayic.agr.service.view.IServicePriceByEntView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServicePriceByEntPrensenterImpl implements IServicePriceByEntPresenter {

    private IServicePriceByEntView view;
    @Autowired
    IServerModel serverModel;

    public ServicePriceByEntPrensenterImpl(IServicePriceByEntView view) {
        this.view = view;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllPriceByService(String serviceID) {
        view.openRefresh();
        serverModel.getAllPriceByService(serviceID, new OnDataListener<List<CommonPriceBean>>() {
            @Override
            public void success(List<CommonPriceBean> response) {
                view.clearRefresh();
                view.refreshServiceView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                view.clearRefresh();
                view.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
