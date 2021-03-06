package com.erayic.agr.view.impl;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.R;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.dahua.DaHuaVideoView;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.service.ErayicService;
import com.erayic.agr.common.util.ErayicApp;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.NoScrollViewPager;
import com.erayic.agr.common.view.update.UpdateAppHttpUtil;
import com.erayic.agr.presenter.IMainPresenter;
import com.erayic.agr.presenter.impl.MainPresenterImpl;
import com.erayic.agr.view.IMainView;
import com.jaeger.library.StatusBarUtil;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/main/Activity/MainActivity", name = "APP主承载页面")
public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.main_view_pager)
    NoScrollViewPager mainViewPager;
    @BindView(R.id.main_tab_bar)
    JPTabBar mainTabBar;

    private IMainPresenter presenter;
    private FragmentPagerAdapter adapter;

    private String[] titlesName;
    private int[] titleNormalIcons;
    private int[] titleSelectedIcons;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        //传入一定要集成继承ViewPager
        mainTabBar.setContainer(mainViewPager);
        mainViewPager.setNoScroll(true);//不可滑动
        mainTabBar.setTabListener(new OnTabSelectListener() {//TabBar回调事件
            @Override
            public void onTabSelect(int index) {

            }
        });
        mainTabBar.setDismissListener(new BadgeDismissListener() {
            @Override
            public void onDismiss(int position) {

            }
        });
    }

    @Override
    public void initData() {
        presenter = new MainPresenterImpl(this);
        presenter.getUserInfo();
    }

    /**
     * 根据网络数据加载布局
     */
    @Override
    public void initNetData() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (PreferenceUtils.getParam("UserRole", -1)) {
                    case EnumUserRole.Role_Admin:
                    case EnumUserRole.Role_Manager:
                    case EnumUserRole.Role_Usage://用户
                    default: {
                        //去掉首页
                        titlesName = new String[]{"管理", "作业", "服务", "我的"};
                        titleNormalIcons = new int[]{R.drawable.app_base_default_unit_press, R.drawable.app_base_default_job_press,
                                R.drawable.app_base_default_service_press, R.drawable.app_base_default_mine_press};
                        titleSelectedIcons = new int[]{R.drawable.app_base_default_unit_nomal, R.drawable.app_base_default_job_nomal,
                                R.drawable.app_base_default_service_nomal, R.drawable.app_base_default_mine_nomal};
                        fragments = new Fragment[]{
                                (Fragment) ARouter.getInstance().build("/unit/fragment/UnitListFragment").withString("titleName", "生产管理").navigation(),
                                (Fragment) ARouter.getInstance().build("/jobs/fragment/JobsListFragment").withString("titleName", "今日作业").navigation(),
                                (Fragment) ARouter.getInstance().build("/service/fragment/ServiceEntranceFragment").withString("titleName", "我的服务").navigation(),
                                (Fragment) ARouter.getInstance().build("/manage/fragment/ManageMineFragment").withString("titleName", "我的").navigation()};
                    }
                    break;
                }
                mainTabBar.setTitles(titlesName)
                        .setNormalIcons(titleNormalIcons)
                        .setSelectedIcons(titleSelectedIcons)
                        .generate();
                adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

                    @Override
                    public int getCount() {
                        return fragments.length;
                    }

                    @Override
                    public Fragment getItem(int arg0) {
                        return fragments[arg0];
                    }
                };
                mainViewPager.setAdapter(adapter);
                mainViewPager.setOffscreenPageLimit(4);//预加载前4个页面。避免卡顿
            }
        });

        //启动JobScheduler
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(), ErayicService.class.getName()))
                .setPeriodic(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        jobScheduler.schedule(jobInfo);
//        startService(new Intent(this, ErayicService.class));//启动服务
//        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this, ErayicService.class));
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//设置需要的网络条件，默认NETWORK_TYPE_NONE
//        builder.setPersisted(true);//设备重启之后你的任务是否还要继续执行
////        builder.setMinimumLatency(3000);// 设置任务运行最少延迟时间
////        builder.setOverrideDeadline(50000);// 设置deadline，若到期还没有达到规定的条件则会开始执行
//        builder.setRequiresCharging(true);// 设置是否充电的条件,默认false
//        builder.setRequiresDeviceIdle(false);// 设置手机是否空闲的条件,默认false
//        builder.setPeriodic(1000);//设置间隔时间 该方法不能和setMinimumLatency、setOverrideDeadline这两个同时调用，否则会报错“java.lang.IllegalArgumentException: Can't call setMinimumLatency() on a periodic job”，或者报错“java.lang.IllegalArgumentException: Can't call setOverrideDeadline() on a periodic job”。

    }

    @Override
    public void loadingError() {
        ErayicStack.getInstance().finishAllActivity();//清空所有的activity
        ARouter.getInstance().build("/index/Activity/LoginActivity").navigation();
    }

    @Override
    public void updateApp() {

        UpdateAppManager manager = new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(MainActivity.this)
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //设置请求方式 默认get,
                .setPost(false)
                //设置头部
                .setTopPic(R.drawable.app_base_default_update)
                //更新地址
                .setUpdateUrl(AgrConstant.AGR_UPDATE_URL)
//                //添加自定义参数
//                .setParams(params)
                //设置主题色
                .setThemeColor(0xff39c1e9)
                .build();

        //检测是否有新版本
        manager.checkNewApp(new UpdateCallback() {
            /**
             * 解析json,自定义协议
             *
             * @param json 服务器返回的json
             * @return UpdateAppBean
             */
            @Override
            protected UpdateAppBean parseJson(String json) {
                UpdateAppBean updateAppBean = new UpdateAppBean();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String strUpdate;
                    if (ErayicApp.getVersionCode(MainActivity.this) >= jsonObject.getInt("VersionCode"))
                        strUpdate = "No";
                    else
                        strUpdate = "Yes";
                    updateAppBean
                            //是否更新Yes,No
                            .setUpdate(strUpdate)
                            //新版本号
                            .setNew_version(jsonObject.getString("new_version"))
                            //下载地址
                            .setApk_file_url(jsonObject.getString("apk_file_url"))
                            //大小
                            .setTarget_size(jsonObject.getString("target_size"))
                            //更新内容
                            .setUpdate_log(jsonObject.getString("update_log"))
                            //是否强制更新
                            .setConstraint(jsonObject.getBoolean("constraint"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return updateAppBean;
            }

            /**
             * 有新版本
             *
             * @param updateApp        新版本信息
             * @param updateAppManager app更新管理器
             */
            @Override
            public void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                updateAppManager.showDialog();
            }

            /**
             * 网络请求之前
             */
            @Override
            public void onBefore() {
                //CProgressDialogUtils.showProgressDialog(MainActivity.this);
            }

            /**
             * 网路请求之后
             */
            @Override
            public void onAfter() {
                //CProgressDialogUtils.cancelProgressDialog(MainActivity.this);
            }

            /**
             * 没有新版本
             */
            @Override
            public void noNewApp() {
//                ErayicToast.TextToast(MainActivity.this, "没有新版本");
            }
        });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
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


}
