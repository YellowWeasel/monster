package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IDeviceModel extends IProvider {

    /**
     * 操作控制设备
     */
   void opeCtrDevice(String serialNum, int cmd, int passNum, int type,OnDataListener<Object> listener);

    /**
     * 得到控制设备子类型状态
     */
    void getCtrlItemStatus(String serialNum, int passNum, int type,OnDataListener<Object> listener);

    /**
     * 得到设备信息
     */
    void getMonitorInfo(String serialNum, OnDataListener<CommonMonitorInfoEntity> listener);

}
