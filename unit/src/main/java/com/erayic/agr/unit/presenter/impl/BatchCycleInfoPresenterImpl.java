package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;
import com.erayic.agr.unit.presenter.IBatchCycleInfoPresenter;
import com.erayic.agr.unit.view.IBatchCycleInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchCycleInfoPresenterImpl implements IBatchCycleInfoPresenter {

    private IBatchCycleInfoView batchCycleInfoView;
    @Autowired
    IUnitModel unitModel;

    public BatchCycleInfoPresenterImpl(IBatchCycleInfoView batchCycleInfoView) {
        this.batchCycleInfoView = batchCycleInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getCycleDetailByBatch(String batchID, String unitID, int type) {
        batchCycleInfoView.showLoading();
        unitModel.getCycleDetailByBatch(batchID, unitID, type, new OnDataListener<CommonUnitBatchCycleBean>() {
            @Override
            public void success(CommonUnitBatchCycleBean response) {
                batchCycleInfoView.dismissLoading();
                batchCycleInfoView.refreshBatchCycleView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                batchCycleInfoView.dismissLoading();
                batchCycleInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getCycleByBatch(String batchID) {
        batchCycleInfoView.showLoading();
        unitModel.getCycleByBatch(batchID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchCycleInfoView.dismissLoading();
//                batchCycleInfoView.refreshBatchCycleView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                batchCycleInfoView.dismissLoading();
                batchCycleInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
