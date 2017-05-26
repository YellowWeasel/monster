package com.erayic.agr.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IApiModel;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonUserInfoBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.presenter.IMainPresenter;
import com.erayic.agr.view.IMainView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class MainPresenterImpl implements IMainPresenter {

    private IMainView mainView;
    @Autowired
    IUserModel userModel;

    public MainPresenterImpl(IMainView mainView) {
        this.mainView = mainView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getUserInfo() {
        userModel.getUserInfo(new OnDataListener<CommonUserInfoBean>() {
            @Override
            public void success(CommonUserInfoBean response) {
                mainView.showToast("用户信息更新成功");
                mainView.initNetData();
            }

            @Override
            public void fail(int errCode, String msg) {
                mainView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                mainView.loadingError();
            }
        });
    }
}
