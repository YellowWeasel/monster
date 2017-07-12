package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;
import com.erayic.agr.unit.presenter.IBatchInfoByLogPresenter;
import com.erayic.agr.unit.view.IBatchInfoByLogView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchInfoByLogPresenterImpl implements IBatchInfoByLogPresenter {

    private IBatchInfoByLogView logView;
    @Autowired
    IUnitModel unitModel;

    public BatchInfoByLogPresenterImpl(IBatchInfoByLogView logView) {
        this.logView = logView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getWorkLogByBatch(String batchID, int pageSize) {
        logView.openRefresh();
        unitModel.getWorkLogByBatch(batchID,1,pageSize, new OnDataListener<List<CommonUnitBatchLogsBean>>() {
            @Override
            public void success(List<CommonUnitBatchLogsBean> response) {
                logView.clearRefresh();
                logView.refreshLogsView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                logView.clearRefresh();
                logView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                logView.refreshLogsView(null);
            }
        });
    }

    @Override
    public void getWorkLogByBatch(String batchID,int pageNum,int pageSize) {

        unitModel.getWorkLogByBatch(batchID,pageNum,pageSize, new OnDataListener<List<CommonUnitBatchLogsBean>>() {
            @Override
            public void success(List<CommonUnitBatchLogsBean> response) {
                logView.loadMoreSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                logView.loadMoreFailure();
                logView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
