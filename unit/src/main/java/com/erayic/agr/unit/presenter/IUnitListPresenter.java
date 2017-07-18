package com.erayic.agr.unit.presenter;

import com.erayic.agr.common.net.back.unit.CommonUnitListBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUnitListPresenter {

    /**
     * 得到所有的管理单元信息
     */
    void getAllUnit();

    /**
     * 控制设备
     */
    void opeCtrDevice(CommonUnitListBean.UnitListCtrlItemsBean bean, int position, int cmd);

    /**
     * 得到控制设备子类型状态
     */
    void getCtrlItemStatus(CommonUnitListBean.UnitListCtrlItemsBean bean, int position);
}
