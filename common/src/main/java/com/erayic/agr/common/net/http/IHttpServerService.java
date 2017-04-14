package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.ServiceInfoByEntBean;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务相关
 */

public interface IHttpServerService {

    /**
     * 得到用户所已拥有的所有服务
     *
     * @return
     */
    @GET("Service/GetAllServiceByUser")
    Observable<DataBack<List<ServiceBuyByUserBean>>> getAllServiceByUser();

    /**
     * 得到应用系统所适用的所有服务
     *
     * @return DataBack
     */
    @GET("Service/GetAllSystemServiceByEnt")
    Observable<DataBack<ServiceSystemBean>> getAllSystemServiceByEnt();

    /**
     * 以企业身份得到一个服务详情
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/GetSpecifyServiceByEnt")
    Observable<DataBack<ServiceInfoByEntBean>> getSpecifyServiceByEnt(
            @Query("serviceID") String serviceID
    );

    /**
     * 得到服务所有的价格
     * @param serviceID 服务ID
     * @return DataBack
     */
    @GET("Service/GetAllPriceByService")
    Observable<DataBack<List<CommonPriceBean>>> getAllPriceByService(
            @Query("serviceID") String serviceID
    );

}
