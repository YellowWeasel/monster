package com.erayic.agr.service.presenter;

import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IOrderByInfoPresenter {

    /**
     * 获取订单详情
     */
    void getSpecifyOrderDetail(String orderID);

    /**
     * 取消订单
     */
    void cancelOrderByEntUser(String orderID);

    /**
     * 索要发票
     */
    void requestInvoice(CommonWcfInvoiceBean orderIDs);
}
