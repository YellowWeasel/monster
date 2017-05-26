package com.erayic.agr.common.net.http;

import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

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
    Observable<DataBack<List<CommonWorkListBean>>> getJobList();

    /**
     * 保存一个自定义作业
     *
     * @return DataBack
     */
    @POST("Work/UpdateJob")
    Observable<DataBack<Object>> updateJob(
            @Body CommonWorkInfoBean bean
    );

    /**
     * 删除一个自定义作业
     *
     * @param jobID 作业ID
     * @return DataBack
     */
    @GET("Work/DeleteJob")
    Observable<DataBack<Object>> deleteJob(
            @Query("jobID") String jobID
    );

    /**
     * 得到指定预定义作业详情
     *
     * @param jobID 自定义作业ID
     * @return DataBack
     */
    @GET("Work/GetJobDetails")
    Observable<DataBack<CommonWorkInfoBean>> getJobDetails(
            @Query("jobID") String jobID
    );

    /**
     * 增加一个工作安排
     *
     * @param schedule 工作安排（CommonJobInfoBean类型）
     * @return DataBack
     */
    @POST("Work/AddSchedule")
    Observable<DataBack<Object>> addSchedule(
            @Body CommonJobInfoBean schedule
    );

}
