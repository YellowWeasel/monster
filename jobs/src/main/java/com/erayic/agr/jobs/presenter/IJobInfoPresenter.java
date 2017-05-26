package com.erayic.agr.jobs.presenter;

import com.erayic.agr.common.net.back.work.CommonJobInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobInfoPresenter {

    /**
     * 增加一个工作安排
     */
    void addSchedule(CommonJobInfoBean bean);

}
