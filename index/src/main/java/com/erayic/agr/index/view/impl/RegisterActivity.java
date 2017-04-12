package com.erayic.agr.index.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.index.R;
import com.erayic.agr.index.R2;
import com.erayic.agr.index.view.IRegisterView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/RegisterActivity", name = "注册选择")
public class RegisterActivity extends BaseActivity implements IRegisterView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.index_register_ent)
    LinearLayout indexRegisterEnt;
    @BindView(R2.id.index_register_user)
    LinearLayout indexRegisterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_register);
    }

    @Override
    public void initView() {
        toolbar.setTitle("注册类型");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home://返回
            {
                finish();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R2.id.index_register_ent)
    public void onIndexRegisterEntClicked() {
        ARouter.getInstance().build("/index/Activity/RegisterByEntActivity").navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {

            }

            @Override
            public void onArrival(Postcard postcard) {
                RegisterActivity.this.finish();
            }

            @Override
            public void onInterrupt(Postcard postcard) {

            }
        });
        finish();
    }

    @OnClick(R2.id.index_register_user)
    public void onIndexRegisterUserClicked() {
        ARouter.getInstance().build("/index/Activity/RegisterByUserActivity").navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {

            }

            @Override
            public void onArrival(Postcard postcard) {
                RegisterActivity.this.finish();
            }

            @Override
            public void onInterrupt(Postcard postcard) {

            }
        });
    }
}
