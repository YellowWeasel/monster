package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.manage.presenter.IResourceListPresenter;
import com.erayic.agr.manage.view.IResourceListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ResourceListPresenterImpl implements IResourceListPresenter {

    private IResourceListView pesticideListView;

    @Autowired
    IResourceModel resourceModel;

    public ResourceListPresenterImpl(IResourceListView pesticideListView){
        this.pesticideListView = pesticideListView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getResourceByType(int type) {
        pesticideListView.openRefresh();
        //生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
        resourceModel.getResourceByType(type, new OnDataListener<List<CommonResourceBean>>() {
            @Override
            public void success(List<CommonResourceBean> response) {
                pesticideListView.clearRefresh();
                pesticideListView.refreshPersonnelView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideListView.clearRefresh();
                pesticideListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
