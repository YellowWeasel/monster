package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.jobs.presenter.IWorkInfoPresenter;
import com.erayic.agr.jobs.view.IWorkInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class WorkInfoPresenterImpl implements IWorkInfoPresenter {

    private IWorkInfoView workInfoView;
    @Autowired
    IWorkModel workModel;

    public WorkInfoPresenterImpl(IWorkInfoView workInfoView) {
        this.workInfoView = workInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void updateJob(CommonWorkInfoBean bean) {
        workInfoView.showLoading();
        workModel.updateJob(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                workInfoView.dismissLoading();
                workInfoView.showToast("保存成功");
                workInfoView.updateOrDeleteSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                workInfoView.dismissLoading();
                workInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteJob(String jobID) {
        workInfoView.showLoading();
        workModel.deleteJob(jobID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                workInfoView.dismissLoading();
                workInfoView.showToast("删除成功");
                workInfoView.updateOrDeleteSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                workInfoView.dismissLoading();
                workInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getJobDetails(String jobID) {
        workInfoView.showLoading();
        workModel.getJobDetails(jobID, new OnDataListener<CommonWorkInfoBean>() {
            @Override
            public void success(CommonWorkInfoBean response) {
                workInfoView.dismissLoading();
                workInfoView.selectSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                workInfoView.dismissLoading();
                workInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
