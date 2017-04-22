package com.erayic.agr.service.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonInvoiceBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IInvoiceSettingView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 更新UI数据
     */
    void updateView(CommonInvoiceBean bean);

    /**
     * 提交成功
     */
    void submitSure();

    /**
     * 获取失败
     */
    void getDataFailure();

}
