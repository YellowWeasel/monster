package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBindServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBuyServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchResumeBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHttpUnitService {

    /**
     * 得到管理单元列表（含所有信息）
     *
     * @param type 管理单元类型（UnitType）
     * @return DataBack
     */
    @GET("Unit/GetAllUnit")
    Flowable<DataBack<List<CommonUnitListBean>>> getAllUnit(
            @Query("type") int type
    );

    /**
     * 得到管理单元列表（只有单元列表）
     *
     * @param type 管理单元类型（UnitType）
     * @return DataBack
     */
    @GET("Unit/GetAllUnitByBase")
    Flowable<DataBack<List<CommonUnitListByBaseBean>>> getAllUnitByBase(
            @Query("type") int type
    );

    /**
     * 新建一个批次
     *
     * @param proID    产品ID
     * @param seedID   种苗ID
     * @param seedName 种苗名称
     * @param quantity 种植数量
     * @param unit     种植数量单位（1:亩,2;个数）
     * @param stTime   种植时间
     * @param ope      操作者用户ID
     * @param unitID   隶属管理单元ID
     * @param unitType 隶属类型（1：基地，2：地块，3：塘，4：栏）
     * @return DataBack
     */
    @GET("Unit/CreateBatch")
    Flowable<DataBack<Object>> createBatch(
            @Query("proID") String proID,
            @Query("seedID") String seedID,
            @Query("seedName") String seedName,
            @Query("quantity") String quantity,
            @Query("unit") int unit,
            @Query("stTime") String stTime,
            @Query("ope") String ope,
            @Query("unitID") String unitID,
            @Query("unitType") int unitType
    );


    /**
     * 批次绑定服务
     *
     * @param batchID   批次ID（Guid）
     * @param serType   指定的绑定服务类型（1：生长适应性评价服务 2：生长周期预测服务 3：产量预测服务 4：市场价格服务）
     * @param serviceID 绑定的服务ID（Guid）
     * @return DataBack
     */
    @GET("Unit/BindServiceByBatch")
    Flowable<DataBack<Object>> bindServiceByBatch(
            @Query("unitID") String unitID,
            @Query("type") int unitType,
            @Query("batchID") String batchID,
            @Query("serType") int serType,
            @Query("serviceID") String serviceID
    );

    /**
     * 按指定绑定服务类型得到用户已购买的特定作物服务列表
     *
     * @param serType 指定的绑定服务类型（1：生长适应性评价服务 2：生长周期预测服务 3：产量预测服务 4：市场价格服务）
     * @return DataBack
     */
    @GET("Unit/GetSingleSeviceList")
    Flowable<DataBack<List<CommonUnitBatchServiceBean>>> getSingleSeviceList(
            @Query("serType") int serType
    );

    /**
     * 得到当前选定批次绑定的服务
     *
     * @param batchID 批次ID（Guid）
     * @param serType 指定的绑定服务类型（1：生长适应性评价服务 2：生长周期预测服务 3：产量预测服务 4：市场价格服务）
     * @return
     */
    @GET("Unit/GetBindService")
    Flowable<DataBack<CommonUnitBatchBindServiceBean>> getBindService(
            @Query("batchID") String batchID,
            @Query("serType") int serType
    );

    /**
     * 取消当前选定批次绑定的服务
     *
     * @param batchID 批次ID（Guid）
     * @param serType 指定的绑定服务类型（1：生长适应性评价服务 2：生长周期预测服务 3：产量预测服务 4：市场价格服务）
     * @return DataBack
     */
    @GET("Unit/CancelServieBind")
    Flowable<DataBack<Object>> cancelServieBind(
            @Query("batchID") String batchID,
            @Query("serType") int serType
    );

    /**
     * 得到绑定服务的主题服务ID
     *
     * @param serType 指定的绑定服务类型（1：生长适应性评价服务 2：生长周期预测服务 3：产量预测服务 4：市场价格服务）
     * @return DataBack
     */
    @GET("Unit/GetBindServiceOfSubject")
    Flowable<DataBack<CommonUnitBatchBuyServiceBean>> getBindServiceOfSubject(
            @Query("serType") int serType
    );

    /**
     * 得到作物批次的生产履历
     *
     * @param batchID 批次ID
     * @return DataBack
     */
    @GET("Unit/GetPorduceHistoryByBatch")
    Flowable<DataBack<List<CommonUnitBatchResumeBean>>> getPorduceHistoryByBatch(
            @Query("batchID") String batchID
    );

    /**
     * 保存一个工作日志
     *
     * @return DataBack
     */
    @POST("Unit/SaveWorkLog")
    Flowable<DataBack<Object>> saveWorkLog(
            @Query("logID") String logID,
            @Query("batchID") String batchID,
            @Query("descript") String descript,
            @Body CommonUnitBatchSaveLogBean.ApplyPic pics
    );

    /**
     * 得到批次所有的工作日志
     *
     * @param batchID 批次ID
     * @return DataBack
     */
    @GET("Unit/GetWorkLogByBatch")
    Flowable<DataBack<List<CommonUnitBatchLogsBean>>> getWorkLogByBatch(
            @Query("batchID") String batchID
    );

    /**
     * 得到批次详情首页
     *
     * @param unitID  管理单元ID（Guid）
     * @param type    管理单元类型（1：基地，2：地块，3：塘，4：栏）
     * @param batchID 批次ID（Guid）
     * @return DataBack
     */
    @GET("Unit/GetBatchDetail")
    Flowable<DataBack<CommonUnitBatchInfoBean>> getBatchDetail(
            @Query("unitID") String unitID,
            @Query("type") int type,
            @Query("batchID") String batchID
    );

    /**
     * 得到作物的生长评估详情（生长期、积温，累积降水）
     *
     * @param batchID 批次ID（Guid）
     * @param unitID  管理单元ID（Guid）
     * @param type    管理单元类型（1：基地，2：地块，3：塘，4：栏）
     * @return DataBack
     */
    @GET("Unit/GetCycleDetailByBatch")
    Flowable<DataBack<CommonUnitBatchCycleBean>> getCycleDetailByBatch(
            @Query("batchID") String batchID,
            @Query("unitID") String unitID,
            @Query("type") int type
    );

    /**
     * 得到批次的生长周期详情
     *
     * @param batchID 批次ID（Guid）
     * @return DataBack
     */
    @GET("Unit/GetCycleByBatch")
    Flowable<DataBack<Object>> getCycleByBatch(
            @Query("batchID") String batchID
    );

    /**
     * 得到生产建议详情
     *
     * @param batchID 批次ID
     * @param unitID  管理单元ID
     * @param type    管理单元类型
     * @return DataBack
     */
    @GET("Unit/GetSuggestDetail")
    Flowable<DataBack<Object>> getSuggestDetail(
            @Query("batchID") String batchID,
            @Query("unitID") String unitID,
            @Query("type") int type
    );

}
