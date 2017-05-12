package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUnitModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.unit.presenter.IUnitListPresenter;
import com.erayic.agr.unit.view.IUnitListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListPresenterImpl implements IUnitListPresenter {

    private IUnitListView unitListView;

    @Autowired
    IUnitModel unitModel;

    public UnitListPresenterImpl(IUnitListView unitListView) {
        this.unitListView = unitListView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getAllUnit() {
        unitListView.openRefresh();
        unitModel.getAllUnit(EnumUnitType.TYPE_PLOTS, new OnDataListener<List<CommonUnitListBean>>() {
            @Override
            public void success(List<CommonUnitListBean> response) {
                unitListView.clearRefresh();
                unitListView.refreshUnitView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                unitListView.clearRefresh();
                unitListView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
