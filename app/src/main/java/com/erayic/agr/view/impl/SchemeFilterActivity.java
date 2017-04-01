package com.erayic.agr.view.impl;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;

public class SchemeFilterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        直接通过ARouter处理外部Uri
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                finish();
            }

            @Override
            public void onLost(Postcard postcard) {
                finish();
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
