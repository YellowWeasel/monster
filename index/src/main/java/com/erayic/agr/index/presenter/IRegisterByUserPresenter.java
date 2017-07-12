package com.erayic.agr.index.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IRegisterByUserPresenter {

    /**
     * 邀请码注册
     */
    void userByInvite(String pass, String tel, String code, String verifyNum,String identifier);

    /**
     * 发送验证码
     */
    void sendTelVerify(String tel);

}
