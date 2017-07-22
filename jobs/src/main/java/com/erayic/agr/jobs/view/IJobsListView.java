package com.erayic.agr.jobs.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsListView extends IBaseView {

    /**
     * 更新用户数据
     */
    void selectUserSure(CommonJobsListUserBean bean);

    /**
     * 更新管理员数据
     */
    void selectManagerSure(CommonJobsListManagerBean bean);

    /**
     * 更新日历数据
     */
    void refreshSchedule(List<String> list);

}
