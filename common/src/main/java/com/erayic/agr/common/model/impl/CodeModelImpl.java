package com.erayic.agr.common.model.impl;

import com.erayic.agr.common.model.ICodeModel;
import com.erayic.agr.common.net.OnDataListener;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CodeModelImpl implements ICodeModel{
    @Override
    public void sendTelVerify(String tel, OnDataListener<Object> listener) {

    }

    @Override
    public void checkTelVerify(String tel, String verifyCode, OnDataListener<Object> listener) {

    }
}
