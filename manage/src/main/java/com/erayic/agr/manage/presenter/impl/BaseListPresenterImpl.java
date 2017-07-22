package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IManageModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.manage.presenter.IBaseListPresenter;
import com.erayic.agr.manage.view.IBaseListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BaseListPresenterImpl implements IBaseListPresenter {

    private IBaseListView baseListView;
    @Autowired
    IManageModel manageModel;

    public BaseListPresenterImpl(IBaseListView baseListView) {
        this.baseListView = baseListView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getBaseByEnt() {
        baseListView.openRefresh();
        manageModel.getBaseByEnt(new OnDataListener<List<CommonBaseListBean>>() {
            @Override
            public void success(List<CommonBaseListBean> response) {
                baseListView.clearRefresh();
                baseListView.refreshBaseListView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                baseListView.clearRefresh();
                baseListView.refreshBaseListView(null);
                baseListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void addBaseByEnt(String newBaseName,String phoneCode) {
        baseListView.showLoading();
        manageModel.addBaseByEnt(newBaseName,phoneCode, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                baseListView.dismissLoading();
                baseListView.addBaseSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                baseListView.dismissLoading();
                baseListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
