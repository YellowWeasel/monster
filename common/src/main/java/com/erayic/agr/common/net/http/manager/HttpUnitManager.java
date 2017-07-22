package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;
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

    /**
     * 批次绑定服务
     */
    public Flowable bindServiceByBatch(String unitID, int unitType, String batchID, int serType, String serviceID) {
        return unitService.bindServiceByBatch(unitID, unitType, batchID, serType, serviceID);
    }

    /**
     * 按指定绑定服务类型得到用户已购买的特定作物服务列表
     */
    public Flowable getSingleSeviceList(int serType) {
        return unitService.getSingleSeviceList(serType);
    }

    /**
     * 得到当前选定批次绑定的服务
     */
    public Flowable getBindService(String batchID, int serType) {
        return unitService.getBindService(batchID, serType);
    }

    /**
     * 取消当前选定批次绑定的服务
     */
    public Flowable cancelServieBind(String batchID, int serType) {
        return unitService.cancelServieBind(batchID, serType);
    }

    /**
     * 得到绑定服务的主题服务ID
     */
    public Flowable getBindServiceOfSubject(int serType) {
        return unitService.getBindServiceOfSubject(serType);
    }

    /**
     * 得到作物批次的生产履历
     */
    public Flowable getPorduceHistoryByBatch(String batchID, int pageNum, int pageSize) {
        return unitService.getPorduceHistoryByBatch(batchID, pageNum, pageSize);
    }

    /**
     * 保存一个工作日志
     */
    public Flowable saveWorkLog(CommonUnitBatchSaveLogBean bean) {
        return unitService.saveWorkLog(bean.getLogID(), bean.getBatchID(), bean.getDescript(), bean.getPics());
    }

    /**
     * 得到批次所有的工作日志
     */
    public Flowable getWorkLogByBatch(String batchID, int pageNum, int pageSize) {
        return unitService.getWorkLogByBatch(batchID, pageNum, pageSize);
    }

    /**
     * 得到批次详情首页
     */
    public Flowable getBatchDetail(String unitID, int type, String batchID) {
        return unitService.getBatchDetail(unitID, type, batchID);
    }

    /**
     * 得到作物的生长评估详情（积温，累积降水）
     */
    public Flowable getCycleDetailByBatch(String batchID, String unitID, int type) {
        return unitService.getCycleDetailByBatch(batchID, unitID, type);
    }

    /**
     * 得到批次的生长周期详情（积温，累积降水）
     */
    public Flowable getCycleByBatch(String batchID) {
        return unitService.getCycleByBatch(batchID);
    }

    /**
     * 得到生产建议详情
     */
    public Flowable getSuggestDetail(String unitID, int type, String batchID) {
        return unitService.getSuggestDetail(batchID, unitID, type);
    }

}
