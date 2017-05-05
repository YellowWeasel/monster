package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IResourceModel;
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

}
