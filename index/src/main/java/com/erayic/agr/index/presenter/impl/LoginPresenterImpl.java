package com.erayic.agr.index.presenter.impl;

import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.model.impl.IndexModelImpl;
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

    private IIndexModel indexModel;

    public LoginPresenterImpl(ILoginView loginView) {
        this.loginView = loginView;
        this.indexModel = new IndexModelImpl();
    }


    @Override
    public void login(String tel, String pass) {
        loginView.showLoading();
        indexModel.login(AgrConstant.AGR_PLANT_APPID, tel, pass, AgrConstant.Test_PHONE, new OnDataListener<Object>() {
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
                    loginView.showToast(msg);
                }
            }
        });
    }
}
