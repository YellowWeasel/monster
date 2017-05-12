package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpUnitService;

import rx.Observable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpUnitManager {

    private static HttpUnitManager manager;

    private static IHttpUnitService unitService;

    private HttpUnitManager() {
    }

    public static HttpUnitManager getInstance() {
        if (manager == null) {
            synchronized (IHttpUnitService.class) {
                if (manager == null) {
                    manager = new HttpUnitManager();
                    unitService = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpUnitService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 得到管理单元列表
     */
    public Observable getAllUnit(int type) {
        return unitService.getAllUnit(type);
    }
}
