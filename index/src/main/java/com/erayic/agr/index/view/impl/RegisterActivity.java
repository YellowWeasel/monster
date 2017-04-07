package com.erayic.agr.index.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.index.R;
import com.erayic.agr.index.view.IRegisterView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/index/Activity/RegisterActivity", name = "注册选择")
public class RegisterActivity extends BaseActivity implements IRegisterView, View.OnClickListener {

    private Toolbar toolbar;
    private LinearLayout index_register_ent, index_register_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_register);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("注册类型");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        index_register_ent = (LinearLayout) findViewById(R.id.index_register_ent);
        index_register_ent.setOnClickListener(this);
        index_register_user = (LinearLayout) findViewById(R.id.index_register_user);
        index_register_user.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.index_register_ent) {
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
            });
            finish();
        } else if (v.getId() == R.id.index_register_user) {
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
            });
        }
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

}
