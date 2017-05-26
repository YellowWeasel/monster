package com.erayic.agr.jobs.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IWorkModel;
import com.erayic.agr.jobs.presenter.IArrangeJobPresenter;
import com.erayic.agr.jobs.view.IArrangeJobView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ArrangeJobPresenterImpl implements IArrangeJobPresenter {

    private IArrangeJobView arrangeJobView;
    @Autowired
    IWorkModel workModel;

    public ArrangeJobPresenterImpl(IArrangeJobView arrangeJobView) {
        this.arrangeJobView = arrangeJobView;
        ARouter.getInstance().inject(this);
    }

}
