package com.erayic.agr.index.presenter;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：登录Presenter接口
 */

public interface ILoginPresenter {
    /**
     * 用户登录
     *
     * @param appID     应用系统ID
     * @param tel       电话号码
     * @param pass      用户密码
     * @param phoneCode 手机识别码
     */
    void login(String appID, String tel, String pass, String phoneCode);

}
