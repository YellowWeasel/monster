package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.manage.presenter.IUserSelectPresenter;
import com.erayic.agr.manage.view.IUserSelectView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UserSelectPresenterImpl implements IUserSelectPresenter {

    private IUserSelectView userSelectView;
    @Autowired
    IUserModel userModel;

    public UserSelectPresenterImpl(IUserSelectView userSelectView){
        this.userSelectView = userSelectView;
        ARouter.getInstance().inject(this);
    }


    @Override
    public void getAllUserBySpecifyBase(String baseID) {
        userSelectView.openRefresh();
        userModel.getAllUserBySpecifyBase(baseID, new OnDataListener<List<CommonPersonnelBean>>() {
            @Override
            public void success(List<CommonPersonnelBean> response) {
                userSelectView.clearRefresh();
                userSelectView.refreshUserView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                userSelectView.clearRefresh();
                userSelectView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
