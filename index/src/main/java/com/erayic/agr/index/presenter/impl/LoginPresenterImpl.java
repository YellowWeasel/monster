package com.erayic.agr.index.presenter.impl;

import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.model.impl.IndexModelImpl;
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
    public void login(String appID, String tel, String pass, String phoneCode) {
        indexModel.login(appID, tel, pass, phoneCode, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {

            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}
