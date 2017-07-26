package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.device.CommonCtrlDeviceInfoEntity;
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
    void opeCtrDevice(String serialNum, int cmd, int passNum, int type, OnDataListener<Object> listener);

    /**
     * 得到控制设备子类型状态
     */
    void getCtrlItemStatus(String serialNum, int passNum, int type, OnDataListener<Object> listener);

    /**
     * 得到设备信息(获取视频接口)
     */
    void getMonitorInfo(String serialNum, OnDataListener<CommonMonitorInfoEntity> listener);

    /**
     * 远程重启控制设备
     */
    void resetDevice(String serialNum, OnDataListener<Object> listener);

    /**
     * 设置峰鸣器状态
     */
    void setCtrlBuzzer(String serialNum, boolean isOpen,OnDataListener<Object> listener);

    /**
     * 设置控制柜工作模式
     */
    void setControlMode(String serialNum, boolean isAuto,OnDataListener<Object> listener);

    /**
     * 得到控制柜详细信息
     */
    void getCtrlDeviceInfo(String serialNum, String deviceName,OnDataListener<CommonCtrlDeviceInfoEntity> listener);

}
