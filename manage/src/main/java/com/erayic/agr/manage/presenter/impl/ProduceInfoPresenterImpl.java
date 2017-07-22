package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonProduceInfoBean;
import com.erayic.agr.manage.presenter.IProduceInfoPresenter;
import com.erayic.agr.manage.view.IProduceInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ProduceInfoPresenterImpl implements IProduceInfoPresenter {

    private IProduceInfoView infoView;
    @Autowired
    IResourceModel model;

    public ProduceInfoPresenterImpl(IProduceInfoView infoView) {
        this.infoView = infoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getProductDetail(String proID) {
        infoView.showLoading();
        model.getProductDetail(proID, new OnDataListener<CommonProduceInfoBean>() {
            @Override
            public void success(CommonProduceInfoBean response) {
                infoView.dismissLoading();
                infoView.refreshPersonnelView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.dismissLoading();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                infoView.refreshPersonnelView(null);
            }
        });
    }

    @Override
    public void updateProduct(String proID, String productName, int classicID, String descript) {
        infoView.showLoading();
        model.updateProduct(proID, productName, classicID, descript, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                infoView.dismissLoading();
                infoView.noticeRefresh();
                infoView.finishCurrent();
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.dismissLoading();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteProduct(String proID) {
        infoView.showLoading();
        model.deleteProduct(proID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                infoView.dismissLoading();
                infoView.noticeRefresh();
                infoView.showToast("删除成功");
                infoView.finishCurrent();
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.dismissLoading();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateProductIcon(String proID, String icon) {
        infoView.showLoading();
        model.updateProductIcon(proID, icon, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                infoView.dismissLoading();
                infoView.noticeRefresh();
                infoView.updateIconSure(response.toString());
            }

            @Override
            public void fail(int errCode, String msg) {
                infoView.dismissLoading();
                infoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void addProductPhoto(String proID, String photo) {

    }

    @Override
    public void delProductPhoto(String imgID, String imgPath) {

    }
}
