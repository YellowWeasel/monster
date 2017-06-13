package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchInfoView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新批次详情列表
     */
    void refreshBatchView(Object bean);

}
