package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.unit.presenter.IBatchSuggestInfoPresenter;
import com.erayic.agr.unit.view.IBatchSuggestInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchSuggestInfoPresenterImpl implements IBatchSuggestInfoPresenter {

    private IBatchSuggestInfoView batchSuggestInfoView;
    @Autowired
    IUnitModel unitModel;

    public BatchSuggestInfoPresenterImpl(IBatchSuggestInfoView batchSuggestInfoView) {
        this.batchSuggestInfoView = batchSuggestInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getSuggestDetail(String unitID, int type, String batchID) {
        batchSuggestInfoView.showLoading();
        unitModel.getSuggestDetail(unitID, type, batchID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                batchSuggestInfoView.dismissLoading();
                batchSuggestInfoView.refreshBatchSuggestView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                batchSuggestInfoView.dismissLoading();
                batchSuggestInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
