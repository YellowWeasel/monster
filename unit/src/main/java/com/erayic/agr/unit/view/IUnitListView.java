package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUnitListView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新服务列表
     */
    void refreshUnitView(List<CommonUnitListBean> list);

    /**
     * 控制设备成功
     */
    void refreshEquCtrSure(CommonUnitListBean.UnitListCtrlItemsBean bean, int position);

    /**
     * 控制设备失败
     */
    void refreshEquCtrFail(CommonUnitListBean.UnitListCtrlItemsBean bean, int position);


}
