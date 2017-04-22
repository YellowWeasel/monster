package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;
import com.erayic.agr.service.presenter.IOrderByInfoPresenter;
import com.erayic.agr.service.view.IOrderInfoView;

import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByInfoPresenterImpl implements IOrderByInfoPresenter {

    private IOrderInfoView infoView;
    @Autowired
    IServerModel serverModel;

    public OrderByInfoPresenterImpl(IOrderInfoView infoView) {
        this.infoView = infoView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getSpecifyOrderDetail(String orderID) {
        infoView.openRefresh();
        serverModel.getSpecifyOrderDetail(orderID, new OnDataListener<CommonOrderBean>() {
            @Override
            public void success(CommonOrderBean response) {
                infoView.clearRefresh();
                infoView.refreshOrderView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.clearRefresh();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void cancelOrderByEntUser(String orderID) {
        infoView.showLoading();
        serverModel.cancelOrderByEntUser(orderID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                infoView.dismissLoading();
                infoView.cancelOrderSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.dismissLoading();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void requestInvoice(CommonWcfInvoiceBean orderIDs) {
        infoView.showLoading();
        serverModel.requestInvoice(orderIDs, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                infoView.dismissLoading();
                infoView.requestInvoiceSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.dismissLoading();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
