package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.api.CommonEnvironmentParameterBean;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.serverproduct.adapter.entity.EnvironmentParamterDatas;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IReportingInfoView extends IBaseView{
    /**
     * 刷新环境数据
     * @param datas
     */
    void refreshReportingInfoView(EnvironmentParamterDatas datas);
    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();


}
