package com.erayic.agr.view.impl;

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
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.permission.PerUtils;
import com.erayic.agr.common.permission.PerimissionsCallback;
import com.erayic.agr.common.permission.PermissionEnum;
import com.erayic.agr.common.permission.PermissionManager;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.view.IWelcomeView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.TimerTask;

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
        ArrayList<PermissionEnum> permissionList = new ArrayList<>();
        permissionList.add(PermissionEnum.ACCESS_COARSE_LOCATION);
        permissionList.add(PermissionEnum.ACCESS_FINE_LOCATION);
        permissionList.add(PermissionEnum.WRITE_EXTERNAL_STORAGE);
        permissionList.add(PermissionEnum.READ_EXTERNAL_STORAGE);
        permissionList.add(PermissionEnum.READ_PHONE_STATE);
        permissionList.add(PermissionEnum.CAMERA);
//        permissionList.add(PermissionEnum.GROUP_CAMERA);
//        permissionList.add(PermissionEnum.GROUP_LOCATION);
//        permissionList.add(PermissionEnum.GROUP_STORAGE);
        PermissionManager
                .with(WelcomeActivity.this)
                .tag(1000)
                .permissions(permissionList)
                .callback(new PerimissionsCallback() {
                    @Override
                    public void onGranted(ArrayList<PermissionEnum> grantedList) {
                        //权限被允许了
                        toMainActivity();
                    }

                    @Override
                    public void onDenied(ArrayList<PermissionEnum> deniedList) {
                        //权限被拒绝了
//                        Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                        PermissionDenied(deniedList);
                    }
                })
                .checkAsk();
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

    private void PermissionDenied(final ArrayList<PermissionEnum> permissionsDenied) {
        StringBuilder msgCN = new StringBuilder();
        for (int i = 0; i < permissionsDenied.size(); i++) {

            if (i == permissionsDenied.size() - 1) {
                msgCN.append(permissionsDenied.get(i).getName_cn());
            } else {
                msgCN.append(permissionsDenied.get(i).getName_cn() + ",");
            }
        }
        if (mContext == null) {
            return;
        }

        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setMessage(String.format(mContext.getResources().getString(R.string.permission_explain), msgCN.toString()))
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
