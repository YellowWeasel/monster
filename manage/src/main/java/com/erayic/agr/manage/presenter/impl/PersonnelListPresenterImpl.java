package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.manage.presenter.IPersonnelListPresenter;
import com.erayic.agr.manage.view.IPersonnelListView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class PersonnelListPresenterImpl implements IPersonnelListPresenter {

    private IPersonnelListView personnelView;
    @Autowired
    IUserModel userModel;

    public PersonnelListPresenterImpl(IPersonnelListView personnelView) {
        this.personnelView = personnelView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void GetAllUserByBase() {
        personnelView.openRefresh();
        userModel.GetAllUserByBase(new OnDataListener<List<CommonPersonnelBean>>() {
            @Override
            public void success(List<CommonPersonnelBean> response) {
                personnelView.clearRefresh();
                personnelView.refreshPersonnelView(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                personnelView.clearRefresh();
                personnelView.refreshPersonnelView(null);
                personnelView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
