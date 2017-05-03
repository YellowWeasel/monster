package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.manage.presenter.IPesticideListPresenter;
import com.erayic.agr.manage.view.IPesticideListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class PesticideListPresenterImpl implements IPesticideListPresenter {

    private IPesticideListView pesticideListView;

    @Autowired
    IResourceModel resourceModel;

    public PesticideListPresenterImpl(IPesticideListView pesticideListView){
        this.pesticideListView = pesticideListView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getResourceByType() {
        pesticideListView.openRefresh();
        //生产资料类型（1：农药，2：肥料，3：饲料，4：种子）
        resourceModel.getResourceByType(1, new OnDataListener<List<Object>>() {
            @Override
            public void success(List<Object> response) {
                pesticideListView.clearRefresh();
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideListView.clearRefresh();
                pesticideListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
