package com.erayic.agr.manage.view.impl;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.manage.presenter.ISeedInfoPresenter;
import com.erayic.agr.manage.presenter.impl.SeedInfoPresenterImpl;
import com.erayic.agr.manage.view.ISeedInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/SeedInfoActivity", name = "种子详情")
public class SeedInfoActivity extends BaseActivity implements ISeedInfoView {


    private ISeedInfoPresenter presenter;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        presenter = new SeedInfoPresenterImpl(this);
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg);
            }
        });
    }

}
