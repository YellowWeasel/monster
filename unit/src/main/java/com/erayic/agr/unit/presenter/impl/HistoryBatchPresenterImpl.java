package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.unit.presenter.IHistoryBatchPresenter;
import com.erayic.agr.unit.view.IHistoryBatchListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class HistoryBatchPresenterImpl implements IHistoryBatchPresenter {

    private IHistoryBatchListView historyBatchView;
    @Autowired
    IUnitModel unitModel;

    public HistoryBatchPresenterImpl(IHistoryBatchListView historyBatchView) {
        this.historyBatchView = historyBatchView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllBatchByHistory(String unitID, int pageSize) {
        historyBatchView.openRefresh();
        unitModel.getAllBatchByHistory(unitID, EnumUnitType.TYPE_PLOTS, 1, pageSize, new OnDataListener<List<CommonUnitBatchInfoBean.Batch>>() {
            @Override
            public void success(List<CommonUnitBatchInfoBean.Batch> response) {
                historyBatchView.clearRefresh();
                historyBatchView.refreshBatchView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                historyBatchView.clearRefresh();
                historyBatchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getAllBatchByHistory(String unitID, int pageNum, int pageSize) {
        unitModel.getAllBatchByHistory(unitID, EnumUnitType.TYPE_PLOTS, pageNum, pageSize, new OnDataListener<List<CommonUnitBatchInfoBean.Batch>>() {
            @Override
            public void success(List<CommonUnitBatchInfoBean.Batch> response) {
                historyBatchView.loadMoreSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                historyBatchView.loadMoreFailure();
                historyBatchView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
