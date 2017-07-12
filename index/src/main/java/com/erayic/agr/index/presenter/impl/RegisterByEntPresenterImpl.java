package com.erayic.agr.index.presenter.impl;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.index.presenter.IRegisterByEntPresenter;
import com.erayic.agr.index.view.IRegisterByEntView;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：注册Presenter
 */

public class RegisterByEntPresenterImpl implements IRegisterByEntPresenter {

    private IRegisterByEntView registerByEntView;
    @Autowired
    IIndexModel indexModel;

    public RegisterByEntPresenterImpl(IRegisterByEntView registerByEntView) {
        this.registerByEntView = registerByEntView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void enterpriseRegister(String phone, String name, String entName, String password, String verCode,String identifier) {
        indexModel.enterpriseRegister(entName, name, password, phone, AgrConstant.AGR_APPID, identifier, verCode, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                registerByEntView.registerSure();
            }

            @Override
            public void fail(int errCode,String msg) {
                registerByEntView.showToast(msg);
            }
        });
    }

    @Override
    public void sendTelVerify(String tel) {
        indexModel.sendTelVerify(tel, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                registerByEntView.verSendSure();
                registerByEntView.showToast("验证码已发送");
            }

            @Override
            public void fail(int errCode,String msg) {
                registerByEntView.showToast(msg);
            }
        });
    }

}
