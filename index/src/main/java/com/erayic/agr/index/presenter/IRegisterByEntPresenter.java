package com.erayic.agr.index.presenter;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：注册Presenter接口
 */

public interface IRegisterByEntPresenter {

    /**
     * 企业注册
     */
    void enterpriseRegister(String tel, String userName, String entName, String password, String verCode);

    /**
     * 发送验证码
     */
    void sendTelVerify(String tel);

}
