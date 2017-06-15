package com.erayic.agr.common.model;

import android.view.View;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonReportsByMonthBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalInfoBean;
import com.erayic.agr.common.net.back.api.CommonAgriculturalinfoDetailBean;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonEnvironmentParameterBean;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsDetailBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IApiModel extends IProvider {

    /**
     * 得到气象实况信息
     */
    void getRealTimeWeather(OnDataListener<CommonEnvironmentParameterBean> listener);

    /**
     * 得到未来24小时气象信息
     */
    void getFeatureWeather(OnDataListener<List<CommonFutureWeatherBean>> listener);

    /**
     * 获取农业气象旬报
     */
    void getWeatherTenDayReportsByMonth(int year, int month, OnDataListener<List<CommonReportsByMonthBean>> listener);
    /**
     * 获取政策法规
     */
    void getPoliciesRegulations(int pageIndex,int pageSize,OnDataListener<List<CommonPoliciesRegulationsBean>> listener);

    /**
     * 获取动态价格
     */
    void getDynamicPrice(int cropId,String start,String end,String serviceId,OnDataListener<CommonDynamicPriceBean> listener);

    /**
     *   获取政策法规详情
     */
    void getPoliciesRegulationsDetail(int Id, OnDataListener<CommonPoliciesRegulationsDetailBean> listener);

    /**
     * 获取指定市场价格
     */
    void getDesignatedMarketDynamicPrices(int cropId, String marketName,String start,String end,String serviceId,final OnDataListener<List<CommonMarketDynamicPriceBean>> listener);

    /**
     * 获取农业资讯详情
     * @param Id
     * @param listener
     */
    void getAgriculturalDetailInfos(int Id, OnDataListener<CommonAgriculturalinfoDetailBean> listener);

    /**
     * 获取农业资讯列表
     * @param pageIndex
     * @param pageSize
     * @param listener
     */
    void getAgriculturalInfos(int pageIndex,int pageSize, OnDataListener<List<CommonAgriculturalInfoBean>> listener);
}
