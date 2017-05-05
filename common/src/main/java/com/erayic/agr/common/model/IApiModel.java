package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.api.CommonFertureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;

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
    void getRealTimeWeather(OnDataListener<CommonRealTimeWeatherBean> listener);

    /**
     * 得到未来24小时气象信息
     */
    void getFeatureWeather(OnDataListener<List<CommonFertureWeatherBean>> listener);

}
