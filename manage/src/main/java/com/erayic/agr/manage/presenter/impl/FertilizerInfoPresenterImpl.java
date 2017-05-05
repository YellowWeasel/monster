package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.manage.presenter.IFertilizerInfoPresenter;
import com.erayic.agr.manage.view.IFertilizerInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class FertilizerInfoPresenterImpl implements IFertilizerInfoPresenter {

    private IFertilizerInfoView fertilizerInfoView;
    @Autowired
    IResourceModel resourceModel;

    public FertilizerInfoPresenterImpl(IFertilizerInfoView fertilizerInfoView) {
        this.fertilizerInfoView = fertilizerInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void fertilizerCheck(String pID) {
        fertilizerInfoView.showLoading();
        resourceModel.fertilizerCheck(pID, new OnDataListener<CommonFertilizerBean>() {
            @Override
            public void success(CommonFertilizerBean response) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.updateSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void addFertilizer(CommonFertilizerBean bean) {
        fertilizerInfoView.showLoading();
        resourceModel.addFertilizer(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.showToast("保存成功");
                fertilizerInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getSpecifyResources(String resID, int type) {
        fertilizerInfoView.showLoading();
        resourceModel.getSpecifyResourcesByFertilizer(resID, type, new OnDataListener<CommonFertilizerBean>() {
            @Override
            public void success(CommonFertilizerBean response) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.updateSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteResource(String resID, int type) {
        fertilizerInfoView.showLoading();
        resourceModel.deleteResource(resID, type, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.showToast("已删除");
                fertilizerInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                fertilizerInfoView.dismissLoading();
                fertilizerInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
