package com.erayic.agr.view.impl;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.R;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.permission.PerUtils;
import com.erayic.agr.common.permission.PermissionManager;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.view.IWelcomeView;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.TimerTask;

import io.reactivex.functions.Consumer;


public class WelcomeActivity extends Activity implements IWelcomeView {

    private ImageView welcome_img;
    private Animation mAnimation = null;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mContext = this;
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
        initPermission();
    }

    /**
     * 权限检查
     */
    public void initPermission() {


        new RxPermissions(WelcomeActivity.this).request(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //所有权限都开启aBoolean才为true，否则为false
                            toMainActivity();
                        } else {
                            PermissionDenied();
                        }
                    }
                });
    }

    protected void setStatusBar() {
//        StatusBarUtil.setTranslucentForCoordinatorLayout(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
    }
//
//    @Override
//    public boolean isSupportSwipeBack() {
//        //重写父类方法 设置滑动返回不可用
//        return false;
//    }

    private void PermissionDenied() {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setMessage("您有应用需要的权限未开启，是否前去设置？")
                .setCancelable(false)
                .setPositiveButton(R.string.per_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PerUtils.openApplicationSettings(mContext, R.class.getPackage().getName());
                    }
                })
                .setNegativeButton(R.string.per_cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ErayicToast.TextToast(mContext, "权限未通过，关闭应用");//
                        ErayicStack.getInstance().finishCurrentActivity();
                    }
                }).create();
        alertDialog.show();
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
        }, 200);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handleResult(requestCode, permissions, grantResults);
    }
}
