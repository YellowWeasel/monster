package com.erayic.agr.service.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonOrderBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：订单详情
 */

public interface IOrderInfoView extends IBaseView{

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
     * 取消订单成功
     */
    void  cancelOrderSure();

    /**
     * 索要发票成功
     */
    void requestInvoiceSure();

    /**
     * 刷新列表
     */
    void refreshOrderView(CommonOrderBean bean);

}
