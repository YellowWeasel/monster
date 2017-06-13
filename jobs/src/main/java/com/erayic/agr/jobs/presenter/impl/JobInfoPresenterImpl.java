package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.jobs.presenter.IJobInfoPresenter;
import com.erayic.agr.jobs.view.IJobInfoView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobInfoPresenterImpl implements IJobInfoPresenter {

    private IJobInfoView jobInfoView;
    @Autowired
    IWorkModel workModel;

    public JobInfoPresenterImpl(IJobInfoView jobInfoView) {
        this.jobInfoView = jobInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void saveSchedule(CommonJobInfoBean bean, List<String> unitIDs) {
        jobInfoView.showLoading();
        workModel.saveSchedule(bean, unitIDs, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                jobInfoView.dismissLoading();
                jobInfoView.showToast("安排成功");
                jobInfoView.updateOrDeleteSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                jobInfoView.dismissLoading();
                jobInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
