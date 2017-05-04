package com.erayic.agr.serverproduct.view.impl;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;

/**
 * Created by wxk on 2017/5/4.
 */

@Route(path = "/serverproduct/activity/ReportingActivity", name = "我的服务")
public class ReportingActivity extends BaseActivity {

    @Autowired
    String serviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
