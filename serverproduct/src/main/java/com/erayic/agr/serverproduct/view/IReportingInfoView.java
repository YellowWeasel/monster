package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IReportingInfoView extends IBaseView{

    /**
     * 刷新实况数据
     */
    void refreshRealDataView(CommonRealTimeWeatherBean bean);

    /**
     * 刷新24小时数据
     * @param beans
     */
    void refreshFeatureDataView(List<CommonFutureWeatherBean> beans);
    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();


}
