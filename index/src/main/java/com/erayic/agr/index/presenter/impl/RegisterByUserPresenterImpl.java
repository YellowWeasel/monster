package com.erayic.agr.index.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.model.impl.IndexModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.index.presenter.IRegisterByUserPresenter;
import com.erayic.agr.index.view.IRegisterByUserView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class RegisterByUserPresenterImpl implements IRegisterByUserPresenter {

    private IRegisterByUserView registerByUserView;
    @Autowired
    IIndexModel indexModel;

    public RegisterByUserPresenterImpl(IRegisterByUserView registerByUserView) {
        this.registerByUserView = registerByUserView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void userByInvite(String pass, String tel, String code, String verifyNum) {
        indexModel.userByInvite(AgrConstant.AGR_PLANT_APPID, pass, tel, code, AgrConstant.Test_PHONE, verifyNum, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                registerByUserView.registerSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                registerByUserView.showToast(msg);
            }
        });
    }

    @Override
    public void sendTelVerify(String tel) {
        indexModel.sendTelVerify(tel, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                registerByUserView.verSendSure();
                registerByUserView.showToast("验证码已发送");
            }

            @Override
            public void fail(int errCode, String msg) {
                registerByUserView.showToast(msg);
            }
        });
    }
}
