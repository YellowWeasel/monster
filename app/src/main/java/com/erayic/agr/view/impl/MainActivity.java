package com.erayic.agr.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.R;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.presenter.IMainPresenter;
import com.erayic.agr.presenter.impl.MainPresenterImpl;
import com.erayic.agr.view.IMainView;
import com.jaeger.library.StatusBarUtil;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/main/Activity/MainActivity", name = "APP主承载页面")
public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;
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
                switch (PreferenceUtils.getParam("UserRole", 0)) {
                    case 1://管理员
                    {
                        titlesName = new String[]{"首页", "管理", "作业", "服务", "我的"};
                        titleNormalIcons = new int[]{R.drawable.app_base_default_index_press, R.drawable.app_base_default_unit_press, R.drawable.app_base_default_job_press,
                                R.drawable.app_base_default_service_press, R.drawable.app_base_default_mine_press};
                        titleSelectedIcons = new int[]{R.drawable.app_base_default_index_nomal, R.drawable.app_base_default_unit_nomal, R.drawable.app_base_default_job_nomal,
                                R.drawable.app_base_default_service_nomal, R.drawable.app_base_default_mine_nomal};
                        fragments = new Fragment[]{(Fragment) ARouter.getInstance().build("/main/fragment/MineFragment").withString("titleName", "首页").navigation(),
                                (Fragment) ARouter.getInstance().build("/unit/fragment/UnitListFragment").withString("titleName", "生产管理").navigation(),
                                (Fragment) ARouter.getInstance().build("/main/fragment/MineFragment").withString("titleName", "首页").navigation(),
                                (Fragment) ARouter.getInstance().build("/service/fragment/ServiceEntranceFragment").withString("titleName", "我的服务").navigation(),
                                (Fragment) ARouter.getInstance().build("/manage/fragment/ManageMineFragment").withString("titleName", "我的").navigation()};
                    }
                    break;
                    case 9://用户
                    {
                        titlesName = new String[]{"首页", "管理", "作业", "服务", "我的"};
                        titleNormalIcons = new int[]{R.drawable.app_base_default_index_press, R.drawable.app_base_default_unit_press, R.drawable.app_base_default_job_press,
                                R.drawable.app_base_default_service_press, R.drawable.app_base_default_mine_press};
                        titleSelectedIcons = new int[]{R.drawable.app_base_default_index_nomal, R.drawable.app_base_default_unit_nomal, R.drawable.app_base_default_job_nomal,
                                R.drawable.app_base_default_service_nomal, R.drawable.app_base_default_mine_nomal};
                        fragments = new Fragment[]{(Fragment) ARouter.getInstance().build("/main/fragment/MineFragment").withString("titleName", "首页").navigation(),
                                (Fragment) ARouter.getInstance().build("/unit/fragment/UnitListFragment").withString("titleName", "生产管理").navigation(),
                                (Fragment) ARouter.getInstance().build("/main/fragment/MineFragment").withString("titleName", "首页").navigation(),
                                (Fragment) ARouter.getInstance().build("/service/fragment/ServiceEntranceFragment").withString("titleName", "我的服务").navigation(),
                                (Fragment) ARouter.getInstance().build("/manage/fragment/ManageMineFragment").withString("titleName", "我的").navigation()};
                    }
                    break;
                    default:
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
            }
        });
    }

    @Override
    public void loadingError() {
        ErayicStack.getInstance().finishAllActivity();//退出所有页面（获取用户信息失败）
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
