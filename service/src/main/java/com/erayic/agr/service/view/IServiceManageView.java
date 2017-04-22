package com.erayic.agr.service.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IServiceManageView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 更新开关状态
     */
    void updateSwitchFailure();

    /**
     * 更新开关状态
     */
    void updateSwitchStatue();

    /**
     * 刷新列表
     */
    void refreshView(List<ServiceBuyByUserBean> list);

}
