package com.erayic.agr.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.R;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.view.IWelcomeView;
import com.jaeger.library.StatusBarUtil;

import java.util.TimerTask;

public class WelcomeActivity extends Activity implements IWelcomeView {

    private ImageView welcome_img;
    private Animation mAnimation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        setStatusBar();
        initData();
    }

    public void initView() {
        welcome_img = (ImageView) findViewById(R.id.welcome_img);
        mAnimation = AnimationUtils
                .loadAnimation(this, R.anim.welcome_image_in);
        welcome_img.startAnimation(mAnimation);
    }

    public void initData() {
        //测试引导数据
//        PreferenceUtils.putParam("versionCode", 0);
        toMainActivity();
    }

    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }
//
//    @Override
//    public boolean isSupportSwipeBack() {
//        //重写父类方法 设置滑动返回不可用
//        return false;
//    }

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
                                //到启动页面
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                //未找到启动页面
                                showToast("启动APP失败，请联系我们");
                                delayFinish();
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                //找到启动页面并且通过拦截器成功
                                delayFinish();
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                //找到启动页面被拦截器执行打断
                                delayFinish();
                            }
                        });
            }
        }, 1500);
    }

    @Override
    public void delayFinish() {
        new Handler(Looper.getMainLooper()).postDelayed(new TimerTask() {
            @Override
            public void run() {
                WelcomeActivity.this.finish();
            }
        }, 0);
    }

}
