package com.erayic.agr.index.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ICodePresenter {

    /**
     * 发送验证码
     */
    void sendTelVerify(String tel);

    /**
     * 校验验证码
     */
    void checkTelVerify(String tel, String verifyCode);

}
