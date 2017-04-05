package com.erayic.agr.view.impl;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.R;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.view.IWelcomeView;
import com.jaeger.library.StatusBarUtil;

import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity implements IWelcomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        toMainActivity();
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    public boolean isSupportSwipeBack() {
        //重写父类方法 设置滑动返回不可用
        return false;
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


    @Override
    public void toMainActivity() {
        baseHandler.postDelayed(new TimerTask() {
            @Override
            public void run() {
                ARouter.getInstance().build("/main/MainActivity").navigation();
                WelcomeActivity.this.finish();
            }
        }, 3000);
    }

}
