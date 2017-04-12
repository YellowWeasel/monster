package com.erayic.agr.service.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.ServiceSystemBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务市场
 */

public interface IServiceMarketView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新服务列表
     */
    void refreshServiceView(ServiceSystemBean list);

}
