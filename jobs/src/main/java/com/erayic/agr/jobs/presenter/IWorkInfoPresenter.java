package com.erayic.agr.jobs.presenter;

import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IWorkInfoPresenter {

    /**
     * 保存一个自定义作业
     */
    void updateJob(CommonWorkInfoBean bean);

    /**
     * 删除一个自定义作业
     */
    void deleteJob(String jobID);

    /**
     * 得到指定预定义作业详情
     */
    void getJobDetails(String jobID);

}
