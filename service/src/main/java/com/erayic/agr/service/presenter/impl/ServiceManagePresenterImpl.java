package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.service.presenter.IServiceManagePresenter;
import com.erayic.agr.service.view.IServiceManageView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceManagePresenterImpl implements IServiceManagePresenter {

    private IServiceManageView serviceView;
    @Autowired
    IServerModel serverModel;

    public ServiceManagePresenterImpl(IServiceManageView serviceView) {
        this.serviceView = serviceView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getAllSystemServiceByUser() {
        serviceView.openRefresh();
        serverModel.getAllSystemServiceByUser(new OnDataListener<List<ServiceBuyByUserBean>>() {
            @Override
            public void success(List<ServiceBuyByUserBean> response) {
                serviceView.clearRefresh();
                serviceView.refreshView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                serviceView.clearRefresh();
                serviceView.refreshView(null);
                serviceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void orderServiceByBuyOfEntUser(String serviceID) {
        serviceView.showLoading();
        serverModel.orderServiceByBuyOfEntUser(serviceID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                serviceView.dismissLoading();
                serviceView.updateSwitchStatue();
            }

            @Override
            public void fail(int errCode, String msg) {
                serviceView.dismissLoading();
                serviceView.updateSwitchFailure();
                serviceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void cancelUserService(String serviceID) {
        serviceView.showLoading();
        serverModel.cancelUserService(serviceID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                serviceView.dismissLoading();
                serviceView.updateSwitchStatue();
            }

            @Override
            public void fail(int errCode, String msg) {
                serviceView.dismissLoading();
                serviceView.updateSwitchFailure();
                serviceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
