package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonSeedBean;
import com.erayic.agr.manage.presenter.ISeedInfoPresenter;
import com.erayic.agr.manage.view.ISeedInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class SeedInfoPresenterImpl implements ISeedInfoPresenter {


    private ISeedInfoView seedInfoView;
    @Autowired
    IResourceModel resourceModel;

    public SeedInfoPresenterImpl(ISeedInfoView seedInfoView) {
        this.seedInfoView = seedInfoView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getSpecifyResources(String resID, int type) {
        seedInfoView.showLoading();
        resourceModel.getSpecifyResourcesBySeed(resID, type, new OnDataListener<CommonSeedBean>() {
            @Override
            public void success(CommonSeedBean response) {
                seedInfoView.dismissLoading();
                seedInfoView.updateSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                seedInfoView.dismissLoading();
                seedInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteResource(String resID, int type) {
        seedInfoView.showLoading();
        resourceModel.deleteResource(resID, type, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                seedInfoView.dismissLoading();
                seedInfoView.showToast("删除生产资料成功");
                seedInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                seedInfoView.dismissLoading();
                seedInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void saveSeed(CommonSeedBean bean) {
        seedInfoView.showLoading();
        resourceModel.saveSeed(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                seedInfoView.dismissLoading();
                seedInfoView.showToast("保存生产资料成功");
                seedInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                seedInfoView.dismissLoading();
                seedInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
