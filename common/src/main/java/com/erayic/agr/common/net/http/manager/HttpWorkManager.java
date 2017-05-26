package com.erayic.agr.common.net.http.manager;

import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.http.HttpRetrofit;
import com.erayic.agr.common.net.http.IHttpWorkService;

import rx.Observable;

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
    public Observable getJobList() {
        return workService.getJobList();
    }

    /**
     * 保存一个自定义作业
     */
    public Observable updateJob(CommonWorkInfoBean bean) {
        return workService.updateJob(bean);
    }

    /**
     * 删除一个自定义作业
     */
    public Observable deleteJob(String jobID) {
        return workService.deleteJob(jobID);
    }

    /**
     * 得到指定预定义作业详情
     */
    public Observable getJobDetails(String jobID) {
        return workService.getJobDetails(jobID);
    }

    /**
     * 增加一个工作安排
     */
    public Observable addSchedule(CommonJobInfoBean bean) {
        return workService.addSchedule(bean);
    }

}
