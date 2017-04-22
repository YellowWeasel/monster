package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.service.presenter.IServiceEntrancePresenter;
import com.erayic.agr.service.view.IServiceEntranceView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceEntrancePresenterImpl implements IServiceEntrancePresenter {

    private IServiceEntranceView serviceView;
    @Autowired
    IServerModel serverModel;

    public ServiceEntrancePresenterImpl(IServiceEntranceView serviceView) {
        this.serviceView = serviceView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getAllSystemServiceByUser() {
        serverModel.getAllSystemServiceByUser(new OnDataListener<List<ServiceBuyByUserBean>>() {
            @Override
            public void success(List<ServiceBuyByUserBean> response) {
                serviceView.refreshView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                serviceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
