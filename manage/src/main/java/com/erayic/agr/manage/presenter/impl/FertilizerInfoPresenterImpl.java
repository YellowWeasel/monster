package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
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

}
