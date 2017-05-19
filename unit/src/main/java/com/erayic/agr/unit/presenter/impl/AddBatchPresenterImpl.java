package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.unit.presenter.IAddBatchPresenter;
import com.erayic.agr.unit.view.IAddBatchView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class AddBatchPresenterImpl implements IAddBatchPresenter {

    private IAddBatchView batchView;
    @Autowired
    IUnitModel unitModel;

    public AddBatchPresenterImpl(IAddBatchView batchView) {
        this.batchView = batchView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void createBatch(String proID, String seedID,String seedName, String quantity, String stTime, String ope, String unitID) {
        batchView.showLoading();
        unitModel.createBatch(proID, seedID,seedName, quantity, 1, stTime, ope, unitID, EnumUnitType.TYPE_PLOTS, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchView.dismissLoading();
                batchView.showToast("增加批次成功");
                batchView.addBatchSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                batchView.dismissLoading();
                batchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
