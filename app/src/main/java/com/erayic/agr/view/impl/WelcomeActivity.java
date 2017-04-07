package com.erayic.agr.view.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.R;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
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
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg, ErayicToast.BOTTOM);
            }
        });
    }


    @Override
    public void toMainActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new TimerTask() {
            @Override
            public void run() {
                ARouter.getInstance().build("/main/Activity/MainActivity")
                        .navigation(WelcomeActivity.this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                delayFinish();
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                showToast("启动APP失败，请联系我们");
                                delayFinish();
                            }

                            @Override
                            public void onArrival(Postcard postcard) {

                            }
                        });
            }
        }, 3000);
    }

    @Override
    public void delayFinish() {
        new Handler(Looper.getMainLooper()).postDelayed(new TimerTask() {
            @Override
            public void run() {
                WelcomeActivity.this.finish();
            }
        }, 1000);
    }

}
