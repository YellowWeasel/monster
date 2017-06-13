package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpUnitService;

import io.reactivex.Flowable;

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
     * 得到管理单元列表（含所有信息）
     */
    public Flowable getAllUnit(int type) {
        return unitService.getAllUnit(type);
    }

    /**
     * 得到管理单元列表（只有单元列表）
     */
    public Flowable getAllUnitByBase(int type) {
        return unitService.getAllUnitByBase(type);
    }

    /**
     * 新建一个批次
     */
    public Flowable createBatch(String proID, String seedID, String seedName, String quantity, int unit, String stTime, String ope, String unitID, int unitType) {
        return unitService.createBatch(proID, seedID, seedName, quantity, unit, stTime, ope, unitID, unitType);
    }
}
