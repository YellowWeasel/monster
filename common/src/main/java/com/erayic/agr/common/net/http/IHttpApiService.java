package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.CommonReportsByMonthBean;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsDetailBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

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
    Observable<DataBack<CommonRealTimeWeatherBean>> getRealTimeWeather();

    /**
     * 得到未来24小时气象信息
     *
     * @return DataBack
     */
    @GET("Weather/GetFeatureWeather")
    Observable<DataBack<List<CommonFutureWeatherBean>>> getFeatureWeather();

    /**
     * 获取农业气象旬报
     *
     * @param year  年
     * @param month 月
     * @return
     */
    @GET("Weather/GetWeatherTenDayReportsByMonth")
    Observable<DataBack<List<CommonReportsByMonthBean>>> getWeatherTenDayReportsByMonth(
            @Query("year") int year,
            @Query("month") int month
    );

    /**
     * 获取政策法规
     * @return
     */
    @GET("MarketInfo/GetPoliciesList")
    Observable<DataBack<List<CommonPoliciesRegulationsBean>>>  getPoliciesRegulations(
            @Query("pageIndex")int pageIndex
            ,@Query("pageSize")int pageSize
    );

    @GET("MarketInfo/GetPoliciesById")
    Observable<DataBack<CommonPoliciesRegulationsDetailBean>>  getPoliciesRegulationsDetail(
            @Query("Id")int Id
    );

    /**
     * 获取价格动态
     * @param cropId
     * @param start
     * @param end
     * @return
     */
    @GET("MarketInfo/GetMarketPrice1")
    Observable<DataBack<CommonDynamicPriceBean>> getDynamicPrices(
        @Query("cropId")int cropId,
        @Query("start")String start,
        @Query("end")String end
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
    Observable<DataBack<List<CommonMarketDynamicPriceBean>>> getDesignatedMarketDynamicPrices(
            @Query("cropId") int cropId,
            @Query("marketName")String marketName,
            @Query("start")String start,
            @Query("end")String end
    );

}
