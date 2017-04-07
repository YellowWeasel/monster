package com.erayic.agr.index.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：登录Activity接口
 */

public interface ILoginView extends IBaseView {

    /**
     * 登陆成功
     */
    void loginSure();

    /**
     * 需要验证
     */
    void toCodeActivity();

    /**
     * 显示登陆加载Dialog
     */
    void showLoading();

    /**
     * 隐藏登陆加载Dialog
     */
    void dismissLoading();

}
