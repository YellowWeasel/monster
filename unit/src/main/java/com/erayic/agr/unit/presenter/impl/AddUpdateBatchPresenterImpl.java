package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.unit.presenter.IAddUpdateBatchPresenter;
import com.erayic.agr.unit.view.IAddUpdateBatchView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class AddUpdateBatchPresenterImpl implements IAddUpdateBatchPresenter {

    private IAddUpdateBatchView batchView;
    @Autowired
    IUnitModel unitModel;

    public AddUpdateBatchPresenterImpl(IAddUpdateBatchView batchView) {
        this.batchView = batchView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void createBatch(String proID, String seedID, String seedName, String quantity, String stTime, String ope, String unitID) {
        batchView.showLoading();
        unitModel.createBatch(proID, seedID, seedName, quantity, 1, stTime, ope, unitID, EnumUnitType.TYPE_PLOTS, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchView.dismissLoading();
                batchView.addBatchSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                batchView.dismissLoading();
                batchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateBatch(String batchID, String proID, String seedID, String seedName, float quantity, String stTime, String unitID) {
        batchView.showLoading();
        unitModel.updateBatch(batchID, proID, seedID, seedName, quantity, 1, stTime, unitID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchView.dismissLoading();
                batchView.updateBatchSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                batchView.dismissLoading();
                batchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteBatch(String batchID) {
        batchView.showLoading();
        unitModel.deleteBatch(batchID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchView.dismissLoading();
                batchView.deleteBatchSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                batchView.dismissLoading();
                batchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void finishBatch(String batchID, String finishTime) {
        batchView.showLoading();
        unitModel.finishBatch(batchID, finishTime, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchView.dismissLoading();
                batchView.deleteBatchSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                batchView.dismissLoading();
                batchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
