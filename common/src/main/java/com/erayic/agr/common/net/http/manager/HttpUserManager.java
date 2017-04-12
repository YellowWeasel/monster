package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpUserService;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpUserManager {

    private static HttpUserManager manager;

    private static IHttpUserService serviceRequest;

    private HttpUserManager() {
    }

    public static HttpUserManager getInstance() {
        if (manager == null) {
            synchronized (IHttpUserService.class) {
                if (manager == null) {
                    manager = new HttpUserManager();
                    serviceRequest = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpUserService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 获取用户信息
     */
    public Observable getUserInfo() {
        return serviceRequest.getUserInfo();
    }

}
