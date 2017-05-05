package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpApiService;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpApiManager {


    private static HttpApiManager manager;

    private static IHttpApiService apiService;

    private HttpApiManager() {
    }

    public static HttpApiManager getInstance() {
        if (manager == null) {
            synchronized (IHttpApiService.class) {
                if (manager == null) {
                    manager = new HttpApiManager();
                    apiService = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpApiService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 得到气象实况信息
     */
    public Observable getRealTimeWeather() {
        return apiService.getRealTimeWeather();
    }

    /**
     * 得到未来24小时气象信息
     */
    public Observable getFeatureWeather() {
        return apiService.getFeatureWeather();
    }
}
