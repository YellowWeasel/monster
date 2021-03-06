package com.erayic.agr.index.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.net.ErrorCode;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.index.presenter.ILoginPresenter;
import com.erayic.agr.index.view.ILoginView;


/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：登录Presenter
 */

public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginView loginView;
    @Autowired
    IIndexModel indexModel;

    public LoginPresenterImpl(ILoginView loginView) {
        this.loginView = loginView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void login(String tel, String pass, String identifier) {
        loginView.showLoading();
        indexModel.login(AgrConstant.AGR_APPID, tel, pass, identifier, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                loginView.dismissLoading();
                loginView.loginSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                loginView.dismissLoading();
                if (errCode == ErrorCode.Error_UserPhoneIdentifier)
                    loginView.toCodeActivity();
                else {
                    loginView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                }
            }
        });
    }
}
