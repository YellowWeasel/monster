package com.erayic.agr.index.presenter.impl;

import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.model.impl.IndexModelImpl;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.index.presenter.ICodePresenter;
import com.erayic.agr.index.view.ICodeView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CodePresenterImpl implements ICodePresenter {

    private ICodeView codeView;

    private IIndexModel indexModel;

    public CodePresenterImpl(ICodeView codeView) {
        this.codeView = codeView;
        this.indexModel = new IndexModelImpl();
    }

    @Override
    public void sendTelVerify(String tel) {
        indexModel.sendTelVerify(tel, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                codeView.showToast(response.toString());
            }

            @Override
            public void fail(int errCode, String msg) {
                codeView.showToast(msg);
            }
        });
    }

    @Override
    public void checkTelVerify(String tel, String verifyCode) {
        indexModel.checkTelVerify(AgrConstant.AGR_PLANT_APPID, tel, AgrConstant.Test_PHONE, verifyCode, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                codeView.showToast("校验成功，正在返回重新登录");
                codeView.checkSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                codeView.showToast(msg);
            }
        });
    }
}
