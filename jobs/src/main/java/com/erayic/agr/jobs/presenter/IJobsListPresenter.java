package com.erayic.agr.jobs.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsListPresenter {

    /**
     * 得到指定用户、指定日期的工作安排
     */
    void getDayWorkJobByUser(String specifyDay);

}
