package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.manage.CommonBasePositionBean;
import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpManageService;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpManageManager {

    private static HttpManageManager manager;

    private static IHttpManageService manageService;

    private HttpManageManager() {
    }

    public static HttpManageManager getInstance() {
        if (manager == null) {
            synchronized (IHttpManageService.class) {
                if (manager == null) {
                    manager = new HttpManageManager();
                    manageService = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpManageService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 获取企业信息
     */
    public Flowable getEntInfo() {
        return manageService.getEntInfo();
    }

    /**
     * 更新企业名称
     */
    public Flowable updateEntName(String newEntName) {
        return manageService.updateEntName(newEntName);
    }

    /**
     * 得到一个企业的所有基地列表
     */
    public Flowable getBaseByEnt() {
        return manageService.getBaseByEnt();
    }

    /**
     * 增加一个企业基地
     */
    public Flowable addBaseByEnt(String newBaseName, String phoneCode) {
        return manageService.addBaseByEnt(newBaseName, phoneCode);
    }

    /**
     * 用户变更所属基地
     */
    public Flowable changeBase(String newBaseID) {
        return manageService.changeBase(newBaseID);
    }

    /**
     * 上传一个基地位置信息
     */
    public Flowable setBasePosition(String baseID, double lon, double lat, String provinceName, String cityName, String regionName, String regionCode, String townName,
                                    String townCode, String village, String address) {
        //省市截取
        return manageService.setBasePosition(baseID, lon, lat, provinceName, cityName, regionName, regionCode, townName, townCode, village, address);
    }

    /**
     * 得到基地信息
     */
    public Flowable getBaseInfo(String baseID) {
        return manageService.getBaseInfo(2, baseID);
    }

    /**
     * 增加管理单元
     */
    public Flowable addUnit(String baseID, String unitName) {
        return manageService.addUnit(baseID, unitName, 2);
    }

    /**
     * 更新基地信息
     */
    public Flowable updateBaseInfo(String baseID, String baseName, String descript) {
        return manageService.updateBaseInfo(baseID, baseName, descript);
    }

    /**
     * 得到管理单元详情
     */
    public Flowable getUnitDetailInfo(String unitID, int type) {
        return manageService.getUnitDetailInfo(unitID, type);
    }

    /**
     * 更新地块信息
     */
    public Flowable updateBlockInfo(String baseID, String unitID, String unitName, double area, List<String> regions, List<String> workes, boolean isGreenhouse) {
        return manageService.updateBlockInfo(baseID, unitID, unitName, area, regions, workes, isGreenhouse);
    }
}
