package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonInvoiceBean;
import com.erayic.agr.service.presenter.IInvoiceSettingPresenter;
import com.erayic.agr.service.view.IInvoiceSettingView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class InvoiceSettingPresenterImpl implements IInvoiceSettingPresenter {

    private IInvoiceSettingView invoiceSettingView;
    @Autowired
    IServerModel serverModel;

    public InvoiceSettingPresenterImpl(IInvoiceSettingView invoiceSettingView) {
        this.invoiceSettingView = invoiceSettingView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getInvoiceTitleInfo() {
        invoiceSettingView.showLoading();
        serverModel.getInvoiceTitleInfo(new OnDataListener<CommonInvoiceBean>() {
            @Override
            public void success(CommonInvoiceBean response) {
                invoiceSettingView.dismissLoading();
                invoiceSettingView.updateView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                invoiceSettingView.dismissLoading();
                invoiceSettingView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                invoiceSettingView.getDataFailure();
            }
        });
    }

    @Override
    public void updateInvoiceInfo(CommonInvoiceBean bean) {
        invoiceSettingView.showLoading();
        serverModel.updateInvoiceInfo(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                invoiceSettingView.dismissLoading();
                invoiceSettingView.submitSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                invoiceSettingView.dismissLoading();
                invoiceSettingView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
