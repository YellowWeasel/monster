package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.model.impl.ServerModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.service.presenter.IMineServicePresenter;
import com.erayic.agr.service.view.IMineServiceView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class MimeServicePresenterImpl implements IMineServicePresenter {

    private IMineServiceView serviceView;
    @Autowired
    IServerModel serverModel;

    public MimeServicePresenterImpl(IMineServiceView serviceView) {
        this.serviceView = serviceView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getAllServiceByUser() {
        serverModel.getAllServiceByUser(new OnDataListener<List<ServiceBuyByUserBean>>() {
            @Override
            public void success(List<ServiceBuyByUserBean> response) {

            }

            @Override
            public void fail(int errCode, String msg) {
                serviceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
