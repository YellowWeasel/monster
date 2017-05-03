package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonUserInfoBean;
import com.erayic.agr.manage.presenter.IUserInfoPresenter;
import com.erayic.agr.manage.view.IUserInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UserInfoPresenterImpl implements IUserInfoPresenter {

    private IUserInfoView userInfoView;
    @Autowired
    IUserModel userModel;

    public UserInfoPresenterImpl(IUserInfoView userInfoView) {
        this.userInfoView = userInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getUserInfo() {
        userInfoView.openRefresh();
        userModel.getUserInfo(new OnDataListener<CommonUserInfoBean>() {
            @Override
            public void success(CommonUserInfoBean response) {
                userInfoView.clearRefresh();
                userInfoView.refreshUserInfoView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                userInfoView.clearRefresh();
                userInfoView.refreshUserInfoView(null);
                userInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateUserName(final String userName) {
        userInfoView.showLoading();
        userModel.updateUserName(userName, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                userInfoView.dismissLoading();
                userInfoView.updateSure(userName);
            }

            @Override
            public void fail(int errCode, String msg) {
                userInfoView.dismissLoading();
                userInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
