package com.erayic.agr.common.model;

import com.erayic.agr.common.net.OnDataListener;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ICodeModel {

    /**
     * 发送验证码
     */
    void sendTelVerify(String tel, OnDataListener<Object> listener);

    /**
     * 校验验证码
     */
    void checkTelVerify(String tel, String verifyCode, OnDataListener<Object> listener);

}
