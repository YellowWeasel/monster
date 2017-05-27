package com.erayic.agr.jobs.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsListView extends IBaseView {

    /**
     * 查询数据成功
     */
    void selectSure(CommonJobsInfoBean bean);

}
