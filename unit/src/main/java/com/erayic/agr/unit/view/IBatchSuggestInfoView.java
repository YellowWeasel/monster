package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：生长适应性详情
 */

public interface IBatchSuggestInfoView extends IBaseView{

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
    void refreshBatchSuggestView(Object object);
}
