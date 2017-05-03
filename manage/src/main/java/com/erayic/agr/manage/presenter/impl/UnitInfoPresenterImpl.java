package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IManageModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonMapArrayBean;
import com.erayic.agr.common.net.back.CommonUnitInfoBean;
import com.erayic.agr.manage.presenter.IUnitInfoPresenter;
import com.erayic.agr.manage.view.IUnitInfoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitInfoPresenterImpl implements IUnitInfoPresenter {

    private IUnitInfoView unitInfoView;
    @Autowired
    IManageModel manageModel;

    public UnitInfoPresenterImpl(IUnitInfoView unitInfoView) {
        this.unitInfoView = unitInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getUnitDetailInfo(String unitID) {
        unitInfoView.openRefresh();
        manageModel.getUnitDetailInfo(unitID, 2, new OnDataListener<CommonUnitInfoBean>() {
            @Override
            public void success(CommonUnitInfoBean response) {
                unitInfoView.clearRefresh();
                unitInfoView.refreshUnitInfoView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                unitInfoView.clearRefresh();
                unitInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateBlockInfo(CommonUnitInfoBean bean) {
        List<String> list = new ArrayList<>();
        for (CommonMapArrayBean mapArrayBean : bean.getWorkers()) {
            list.add(mapArrayBean.getKey());
        }
        unitInfoView.showLoading();
        manageModel.updateBlockInfo(bean.getUnitID(), bean.getName(), bean.getArea(), bean.getRegion(), list, bean.isGreenhouse(), new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                unitInfoView.dismissLoading();
                unitInfoView.updateSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                unitInfoView.dismissLoading();
                unitInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
