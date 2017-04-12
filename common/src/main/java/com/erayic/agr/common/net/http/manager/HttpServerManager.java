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

    public Observable getAllSystemServiceByEnt(){
        return serviceRequest.getAllSystemServiceByEnt();
    }

}
