package com.erayic.agr.jobs.presenter;

import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.jobs.bean.JobsLocalMedia;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsInfoPresenter {

    /**
     * 得到指定工作安排详情
     */
    void getDayWorkDetail(String schID, String unitID);

    /**
     * 上传图片
     */
    void submitImage(List<JobsLocalMedia> selectMedia);

    /**
     * 执行工作安排
     */
    void executeDayWork(String schID, String unitID, List<String> batchIDs, CommonJobsInfoBean.RecordsInfo recoder);
}
