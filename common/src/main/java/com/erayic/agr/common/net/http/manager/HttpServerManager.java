package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpServerService;

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
    public Observable getAllServiceByUser() {
        return serviceRequest.getAllServiceByUser();
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

}
