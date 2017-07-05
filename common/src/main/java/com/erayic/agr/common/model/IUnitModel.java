package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBindServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBuyServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchResumeBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSuggestBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */


public interface IUnitModel extends IProvider {

    /**
     * 得到管理单元列表（含所有信息）
     */
    void getAllUnit(int type, OnDataListener<List<CommonUnitListBean>> listener);

    /**
     * 得到管理单元列表（只有单元列表）
     */
    void getAllUnitByBase(int type, OnDataListener<List<CommonUnitListByBaseBean>> listener);

    /**
     * 新建一个批次
     */
    void createBatch(String proID, String seedID, String seedName, String quantity, int unit, String stTime, String ope, String unitID, int unitType, OnDataListener<Object> listener);

    /**
     * 批次绑定服务
     */
    void bindServiceByBatch(String unitID, int unitType, String batchID, int serType, String serviceID, OnDataListener<Object> listener);

    /**
     * 按指定绑定服务类型得到用户已购买的特定作物服务列表
     */
    void getSingleSeviceList(int serType, OnDataListener<List<CommonUnitBatchServiceBean>> listener);

    /**
     * 得到当前选定批次绑定的服务
     */
    void getBindService(String batchID, int serType, OnDataListener<CommonUnitBatchBindServiceBean> listener);

    /**
     * 取消当前选定批次绑定的服务
     */
    void cancelServieBind(String batchID, int serType, OnDataListener<Object> listener);

    /**
     * 得到绑定服务的主题服务ID
     */
    void getBindServiceOfSubject(int serType, OnDataListener<CommonUnitBatchBuyServiceBean> listener);

    /**
     * 得到作物批次的生产履历
     */
    void getPorduceHistoryByBatch(String batchID, OnDataListener<List<CommonUnitBatchResumeBean>> listener);

    /**
     * 保存一个工作日志
     */
    void saveWorkLog(CommonUnitBatchSaveLogBean bean, OnDataListener<Object> listener);

    /**
     * 得到批次所有的工作日志
     */
    void getWorkLogByBatch(String batchID, OnDataListener<List<CommonUnitBatchLogsBean>> listener);

    /**
     * 得到批次详情首页
     */
    void getBatchDetail(String unitID, int type, String batchID, OnDataListener<CommonUnitBatchInfoBean> listener);

    /**
     * 得到作物的生长评估详情（生长期、积温，累积降水）
     */
    void getCycleDetailByBatch(String batchID, String unitID, int type, OnDataListener<CommonUnitBatchCycleBean> listener);

    /**
     * 得到批次的生长周期详情
     */
    void getCycleByBatch(String batchID, OnDataListener<Object> listener);

    /**
     *得到生产建议详情
     */
     void getSuggestDetail(String unitID, int type, String batchID, OnDataListener<CommonUnitBatchSuggestBean> listener);
}
