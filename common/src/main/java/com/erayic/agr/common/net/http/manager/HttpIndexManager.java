package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.IHttpIndexService;
import com.erayic.agr.common.net.http.HttpRetrofit;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpIndexManager {

    private static HttpIndexManager manager;

    private static IHttpIndexService service;

    private HttpIndexManager() {
    }

    public static HttpIndexManager getInstance() {
        if (manager == null) {
            synchronized (HttpIndexManager.class) {
                if (manager == null) {
                    manager = new HttpIndexManager();
                    service = HttpRetrofit.getReceivedCookiesRetrofit().create(IHttpIndexService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 1、登录
     */
    public Observable login(String appID, String tel, String pass, String phoneCode) {
        return service.login(appID, tel, pass, phoneCode);
    }

}