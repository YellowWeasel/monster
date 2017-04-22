package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.service.presenter.IOrderByPayingPresenter;
import com.erayic.agr.service.view.IOrderByPayingView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByPayingPresenterImpl implements IOrderByPayingPresenter {

    private IOrderByPayingView payingView;
    @Autowired
    IServerModel serverModel;

    public OrderByPayingPresenterImpl(IOrderByPayingView payingView) {
        this.payingView = payingView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getUnPayOrderListByUser() {
        payingView.openRefresh();
        serverModel.getUnPayOrderListByUser(new OnDataListener<List<CommonOrderBean>>() {
            @Override
            public void success(List<CommonOrderBean> response) {
                payingView.clearRefresh();
                payingView.refreshOrderView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                payingView.clearRefresh();
                payingView.refreshOrderView(null);
                payingView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
