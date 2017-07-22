package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;
import com.erayic.agr.jobs.presenter.IJobsListPresenter;
import com.erayic.agr.jobs.view.IJobsListView;

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
        workModel.getDayWorkJobByUser(specifyDay, new OnDataListener<CommonJobsListUserBean>() {
            @Override
            public void success(CommonJobsListUserBean response) {
//                List<CommonJobsListUserBean.JobsInfo> list = new ArrayList<CommonJobsListUserBean.JobsInfo>();
//                for (int i = 0; i < 5; i++) {
//                    CommonJobsListUserBean.JobsInfo jobsInfo = new CommonJobsListUserBean.JobsInfo();
//                    jobsInfo.setTestName(i + "测试名称");
//                    List<CommonJobsListUserBean.childJobInfo> childList = new ArrayList<CommonJobsListUserBean.childJobInfo>();
//                    for (int j = 0; j < 10; j++) {
//                        CommonJobsListUserBean.childJobInfo childJobInfo = new CommonJobsListUserBean.childJobInfo();
//                        childJobInfo.setTestChildName("10:" + i + "" + j);
//                        childList.add(childJobInfo);
//                    }
//                    jobsInfo.setChildJobInfos(childList);
//                    list.add(jobsInfo);
//                }
//                response.setJobs(list);
                for (int i = 0; i < response.getJobs().size(); i++) {
                    int rate = 0;
                    for (CommonJobsListUserBean.JobsContents contents : response.getJobs().get(i).getContents()) {
                        if (contents.isFinish())
                            rate++;
                    }
                    if (response.getJobs().size() > 0)
                        if (rate == response.getJobs().get(i).getContents().size())
                            response.getJobs().get(i).setPercentage(100);
                        else {
                            double d = (double) rate / response.getJobs().get(i).getContents().size();
                            response.getJobs().get(i).setPercentage((int) (d * 100));
                        }
                    else
                        response.getJobs().get(i).setPercentage(0);
                }


                jobsListView.selectUserSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                jobsListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getDayWorkJobByManager(String specifyDay) {
        workModel.getDayWorkJobByManager(specifyDay, EnumUnitType.TYPE_PLOTS, new OnDataListener<CommonJobsListManagerBean>() {
            @Override
            public void success(CommonJobsListManagerBean response) {
                for (int i = 0; i < response.getJobs().size(); i++) {
                    int rate = 0;
                    for (CommonJobsListManagerBean.JobsEntity contents : response.getJobs().get(i).getJobs()) {
                        if (contents.isFinish())
                            rate++;
                    }
                    if (response.getJobs().size() > 0)
                        if (rate == response.getJobs().get(i).getJobs().size())
                            response.getJobs().get(i).setPercentage(100);
                        else {
                            double d = (double) rate / response.getJobs().get(i).getJobs().size();
                            response.getJobs().get(i).setPercentage((int) (d * 100));
                        }
                    else
                        response.getJobs().get(i).setPercentage(0);
                }
                jobsListView.selectManagerSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                jobsListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getScheduleByTime(String st, String end) {
        workModel.getScheduleByTime(st, end, new OnDataListener<List<String>>() {
            @Override
            public void success(List<String> response) {
                jobsListView.refreshSchedule(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                jobsListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
