package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpWorkService;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HttpWorkManager {

    private static HttpWorkManager manager;

    private static IHttpWorkService workService;

    private HttpWorkManager() {
    }

    public static HttpWorkManager getInstance() {
        if (manager == null) {
            synchronized (IHttpWorkService.class) {
                if (manager == null) {
                    manager = new HttpWorkManager();
                    workService = HttpRetrofit.getRequestCookiesRetrofit().create(IHttpWorkService.class);
                }
            }
        }
        return manager;
    }

    /**
     * 得到所有已定义作业列表
     */
    public Flowable getJobList() {
        return workService.getJobList();
    }

    /**
     * 保存一个自定义作业
     */
    public Flowable updateJob(CommonWorkInfoBean bean) {
        return workService.updateJob(bean);
    }

    /**
     * 删除一个自定义作业
     */
    public Flowable deleteJob(String jobID) {
        return workService.deleteJob(jobID);
    }

    /**
     * 得到指定预定义作业详情
     */
    public Flowable getJobDetails(String jobID) {
        return workService.getJobDetails(jobID);
    }

    /**
     * 增加一个工作安排
     */
    public Flowable saveSchedule(CommonJobInfoBean bean, List<String> unitIDs) {
        return workService.saveSchedule(bean, unitIDs);
    }

    /**
     * 得到指定用户、指定日期的工作安排
     */
    public Flowable getDayWorkJobByUser(String specifyDay) {
        return workService.getDayWorkJobByUser(specifyDay);
    }

    /**
     * 得到指定工作安排详情
     */
    public Flowable getDayWorkDetail(String schID, String unitID, int unitType) {
        return workService.getDayWorkDetail(schID, unitID, unitType);
    }

    /**
     * 执行工作安排
     */
    public Flowable executeDayWork(String schID, String unitID, int unitType, List<String> batchIDs, CommonJobsInfoBean.RecordsInfo recoder) {
        return workService.executeDayWork(schID, unitID, unitType, batchIDs, recoder);
    }

    /**
     * 得到管理者指定日期的工作安排
     */
    public Flowable getDayWorkJobByManager(String specifyDay, int type) {
        return workService.getDayWorkJobByManager(specifyDay, type);
    }

    /**
     * 得到指定时间区域内的工作安排时间
     */
    public Flowable getScheduleByTime(String st, String end) {
        return workService.getScheduleByTime(st, end);
    }

}
