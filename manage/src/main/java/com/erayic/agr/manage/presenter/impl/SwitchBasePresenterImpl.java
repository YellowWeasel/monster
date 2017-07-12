package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.manage.presenter.ISwitchBasePresenter;
import com.erayic.agr.manage.view.ISwitchBaseView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class SwitchBasePresenterImpl implements ISwitchBasePresenter {

    private ISwitchBaseView switchBaseView;
    @Autowired
    IUserModel userModel;

    public SwitchBasePresenterImpl(ISwitchBaseView switchBaseView) {
        this.switchBaseView = switchBaseView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getBaseListByUser() {
        switchBaseView.showLoading();
        userModel.getBaseListByUser(new OnDataListener<List<CommonBaseListBean>>() {
            @Override
            public void success(List<CommonBaseListBean> response) {
                switchBaseView.dismissLoading();
                switchBaseView.refreshViewData(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                switchBaseView.dismissLoading();
                switchBaseView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void changeBase(String newBaseID) {
        switchBaseView.showLoading();
        userModel.changeBase(newBaseID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                switchBaseView.dismissLoading();
                switchBaseView.switchBaseSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                switchBaseView.dismissLoading();
                switchBaseView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
