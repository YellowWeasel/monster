package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonReportsByMonthBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalInfoBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalinfoDetailBean;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsDetailBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务详细产品相关接口
 */

public interface IHttpApiService {

    /**
     * 得到气象实况信息
     *
     * @return DataBack
     */
    @GET("Weather/GetRealTimeWeather")
    Flowable<DataBack<CommonRealTimeWeatherBean>> getRealTimeWeather();

    /**
     * 得到未来24小时气象信息
     *
     * @return DataBack
     */
    @GET("Weather/GetFeatureWeather")
    Flowable<DataBack<List<CommonFutureWeatherBean>>> getFeatureWeather();

    /**
     * 获取农业气象旬报
     *
     * @param year  年
     * @param month 月
     * @return
     */
    @GET("Weather/GetWeatherTenDayReportsByMonth")
    Flowable<DataBack<List<CommonReportsByMonthBean>>> getWeatherTenDayReportsByMonth(
            @Query("year") int year,
            @Query("month") int month
    );
    /**
     * 获取政策法规
     * @return
     */
    @GET("MarketInfo/GetPoliciesList")
    Flowable<DataBack<List<CommonPoliciesRegulationsBean>>>  getPoliciesRegulations(
            @Query("pageIndex")int pageIndex
            ,@Query("pageSize")int pageSize
    );
    @GET("MarketInfo/GetPoliciesById")
    Flowable<DataBack<CommonPoliciesRegulationsDetailBean>>  getPoliciesRegulationsDetail(
            @Query("Id")int Id
    );
    /**
     * 获取价格动态
     * @param cropId
     * @param start
     * @param end
     * @return
     */
    @GET("MarketInfo/GetMarketPrice")
    Flowable<DataBack<CommonDynamicPriceBean>> getDynamicPrices(
        @Query("cropId")int cropId,
        @Query("start")String start,
        @Query("end")String end,
        @Query("serverId") String serviceId
    );
    /**
     * 获取指定市场价格
     * @param cropId
     * @param marketName
     * @param start
     * @param end
     * @return
     */
    @GET("MarketInfo/GetPriceByMarketName")
    Flowable<DataBack<List<CommonMarketDynamicPriceBean>>> getDesignatedMarketDynamicPrices(
            @Query("cropId") int cropId,
            @Query("marketName")String marketName,
            @Query("start")String start,
            @Query("end")String end,
            @Query("serverId") String serviceId
    );

    /**
     * 获取农业资讯列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("MarketInfo/GetAgriInfoList")
    Flowable<DataBack<List<CommonAgriculturalInfoBean>>> getAgriculturalInfos(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize")int pageSize
    );

    /**
     * 获取农业资讯详情
     * @param Id
     * @return
     */
    @GET("MarketInfo/GetAgriInfoById")
    Flowable<DataBack<CommonAgriculturalinfoDetailBean>> getAgriculturalDetailInfos(
            @Query("Id") int Id
    );
}
