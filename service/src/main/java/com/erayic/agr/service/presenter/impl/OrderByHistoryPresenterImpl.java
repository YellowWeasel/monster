package com.erayic.agr.service.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IServerModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.service.presenter.IOrderByHistoryPresenter;
import com.erayic.agr.service.view.IOrderByHistoryView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByHistoryPresenterImpl implements IOrderByHistoryPresenter {

    private IOrderByHistoryView historyView;
    @Autowired
    IServerModel serverModel;

    public OrderByHistoryPresenterImpl(IOrderByHistoryView historyView) {
        this.historyView = historyView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getHistoryOrderListByUser(int pageSize) {
        historyView.openRefresh();
        serverModel.getHistoryOrderListByUser(1, pageSize, new OnDataListener<List<CommonOrderBean>>() {
            @Override
            public void success(List<CommonOrderBean> response) {
                historyView.clearRefresh();
                historyView.refreshOrderView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                historyView.clearRefresh();
                historyView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                historyView.refreshOrderView(null);
            }
        });
    }

    @Override
    public void getHistoryOrderListByUser(int pageNum, int pageSize) {
        serverModel.getHistoryOrderListByUser(pageNum, pageSize, new OnDataListener<List<CommonOrderBean>>() {
            @Override
            public void success(List<CommonOrderBean> response) {
                historyView.loadMoreSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                historyView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                historyView.loadMoreFailure();
            }
        });
    }
}
