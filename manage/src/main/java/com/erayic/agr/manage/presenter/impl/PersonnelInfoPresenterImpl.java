package com.erayic.agr.manage.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IUserModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.manage.presenter.IPersonnelInfoPresenter;
import com.erayic.agr.manage.view.IPersonnelInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class PersonnelInfoPresenterImpl implements IPersonnelInfoPresenter {

    private IPersonnelInfoView personnelInfoView;
    @Autowired
    IUserModel userModel;

    public PersonnelInfoPresenterImpl(IPersonnelInfoView personnelInfoView) {
        this.personnelInfoView = personnelInfoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void sendInvite(String tel, String name, int role) {
        personnelInfoView.showLoading();
        userModel.sendInvite(tel, name, role, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                personnelInfoView.dismissLoading();
                personnelInfoView.showToast("邀请码已发送至该用户手机");
                personnelInfoView.sendSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                personnelInfoView.dismissLoading();
                personnelInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void updateUserInfo(String userID, String tel, String name, int role) {
        personnelInfoView.showLoading();
        userModel.updateUserInfo(userID, name, tel, role, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                personnelInfoView.dismissLoading();
                personnelInfoView.showToast("更新成功");
                personnelInfoView.sendSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                personnelInfoView.dismissLoading();
                personnelInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }

    @Override
    public void deleteUser(String userID) {
        personnelInfoView.showLoading();
        userModel.deleteUser(userID, new OnDataListener<Object>() {
            @Override
            public void success(Object response) {
                personnelInfoView.dismissLoading();
                personnelInfoView.showToast("删除用户成功");
                personnelInfoView.sendSure();
            }

            @Override
            public void fail(int errCode, String msg) {
                personnelInfoView.dismissLoading();
                personnelInfoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
            }
        });
    }
}
