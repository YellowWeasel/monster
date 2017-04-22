package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonInvoiceBean;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;
import com.erayic.agr.common.net.back.ServiceInfoByEntBean;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IServerModel extends IProvider {

    /**
     * 得到用户所拥有的所有服务
     */
    void getAllSystemServiceByUser(OnDataListener<List<ServiceBuyByUserBean>> listener);

    /**
     * 得到系统所有的服务信息
     */
    void getAllSystemServiceByEnt(OnDataListener<ServiceSystemBean> listener);

    /**
     * 以企业身份得到一个服务详情
     */
    void getSpecifyServiceByEnt(String serviceID, OnDataListener<ServiceInfoByEntBean> listener);

    /**
     * 得到服务所有的价格
     */
    void getAllPriceByService(String serviceID, OnDataListener<List<CommonPriceBean>> listener);

    /**
     * 得到主题服务下的所有子服务
     */
    void getBelongSubService(String serviceID, OnDataListener<List<Object>> listener);

    /**
     * 企业订购服务
     */
    void orderServiceByBuyOfEnt(String serviceID, int priceID, List<CommonSubServiceBean> subServiceIDs, int payMode, OnDataListener<Object> listener);

    /**
     * 用户订购服务
     */
    void orderServiceByBuyOfEntUser(String serviceID,OnDataListener<Object> listener);

    /**
     * 用户取消服务
     */
    void cancelUserService(String serviceID,OnDataListener<Object> listener);

    /**
     * 得到未支付的订单列表
     */
    void getUnPayOrderListByUser(OnDataListener<List<CommonOrderBean>> listener);

    /**
     * 得到指定主题服务订购详情
     */
    void getRderDetailBySubInfo(String subServiceID, OnDataListener<List<CommonSubServiceBean>> listener);

    /**
     * 得到一个企业发票开具信息
     */
    void getInvoiceTitleInfo(OnDataListener<CommonInvoiceBean> listener);

    /**
     * 更新一个企业发票开具信息
     */
    void updateInvoiceInfo(CommonInvoiceBean bean, OnDataListener<Object> listener);

    /**
     * 得到一个订单详情
     */
    void getSpecifyOrderDetail(String orderID, OnDataListener<CommonOrderBean> listener);

    /**
     * 取消订单
     */
    void cancelOrderByEntUser(String orderID, OnDataListener<Object> listener);

    /**
     * 索要发票
     */
    void requestInvoice(CommonWcfInvoiceBean orderIDs, OnDataListener<Object> listener);

    /**
     * 得到用户的历史订单列表(不含未支付）
     */
    void getHistoryOrderListByUser(int pageNum, int pageSize, OnDataListener<List<CommonOrderBean>> listener);
}
