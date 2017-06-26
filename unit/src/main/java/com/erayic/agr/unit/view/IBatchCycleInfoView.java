package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：生长周期预测
 */

public interface IBatchCycleInfoView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 刷新数据
     */
    void refreshBatchCycleView(CommonUnitBatchCycleBean bean);


}
