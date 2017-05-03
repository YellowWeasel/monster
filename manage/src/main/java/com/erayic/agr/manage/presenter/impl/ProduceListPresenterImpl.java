package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.manage.presenter.IProduceListPresenter;
import com.erayic.agr.manage.view.IProduceListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ProduceListPresenterImpl implements IProduceListPresenter {

    private IProduceListView produceListView;
    @Autowired
    IResourceModel resourceModel;

    public ProduceListPresenterImpl(IProduceListView produceListView){
        this.produceListView = produceListView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void createProduct(String productName) {
        produceListView.showLoading();
        resourceModel.createProduct(productName, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                produceListView.dismissLoading();
                produceListView.addSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                produceListView.dismissLoading();
                produceListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getAllProduct() {
        produceListView.openRefresh();
        resourceModel.getAllProduct(new OnDataListener<List<CommonProduceListBean>>() {
            @Override
            public void success(List<CommonProduceListBean> response) {
                produceListView.clearRefresh();
                produceListView.refreshPersonnelView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                produceListView.clearRefresh();
                produceListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
