package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;

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

public interface IHttpWorkService {

    /**
     * 得到所有已定义作业列表
     *
     * @return DataBack
     */
    @GET("Work/GetJobList")
    Flowable<DataBack<List<CommonWorkListBean>>> getJobList();

    /**
     * 保存一个自定义作业
     *
     * @return DataBack
     */
    @POST("Work/UpdateJob")
    Flowable<DataBack<Object>> updateJob(
            @Body CommonWorkInfoBean bean
    );

    /**
     * 删除一个自定义作业
     *
     * @param jobID 作业ID
     * @return DataBack
     */
    @GET("Work/DeleteJob")
    Flowable<DataBack<Object>> deleteJob(
            @Query("jobID") String jobID
    );

    /**
     * 得到指定预定义作业详情
     *
     * @param jobID 自定义作业ID
     * @return DataBack
     */
    @GET("Work/GetJobDetails")
    Flowable<DataBack<CommonWorkInfoBean>> getJobDetails(
            @Query("jobID") String jobID
    );

    /**
     * 增加一个工作安排
     *
     * @param schedule 工作安排（CommonJobInfoBean类型）
     * @return DataBack
     */
    @POST("Work/SaveSchedule")
    Flowable<DataBack<Object>> saveSchedule(
            @Body CommonJobInfoBean schedule,
            @Query("unitIDs") List<String> unitIDs
    );

    /**
     * 得到指定用户、指定日期的工作安排
     *
     * @param specifyDay 指定日期
     * @return DataBack
     */
    @GET("Work/GetDayWorkJobByUser")
    Flowable<DataBack<CommonJobsListUserBean>> getDayWorkJobByUser(
            @Query("specifyDay") String specifyDay
    );

    /**
     * 得到指定工作安排详情
     *
     * @param schID  工作安排ID
     * @param unitID 管理单元ID
     * @return DataBack
     */
    @GET("Work/GetDayWorkDetail")
    Flowable<DataBack<CommonJobsInfoBean>> getDayWorkDetail(
            @Query("schID") String schID,
            @Query("unitID") String unitID,
            @Query("unitType") int unitType
    );

    /**
     * 执行工作安排
     *
     * @param schID    工作安排ID（GUID）
     * @param unitID   管理单元ID（GUID）
     * @param unitType 隶属类型（1：基地，2：地块，3：塘，4：栏）
     * @param batchIDs 欲施用的批次集合（Guid[]）
     * @param recoder  工作记录（DayRecoder类）
     * @return DataBack
     */
    @POST("Work/ExecuteDayWork")
    Flowable<DataBack<Object>> executeDayWork(
            @Query("schID") String schID,
            @Query("unitID") String unitID,
            @Query("unitType") int unitType,
            @Query("batchIDs") List<String> batchIDs,
            @Body CommonJobsInfoBean.RecordsInfo recoder
    );

    /**
     * 得到管理者指定日期的工作安排
     * @param specifyDay 指定日期(DateTime)
     * @param type 管理单元类型(1：基地，2：地块，3：塘，4：栏)
     * @return DataBack
     */
    @GET("Work/GetDayWorkJobByManager")
    Flowable<DataBack<CommonJobsListManagerBean>> getDayWorkJobByManager(
            @Query("specifyDay") String specifyDay,
            @Query("type") int type
    );

}
