package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IDaHuaVideoView extends IBaseView {


    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 连接失败
     */
    void loginFail();

}
