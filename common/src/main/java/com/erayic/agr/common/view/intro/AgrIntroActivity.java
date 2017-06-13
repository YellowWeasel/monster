package com.erayic.agr.common.view.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.util.ErayicApp;
import com.github.paolorotolo.appintro.AppIntro;
import com.jaeger.library.StatusBarUtil;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/common/activity/AgrIntroActivity", name = "引导页")
public class AgrIntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        addSlide((Fragment) ARouter.getInstance().build("/common/fragment/AgrIntroFragment").withInt("position", 0).navigation());
        addSlide((Fragment) ARouter.getInstance().build("/common/fragment/AgrIntroFragment").withInt("position", 1).navigation());
        addSlide((Fragment) ARouter.getInstance().build("/common/fragment/AgrIntroFragment").withInt("position", 2).navigation());
        addSlide((Fragment) ARouter.getInstance().build("/common/fragment/AgrIntroFragment").withInt("position", 3).navigation());

        showSkipButton(false);//是否跳跃
        showSeparator(false);//隐藏线条
        setProgressButtonEnabled(true);//设置完成
        setImageNextButton(null);//下一页
        setDoneText("进入农小二");

//        setVibrate(true);//设置震动
//        setVibrateIntensity(30);//设置震动强度
//        setFadeAnimation();
    }

    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PreferenceUtils.putParam("versionCode", ErayicApp.getVersionCode(getApplicationContext()));
                ARouter.getInstance().build("/main/Activity/MainActivity")
                        .navigation(AgrIntroActivity.this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                //到启动页面
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                //未找到启动页面
                                ErayicToast.TextToast(getApplicationContext(), "启动APP失败，请联系我们");
                                AgrIntroActivity.this.finish();
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                //找到启动页面并且通过拦截器成功
                                AgrIntroActivity.this.finish();
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                //找到启动页面被拦截器执行打断
                                AgrIntroActivity.this.finish();
                            }
                        });
                finish();
            }
        });
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
