package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchResumeBean;
import com.erayic.agr.unit.presenter.IBatchInfoByResumePresenter;
import com.erayic.agr.unit.view.IBatchInfoByResumeView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchInfoByResumePresenterImpl implements IBatchInfoByResumePresenter {

    private IBatchInfoByResumeView resumeView;
    @Autowired
    IUnitModel unitModel;

    public BatchInfoByResumePresenterImpl(IBatchInfoByResumeView resumeView) {
        this.resumeView = resumeView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getPorduceHistoryByBatch(String batchID, int pageSize) {
        resumeView.openRefresh();
        unitModel.getPorduceHistoryByBatch(batchID, 1, pageSize, new OnDataListener<List<CommonUnitBatchResumeBean>>() {
            @Override
            public void success(List<CommonUnitBatchResumeBean> response) {
                resumeView.clearRefresh();
                resumeView.refreshLogsView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                resumeView.clearRefresh();
                resumeView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getPorduceHistoryByBatch(String batchID, int pageNum, int pageSize) {
        unitModel.getPorduceHistoryByBatch(batchID, pageNum, pageSize, new OnDataListener<List<CommonUnitBatchResumeBean>>() {
            @Override
            public void success(List<CommonUnitBatchResumeBean> response) {
                resumeView.loadMoreSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                resumeView.loadMoreFailure();
                resumeView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
