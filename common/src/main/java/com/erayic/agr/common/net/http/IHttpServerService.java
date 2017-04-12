package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;

import retrofit2.http.GET;
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

}
