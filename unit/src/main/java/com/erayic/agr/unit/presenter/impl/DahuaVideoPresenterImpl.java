package com.erayic.agr.unit.presenter.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.model.IDeviceModel;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;
import com.erayic.agr.unit.presenter.IDahuaVideoPresenter;
import com.erayic.agr.unit.view.IDaHuaVideoView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class DahuaVideoPresenterImpl implements IDahuaVideoPresenter {

    private IDaHuaVideoView videoView;
    @Autowired
    IDeviceModel deviceModel;

    public DahuaVideoPresenterImpl(IDaHuaVideoView videoView) {
        this.videoView = videoView;
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getMonitorInfo(String serialNum) {
        videoView.showLoading();
        deviceModel.getMonitorInfo(serialNum, new OnDataListener<CommonMonitorInfoEntity>() {
            @Override
            public void success(CommonMonitorInfoEntity response) {
                videoView.loadingConnection(response);
            }

            @Override
            public void fail(int errCode, String msg) {
                videoView.showToast("错误代码：" + errCode + "\n描述：" + msg);
                videoView.loginFail();
            }
        });
    }

}
