package com.erayic.agr.jobs.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.img.CommonResultImage;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.jobs.bean.JobsLocalMedia;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IJobsInfoView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 刷新工作安排
     */
    void refreshJobsView(CommonJobsInfoBean bean);

    /**
     * 上传图片成功
     */
    void uploadImageResult(List<JobsLocalMedia> selectMedia);

    /**
     * 执行工作安排成功
     */
    void submitWorkSure();

}
