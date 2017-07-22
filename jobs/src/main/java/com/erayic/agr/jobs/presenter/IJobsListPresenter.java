package com.erayic.agr.jobs.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsListPresenter {

    /**
     * 得到指定用户、指定日期的工作安排（用户）
     */
    void getDayWorkJobByUser(String specifyDay);

    /**
     * 得到指定用户、指定日期的工作安排（管理员）
     */
    void getDayWorkJobByManager(String specifyDay);

    /**
     * 得到指定时间区域内的工作安排时间
     */
    void getScheduleByTime(String st, String end);

}
