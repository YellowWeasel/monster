package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IAddUpdateBatchView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 增加批次成功
     */
    void addBatchSure();

    /**
     * 更新批次成功
     */
    void updateBatchSure();

    /**
     * 删除批次成功
     */
    void deleteBatchSure();

}
