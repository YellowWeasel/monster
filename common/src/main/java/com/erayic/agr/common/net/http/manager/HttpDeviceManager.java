package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpDeviceService;

import io.reactivex.Flowable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpDeviceManager {

    private static HttpDeviceManager manager;

    private static IHttpDeviceService deviceService;

    private HttpDeviceManager() {
    }

    public static HttpDeviceManager getInstance() {
        if (manager == null) {
            synchronized (IHttpDeviceService.class) {
                if (manager == null) {
                    manager = new HttpDeviceManager();
                    deviceService = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpDeviceService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 操作控制设备
     */
    public Flowable opeCtrDevice(String serialNum, int cmd, int passNum, int type) {
        return deviceService.opeCtrDevice(serialNum, cmd, passNum, type);
    }

    /**
     * 得到控制设备子类型状态
     */
    public Flowable getCtrlItemStatus(String serialNum, int passNum, int type) {
        return deviceService.getCtrlItemStatus(serialNum, passNum, type);
    }

}
