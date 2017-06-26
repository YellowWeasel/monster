package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.CommonLocalMedia;
import com.erayic.agr.common.model.IIndexModel;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.img.CommonResultImage;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.jobs.presenter.IJobsInfoPresenter;
import com.erayic.agr.jobs.view.IJobsInfoView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsInfoPresenterImpl implements IJobsInfoPresenter {

    private IJobsInfoView jobsInfoView;
    @Autowired
    IWorkModel workModel;
    @Autowired
    IIndexModel indexModel;

    int surePosition = 0;//成功图片
    int failPosition = 0;//失败图片

    public JobsInfoPresenterImpl(IJobsInfoView jobsInfoView) {
        this.jobsInfoView = jobsInfoView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getDayWorkDetail(String schID, String unitID) {
        jobsInfoView.showLoading();
        workModel.getDayWorkDetail(schID, unitID, EnumUnitType.TYPE_PLOTS, new OnDataListener<CommonJobsInfoBean>() {
            @Override
            public void success(CommonJobsInfoBean response) {
                jobsInfoView.dismissLoading();
                jobsInfoView.refreshJobsView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                jobsInfoView.dismissLoading();
                jobsInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void submitImage(final List<CommonLocalMedia> selectMedia) {
        jobsInfoView.showLoading();
        final int size = selectMedia.size();
        surePosition = 0;
        failPosition = 0;
        for (final CommonLocalMedia media : selectMedia) {
            if (!media.isUpload())
                indexModel.uploadImg(media.getFinalPath(), new OnDataListener<CommonResultImage>() {
                    @Override
                    public void success(CommonResultImage response) {
                        surePosition++;
                        media.setUpload(true);
                        media.setResultImage(response);
                        if (surePosition + failPosition == size) {
                            jobsInfoView.dismissLoading();
                            jobsInfoView.uploadImageResult(selectMedia);
//                        ErayicLog.e("上传图片：", "成功" + surePosition + "张，失败" + failPosition + "张");
//                        workModel.executeDayWork(schID,unitID,);
                        }
                    }

                    @Override
                    public void fail(int errCode, String msg) {
                        failPosition++;
//                        jobsInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                        media.setUpload(false);
                        if (surePosition + failPosition == size) {
                            jobsInfoView.dismissLoading();
                            jobsInfoView.uploadImageResult(selectMedia);
//                        ErayicLog.e("上传图片：", "成功" + surePosition + "张，失败" + failPosition + "张");
                        }
                    }
                });
            else
                surePosition++;
        }
        if (surePosition == selectMedia.size()){
            jobsInfoView.dismissLoading();
            jobsInfoView.uploadImageResult(selectMedia);
        }
    }

    @Override
    public void executeDayWork(String schID, String unitID, List<String> batchIDs, CommonJobsInfoBean.RecordsInfo recoder) {
        jobsInfoView.showLoading();
        workModel.executeDayWork(schID, unitID, EnumUnitType.TYPE_PLOTS, batchIDs, recoder, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                jobsInfoView.dismissLoading();
                jobsInfoView.submitWorkSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                jobsInfoView.dismissLoading();
                jobsInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }


}
