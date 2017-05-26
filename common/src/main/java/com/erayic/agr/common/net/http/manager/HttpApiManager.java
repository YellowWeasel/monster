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
    /**
     * 获取农业气象旬报
     */
    public Observable getWeatherTenDayReportsByMonth(int year, int month) {
        return apiService.getWeatherTenDayReportsByMonth(year, month);
    }
    /**
     * 获取价格动态
     */
    public Observable getDynamicPrices(int cropId,String start,String end,String serviceId) {
        return apiService.getDynamicPrices(cropId,start,end,serviceId);
    }
    /**
     * 获取政策法规列表
     */
    public Observable getPoliciesRegulations(int pageIndex,int pageSize) {
        return apiService.getPoliciesRegulations(pageIndex,pageSize);
    }
    /**
     * 获取政策法规详情
     */
    public Observable getPoliciesRegulationsDetail(int Id) {
        return apiService.getPoliciesRegulationsDetail(Id);
    }
    /**
     * 获取指定市场价格
     */
    public Observable getDesignatedMarketDynamicPrices(int cropId, String marketName,String start,String end,String serviceId) {
        return apiService.getDesignatedMarketDynamicPrices(cropId,marketName,start,end,serviceId);
    }
    /**
     * 获取农业资讯列表
     */
    public Observable getAgriculturalInfos(int pageIndex,int pageSize) {
        return apiService.getAgriculturalInfos(pageIndex,pageSize);
    }
    /**
     * 获取农业资讯列表
     */
    public Observable getAgriculturalDetailInfos(int Id) {
        return apiService.getAgriculturalDetailInfos(Id);
    }
}
