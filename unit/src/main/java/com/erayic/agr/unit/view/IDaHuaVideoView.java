package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;

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
     * 获取信息成功并执行连接
     */
    void loadingConnection(CommonMonitorInfoEntity entity);

    /**
     * 连接失败
     */
    void loginFail();

}
