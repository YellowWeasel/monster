package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.jobs.presenter.IAdvanceWorkPresenter;
import com.erayic.agr.jobs.view.IAdvanceWorkView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class AdvanceWorkPresenterImpl implements IAdvanceWorkPresenter {

    private IAdvanceWorkView advanceWorkView;
    @Autowired
    IWorkModel workModel;

    public AdvanceWorkPresenterImpl(IAdvanceWorkView advanceWorkView) {
        this.advanceWorkView = advanceWorkView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getJobList() {
        advanceWorkView.openRefresh();
        workModel.getJobList(new OnDataListener<List<CommonWorkListBean>>() {
            @Override
            public void success(List<CommonWorkListBean> response) {
                advanceWorkView.clearRefresh();
                advanceWorkView.refreshWorkView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                advanceWorkView.clearRefresh();
                advanceWorkView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
