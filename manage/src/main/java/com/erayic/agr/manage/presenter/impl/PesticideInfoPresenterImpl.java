package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.manage.presenter.IPesticideInfoPresenter;
import com.erayic.agr.manage.view.IPesticideInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class PesticideInfoPresenterImpl implements IPesticideInfoPresenter {

    private IPesticideInfoView pesticideInfoView;

    @Autowired
    IResourceModel resourceModel;

    public PesticideInfoPresenterImpl(IPesticideInfoView pesticideInfoView) {
        this.pesticideInfoView = pesticideInfoView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void pestilizerCheck(String pID) {
        pesticideInfoView.showLoading();
        resourceModel.pestilizerCheck(pID, new OnDataListener<CommonPesticideBean>() {
            @Override
            public void success(CommonPesticideBean response) {
                response.setReadOnly(true);
                pesticideInfoView.dismissLoading();
                pesticideInfoView.updateSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void getSpecifyResources(String resID, int type) {
        pesticideInfoView.showLoading();
        resourceModel.getSpecifyResourcesByPesticide(resID, type, new OnDataListener<CommonPesticideBean>() {
            @Override
            public void success(CommonPesticideBean response) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.updateSure(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteResource(String resID, int type) {
        pesticideInfoView.showLoading();
        resourceModel.deleteResource(resID, type, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("删除成功");
                pesticideInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void savePesticide(CommonPesticideBean bean) {
        pesticideInfoView.showLoading();
        resourceModel.savePesticide(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("保存生产资料成功");
                pesticideInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void savePesticideByUserDefine(CommonPesticideBean bean) {
        pesticideInfoView.showLoading();
        resourceModel.savePesticideByUserDefine(bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("保存生产资料成功");
                pesticideInfoView.saveSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                pesticideInfoView.dismissLoading();
                pesticideInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

}
