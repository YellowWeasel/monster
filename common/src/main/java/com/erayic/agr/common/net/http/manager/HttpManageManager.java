package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpManageService;

import java.util.List;

import rx.Observable;

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
    public Observable getEntInfo() {
        return manageService.getEntInfo();
    }

    /**
     * 更新企业名称
     */
    public Observable updateEntName(String newEntName) {
        return manageService.updateEntName(newEntName);
    }

    /**
     * 得到一个企业的所有基地列表
     */
    public Observable getBaseByEnt() {
        return manageService.getBaseByEnt();
    }

    /**
     * 增加一个企业基地
     */
    public Observable addBaseByEnt(String newBaseName) {
        return manageService.addBaseByEnt(newBaseName);
    }

    /**
     * 用户变更所属基地
     */
    public Observable changeBase(String newBaseID) {
        return manageService.changeBase(newBaseID);
    }

    /**
     * 上传基地位置信息
     */

    /**
     * 得到基地信息
     */
    public Observable getBaseInfo(String baseID) {
        return manageService.getBaseInfo(2, baseID);
    }

    /**
     * 增加管理单元
     */
    public Observable addUnit(String baseID, String unitName) {
        return manageService.addUnit(baseID, unitName, 2);
    }

    /**
     * 更新基地信息
     */
    public Observable updateBaseInfo(String baseID, String baseName, String descript) {
        return manageService.updateBaseInfo(baseID, baseName, descript);
    }

    /**
     * 得到管理单元详情
     */
    public Observable getUnitDetailInfo(String unitID, int type) {
        return manageService.getUnitDetailInfo(unitID, type);
    }

    /**
     * 更新地块信息
     */
    public Observable updateBlockInfo(String unitID, String unitName, double area, List<String> regions, List<String> workes, boolean isGreenhouse) {
        return manageService.updateBlockInfo(unitID, unitName, area, regions, workes, isGreenhouse);
    }
}