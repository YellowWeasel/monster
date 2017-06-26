package com.erayic.agr.common.base;

import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.http.HttpRetrofit;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：MultiDexApplication解决64K问题
 */

public abstract class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
        initConfig();
        initNet();
        initBGASwipeBackManager();
    }

    /**
     * 初始化activity右滑关闭页面
     */
    private void initBGASwipeBackManager() {
        BGASwipeBackManager.getInstance().init(this);
    }

    /**
     * 初始化ARouter
     */
    private void initARouter() {
        ARouter.openLog();// 打印日志
        ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
    }

    /**
     * 初始化网络访问
     */
    private void initNet() {
        HttpRetrofit.init(this);
    }

    /**
     * 初始化全局数据
     */
    private void initConfig() {
//        UserProgramConfigManage.init(getApplicationContext());
        PreferenceUtils.initialize(getApplicationContext());
    }

    /**
     * 初始化图片选择库
     */
    private void initPictureSelector() {

    }
}
