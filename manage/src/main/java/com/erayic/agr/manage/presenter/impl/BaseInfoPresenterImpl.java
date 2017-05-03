package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IManageModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseInfoBean;
import com.erayic.agr.manage.presenter.IBaseInfoPresenter;
import com.erayic.agr.manage.view.IBaseInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BaseInfoPresenterImpl implements IBaseInfoPresenter {

    private IBaseInfoView baseInfoView;
    @Autowired
    IManageModel manageModel;

    public BaseInfoPresenterImpl(IBaseInfoView baseInfoView) {
        this.baseInfoView = baseInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getBaseInfo(String baseID) {
        baseInfoView.openRefresh();
        manageModel.getBaseInfo(baseID, new OnDataListener<CommonBaseInfoBean>() {
            @Override
            public void success(CommonBaseInfoBean response) {
                baseInfoView.clearRefresh();
                baseInfoView.refreshBaseInfoView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                baseInfoView.clearRefresh();
                baseInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void addUnit(String baseID, String unitName) {
        baseInfoView.showLoading();
        manageModel.addUnit(baseID, unitName, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                baseInfoView.dismissLoading();
                baseInfoView.updateSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                baseInfoView.dismissLoading();
                baseInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateBaseInfo(String baseID, String baseName, String descript) {
        baseInfoView.showLoading();
        manageModel.updateBaseInfo(baseID, baseName, descript, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                baseInfoView.dismissLoading();
                baseInfoView.updateSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                baseInfoView.dismissLoading();
                baseInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
