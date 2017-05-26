package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.manage.presenter.ISelectPresenter;
import com.erayic.agr.manage.view.ISelectView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class SelectPresenterImpl implements ISelectPresenter {

    private ISelectView selectProduceView;
    @Autowired
    IResourceModel resourceModel;
    @Autowired
    IUserModel userModel;

    public SelectPresenterImpl(ISelectView selectProduceView) {
        this.selectProduceView = selectProduceView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllProduct() {
        selectProduceView.openRefresh();
        resourceModel.getAllProduct(new OnDataListener<List<CommonProduceListBean>>() {
            @Override
            public void success(List<CommonProduceListBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshProductView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getResourceByType(int type) {
        selectProduceView.openRefresh();
        resourceModel.getResourceByType(type, new OnDataListener<List<CommonResourceBean>>() {
            @Override
            public void success(List<CommonResourceBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshResourceView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });

    }

    @Override
    public void getAllUserByBase(String baseID) {
        selectProduceView.openRefresh();
        userModel.getAllUserBySpecifyBase(baseID, new OnDataListener<List<CommonPersonnelBean>>() {
            @Override
            public void success(List<CommonPersonnelBean> response) {
                selectProduceView.clearRefresh();
                selectProduceView.refreshUserView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                selectProduceView.clearRefresh();
                selectProduceView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
