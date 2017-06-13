package com.erayic.agr.jobs.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsListView extends IBaseView {

    /**
     * 查询用户数据成功
     */
    void selectUserSure(CommonJobsListUserBean bean);

    void selectManagerSure(CommonJobsListManagerBean bean);

}
