package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.unit.presenter.IBatchInfoPresenter;
import com.erayic.agr.unit.view.IBatchInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchInfoPresenterImpl implements IBatchInfoPresenter {

    private IBatchInfoView batchInfoView;

    public BatchInfoPresenterImpl(IBatchInfoView batchInfoView){
        this.batchInfoView = batchInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getBatchInfo(String batchID) {
        batchInfoView.openRefresh();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    batchInfoView.clearRefresh();
                    batchInfoView.refreshBatchView("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
