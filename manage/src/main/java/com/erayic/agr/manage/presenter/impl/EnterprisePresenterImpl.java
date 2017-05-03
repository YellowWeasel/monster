package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IManageModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonEntInfoBean;
import com.erayic.agr.manage.presenter.IEnterprisePresenter;
import com.erayic.agr.manage.view.IEnterpriseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class EnterprisePresenterImpl implements IEnterprisePresenter {

    private IEnterpriseView enterpriseView;
    @Autowired
    IManageModel manageModel;

    public EnterprisePresenterImpl(IEnterpriseView enterpriseView) {
        this.enterpriseView = enterpriseView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getEntInfo() {
        enterpriseView.openRefresh();
        manageModel.getEntInfo(new OnDataListener<CommonEntInfoBean>() {
            @Override
            public void success(CommonEntInfoBean response) {
                enterpriseView.clearRefresh();
                enterpriseView.refreshPersonnelView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                enterpriseView.clearRefresh();
                enterpriseView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateEntName(final String newEntName) {
        enterpriseView.showLoading();
        manageModel.updateEntName(newEntName, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                enterpriseView.dismissLoading();
                enterpriseView.updateSure(newEntName);
            }

            @Override
            public void fail(int errCode, String msg) {
                enterpriseView.dismissLoading();
                enterpriseView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

}
