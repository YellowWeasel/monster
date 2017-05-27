package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.jobs.presenter.IJobsListPresenter;
import com.erayic.agr.jobs.view.IJobsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsListPresenterImpl implements IJobsListPresenter {

    private IJobsListView jobsListView;
    @Autowired
    IWorkModel workModel;

    public JobsListPresenterImpl(IJobsListView jobsListView) {
        this.jobsListView = jobsListView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getDayWorkJobByUser(String specifyDay) {
        workModel.getDayWorkJobByUser(specifyDay, new OnDataListener<CommonJobsInfoBean>() {
            @Override
            public void success(CommonJobsInfoBean response) {
                List<CommonJobsInfoBean.JobsInfo> list = new ArrayList<CommonJobsInfoBean.JobsInfo>();
                for (int i = 0; i < 5; i++) {
                    CommonJobsInfoBean.JobsInfo jobsInfo = new CommonJobsInfoBean.JobsInfo();
                    jobsInfo.setTestName(i + "测试名称");
                    List<CommonJobsInfoBean.childJobInfo> childList = new ArrayList<CommonJobsInfoBean.childJobInfo>();
                    for (int j = 0; j < 10; j++) {
                        CommonJobsInfoBean.childJobInfo childJobInfo = new CommonJobsInfoBean.childJobInfo();
                        childJobInfo.setTestChildName("10:" + i + "" + j);
                        childList.add(childJobInfo);
                    }
                    jobsInfo.setChildJobInfos(childList);
                    list.add(jobsInfo);
                }
                response.setJobs(list);
                jobsListView.selectSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                jobsListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
