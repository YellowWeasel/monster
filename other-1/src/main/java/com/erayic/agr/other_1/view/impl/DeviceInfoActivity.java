package com.erayic.agr.other_1.view.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.other_1.view.IDeviceInfoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/device/activity/DeviceInfoActivity", name = "设备信息")
public class DeviceInfoActivity extends BaseActivity implements IDeviceInfoView {

    @Autowired
    String serialNum;//	控制设备序号
    @Autowired
    String deviceName;//设备名称

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void showToast(String msg) {

    }
}
