package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IManageModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.manage.CommonBasePositionBean;
import com.erayic.agr.manage.presenter.IBasePositionPresenter;
import com.erayic.agr.manage.view.IBasePositionView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BasePositionPresenterImpl implements IBasePositionPresenter {

    private IBasePositionView positionView;
    @Autowired
    IManageModel manageModel;

    public BasePositionPresenterImpl(IBasePositionView positionView) {
        this.positionView = positionView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void setBasePosition(String baseID,CommonBasePositionBean bean) {
        positionView.showLoading();
        manageModel.setBasePosition(baseID, bean, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                positionView.dismissLoading();
                positionView.showToast("位置保存成功");
                positionView.updateSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                positionView.dismissLoading();
                positionView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
