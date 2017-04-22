package com.erayic.agr.common.net.http.manager;

import android.support.v4.util.ArrayMap;

import com.erayic.agr.common.net.back.CommonInvoiceBean;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;
import com.erayic.agr.common.net.back.ManageSeedBean;
import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpServerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpServerManager {


    private static HttpServerManager manager;

    private static IHttpServerService serviceRequest;

    private HttpServerManager() {
    }

    public static HttpServerManager getInstance() {
        if (manager == null) {
            synchronized (IHttpServerService.class) {
                if (manager == null) {
                    manager = new HttpServerManager();
                    serviceRequest = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpServerService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 得到用户所已拥有的所有服务
     */
    public Observable getAllSystemServiceByUser() {
        return serviceRequest.getAllSystemServiceByUser();
    }

    /**
     * 得到应用系统所适用的所有服务
     */
    public Observable getAllSystemServiceByEnt() {
        return serviceRequest.getAllSystemServiceByEnt();
    }

    /**
     * 以企业身份得到一个服务详情
     */
    public Observable getSpecifyServiceByEnt(String serviceID) {
        return serviceRequest.getSpecifyServiceByEnt(serviceID);
    }

    /**
     * 得到服务所有的价格
     */
    public Observable getAllPriceByService(String serviceID) {
        return serviceRequest.getAllPriceByService(serviceID);
    }

    /**
     * 得到主题服务下的所有子服务
     */
    public Observable getBelongSubService(String serviceID) {
        return serviceRequest.getBelongSubService(serviceID);
    }

    /**
     * 企业订购服务
     */
    public Observable orderServiceByBuyOfEnt(String serviceID, int priceID, List<CommonSubServiceBean> subServiceIDs, int payMode) {
        List<String> list = new ArrayList<>();
        for (CommonSubServiceBean bean : subServiceIDs)
            list.add(bean.getServiceID());
        return serviceRequest.orderServiceByBuyOfEnt(serviceID, priceID, list, payMode);
    }

    /**
     * 用户订购服务
     */
    public Observable orderServiceByBuyOfEntUser(String serviceID) {
        return serviceRequest.orderServiceByBuyOfEntUser(serviceID);
    }

    /**
     * 用户取消服务
     */
    public Observable cancelUserService(String serviceID){
        return serviceRequest.cancelUserService(serviceID);
    }

    /**
     * 得到未支付的订单列表
     */
    public Observable getUnPayOrderListByUser() {
        return serviceRequest.getUnPayOrderListByUser();
    }

    /**
     * 得到指定主题服务订购详情
     */
    public Observable getRderDetailBySubInfo(String subServiceID) {
        return serviceRequest.getRderDetailBySubInfo(subServiceID);
    }

    /**
     * 得到一个企业发票开具信息
     */
    public Observable getInvoiceTitleInfo() {
        return serviceRequest.getInvoiceTitleInfo();
    }

    /**
     * 更新一个企业发票开具信息
     */
    public Observable updateInvoiceInfo(CommonInvoiceBean bean) {
        return serviceRequest.updateInvoiceInfo(bean);
    }

    /**
     * 得到一个订单详情
     */
    public Observable getSpecifyOrderDetail(String orderID) {
        return serviceRequest.getSpecifyOrderDetail(orderID);
    }

    /**
     * 取消订单
     */
    public Observable cancelOrderByEntUser(String orderID) {
        return serviceRequest.cancelOrderByEntUser(orderID);
    }

    /**
     * 索要发票
     */
    public Observable requestInvoice(CommonWcfInvoiceBean orderIDs) {
        return serviceRequest.requestInvoice(orderIDs);
    }

    /**
     * 得到用户的历史订单列表(不含未支付）
     */
    public Observable getHistoryOrderListByUser(int pageNum, int pageSize) {
        return serviceRequest.getHistoryOrderListByUser(pageNum, pageSize);
    }


}
