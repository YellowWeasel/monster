package com.erayic.agr.view.impl;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.R;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.view.IMainView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/main/MainActivity", name = "APP主承载页面")
public class MainActivity extends BaseActivity implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void showToast(final String msg) {
        baseHandler.post(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg, ErayicToast.BOTTOM);
            }
        });
    }
}
