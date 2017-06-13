package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonInvoiceBean;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;
import com.erayic.agr.common.net.back.ServiceInfoByEntBean;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务相关
 */

public interface IHttpServerService {

    /**
     * 得到用户所已拥有的所有服务
     *
     * @return DataBack
     */
    @GET("Service/GetAllSystemServiceByUser")
    Flowable<DataBack<List<ServiceBuyByUserBean>>> getAllSystemServiceByUser();

    /**
     * 得到应用系统所适用的所有服务
     *
     * @return DataBack
     */
    @GET("Service/GetAllSystemServiceByEnt")
    Flowable<DataBack<ServiceSystemBean>> getAllSystemServiceByEnt();

    /**
     * 以企业身份得到一个服务详情
     *
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/GetSpecifyServiceByEnt")
    Flowable<DataBack<ServiceInfoByEntBean>> getSpecifyServiceByEnt(
            @Query("serviceID") String serviceID
    );

    /**
     * 得到服务所有的价格
     *
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/GetAllPriceByService")
    Flowable<DataBack<List<CommonPriceBean>>> getAllPriceByService(
            @Query("serviceID") String serviceID
    );

    /**
     * 得到主题服务下的所有子服务
     *
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/GetBelongSubService")
    Flowable<DataBack<List<Object>>> getBelongSubService(
            @Query("serviceID") String serviceID
    );

    /**
     * 4、企业订购服务
     *
     * @param serviceID 服务ID
     * @param priceID   价格ID（免费、试用：-1）
     * @param options   子服务ID集合
     * @param payMode   支付方式（EnumPayType类型）
     * @return DataBack
     */
    @GET("Service/OrderServiceByBuyOfEnt")
    Flowable<DataBack<Object>> orderServiceByBuyOfEnt(
            @Query("serviceID") String serviceID,
            @Query("priceID") int priceID,
            @Query("subServiceIDs") List<String> options,
            @Query("payMode") int payMode
    );

    /**
     * 用户订购服务
     *
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/OrderServiceByBuyOfEntUser")
    Flowable<DataBack<Object>> orderServiceByBuyOfEntUser(
            @Query("serviceID") String serviceID
    );

    /**
     * 取消用户服务
     *
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/CancelUserService")
    Flowable<DataBack<Object>> cancelUserService(
            @Query("serviceID") String serviceID
    );

    /**
     * 得到未支付的订单列表
     *
     * @return DataBack
     */
    @GET("Service/GetUnPayOrderListByUser")
    Flowable<DataBack<List<CommonOrderBean>>> getUnPayOrderListByUser();

    /**
     * 得到指定主题服务订购详情
     *
     * @param subServiceID 主题服务ID
     * @return DataBack
     */
    @GET("Service/GetrderDetailBySubInfo")
    Flowable<DataBack<List<CommonSubServiceBean>>> getRderDetailBySubInfo(
            @Query("subServiceID") String subServiceID
    );

    /**
     * 得到一个企业发票开具信息
     *
     * @return
     */
    @GET("Service/GetInvoiceTitleInfo")
    Flowable<DataBack<CommonInvoiceBean>> getInvoiceTitleInfo();

    /**
     * 更新一个企业发票开具信息
     *
     * @param bean 发票内容
     * @return DataBack
     */
    @POST("Service/UpdateInvoiceInfo")
    Flowable<DataBack<Object>> updateInvoiceInfo(
            @Body CommonInvoiceBean bean
    );

    /**
     * 得到一个订单详情
     *
     * @param orderID 订单ID
     * @return DataBack
     */
    @GET("Service/GetSpecifyOrderDetail")
    Flowable<DataBack<CommonOrderBean>> getSpecifyOrderDetail(
            @Query("orderID") String orderID
    );

    /**
     * 取消订单
     *
     * @param orderID 欲取消的订单ID
     * @return DataBack
     */
    @GET("Service/CancelOrderByEntUser")
    Flowable<DataBack<Object>> cancelOrderByEntUser(
            @Query("orderID") String orderID
    );

    /**
     * 索要发票
     *
     * @param orderIDs 欲需要发票的订单集合（键值对集合，key=订单ID,value=订单价格）
     * @return DataBack
     */
    @POST("Service/RequestInvoice")
    Flowable<DataBack<Object>> requestInvoice(
            @Body CommonWcfInvoiceBean orderIDs
    );


    /**
     * 得到用户的历史订单列表(不含未支付）
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return
     */
    @GET("Service/GetHistoryOrderListByUser")
    Flowable<DataBack<List<CommonOrderBean>>> getHistoryOrderListByUser(
            @Query("pageNum") int pageNum,
            @Query("pageSize") int pageSize
    );

}
