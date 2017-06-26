package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.unit.presenter.IBatchInfoByStatuePresenter;
import com.erayic.agr.unit.view.IBatchInfoByStatueView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchInfoByStatuePresenterImpl implements IBatchInfoByStatuePresenter {

    private IBatchInfoByStatueView batchInfoView;
    @Autowired
    IUnitModel unitModel;

    public BatchInfoByStatuePresenterImpl(IBatchInfoByStatueView batchInfoView){
        this.batchInfoView = batchInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getBatchInfo(String unitID,int unitType,String batchID) {
        batchInfoView.openRefresh();
        unitModel.getBatchDetail(unitID, unitType, batchID, new OnDataListener<CommonUnitBatchInfoBean>() {
            @Override
            public void success(CommonUnitBatchInfoBean response) {
                batchInfoView.clearRefresh();
                batchInfoView.refreshBatchView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                batchInfoView.clearRefresh();
                batchInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
