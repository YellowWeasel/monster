package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;

/**
 * Created by wxk on 2017/5/9.
 */

@Route(path = "/serverproduct/fragment/WeatherTenDayReportingFragment", name = "我的")
public class WeatherTenDayReportingFragment extends BaseFragment {
    @Autowired
    WeatherTendayReportingData reportingData;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_weather_ten_day;
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
    @Override
    protected void initData() {

    }
}
