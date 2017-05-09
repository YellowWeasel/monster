package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;

import java.util.List;

import retrofit2.http.GET;
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

}
