package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.device.CommonCtrlDeviceInfoEntity;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：设备相关
 */

public interface IHttpDeviceService {

    /**
     * 操作控制设备
     *
     * @param serialNum 控制设备序号
     * @param cmd       操作命令（1、 启动，0、停止 3、正转启动 2、反转启动）
     * @param passNum   通道号（int）
     * @param type      控制设备类型（1、启停设备 2、正反转设备）
     * @return DataBack
     */
    @GET("Device/OpeCtrDevice")
    Flowable<DataBack<Object>> opeCtrDevice(
            @Query("serialNum") String serialNum,
            @Query("cmd") int cmd,
            @Query("passNum") int passNum,
            @Query("type") int type
    );

    /**
     * 得到控制设备子类型状态
     *
     * @param serialNum 设备序列号
     * @param passNum   通道号
     * @param type      控制设备类型（1、启停设备 2、正反转设备）
     * @return DataBack
     */
    @GET("Device/GetCtrlItemStatus")
    Flowable<DataBack<Object>> getCtrlItemStatus(
            @Query("serialNum") String serialNum,
            @Query("passNum") int passNum,
            @Query("type") int type
    );

    /**
     * 得到设备信息
     *
     * @param serialNum 设备号
     * @return DataBack
     */
    @GET("Device/GetMonitorInfo")
    Flowable<DataBack<CommonMonitorInfoEntity>> getMonitorInfo(
            @Query("serialNum") String serialNum
    );

    /**
     * 远程重启控制设备
     *
     * @param serialNum 控制设备序号
     * @return DataBack
     */
    @GET("Device/ResetDevice")
    Flowable<DataBack<Object>> resetDevice(
            @Query("serialNum") String serialNum
    );

    /**
     * 设置峰鸣器状态
     *
     * @param serialNum 控制设备序号
     * @param isOpen    是否打开峰鸣预警(bool)
     * @return DataBack
     */
    @GET("Device/SetCtrlBuzzer")
    Flowable<DataBack<Object>> setCtrlBuzzer(
            @Query("serialNum") String serialNum,
            @Query("isOpen") boolean isOpen
    );

    /**
     * 设置控制柜工作模式
     *
     * @param serialNum 控制设备序号
     * @param isAuto    是否自动工作状态(bool)
     * @return DataBack
     */
    @GET("Device/SetControlMode")
    Flowable<DataBack<Object>> setControlMode(
            @Query("serialNum") String serialNum,
            @Query("isAuto") boolean isAuto
    );

    /**
     * 得到控制柜详细信息
     *
     * @param serialNum  控制设备序号
     * @param deviceName 控制设备名称（错误是为null）
     * @return DataBack
     */
    @GET("Device/GetCtrlDevcieInfo")
    Flowable<DataBack<CommonCtrlDeviceInfoEntity>> getCtrlDeviceInfo(
            @Query("serialNum") String serialNum,
            @Query("deviceName") String deviceName
    );
}
