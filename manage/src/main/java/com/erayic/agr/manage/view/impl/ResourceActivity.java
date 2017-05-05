package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.PagerSlidingTabStrip;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.view.IResourceView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/ResourceActivity", name = "农药化肥种子承载主页面")
public class ResourceActivity extends BaseActivity implements IResourceView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_resource_tab)
    PagerSlidingTabStrip manageResourceTab;
    @BindView(R2.id.manage_resource_viewPage)
    ViewPager manageResourceViewPage;

    private ResourcePagerAdapter adapter;

    private final String[] TITLES = {"农药", "化肥", "种子"};
    private final Fragment[] fragments = new Fragment[]{
            (Fragment) ARouter.getInstance().build("/manage/fragment/ResourceListFragment").withInt("type", EnumResourceType.TYPE_PESTICIDE).navigation(),
            (Fragment) ARouter.getInstance().build("/manage/fragment/ResourceListFragment").withInt("type", EnumResourceType.TYPE_FERTILIZER).navigation(),
            (Fragment) ARouter.getInstance().build("/manage/fragment/ResourceListFragment").withInt("type", EnumResourceType.TYPE_SEED).navigation()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_resource);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("生产资料管理");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapter = new ResourcePagerAdapter(this.getSupportFragmentManager());
        manageResourceViewPage.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                .getDisplayMetrics());
        manageResourceViewPage.setPageMargin(pageMargin);
        manageResourceTab.setViewPager(manageResourceViewPage);
        manageResourceTab.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        manageResourceTab.setIndicatorColorResource(R.color.app_base_tab_strip_indicator);//滑动条的颜色
        //serviceOrderTab.setUnderlineColorResource(R.color.colorBase);//滑动条所在的那个全宽线的颜色
        manageResourceTab.setTextColor(R.color.app_base_text_title_2);
        manageResourceTab.setDividerColorResource(R.color.app_base_tab_strip_divider);//每个标签的分割线的颜色
        manageResourceTab.setIndicatorHeight(10);//滑动条的高度
        manageResourceTab.setUnderlineHeight(1);//滑动条所在的那个全宽线的高度
//        service_ent_tab.setShouldExpand(true);//如果设置为true，每个标签是相同的控件，均匀平分整个屏幕，默认是false
    }

    @Override
    public void initData() {

    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)//返回
        {
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_manage_resource_add_pesticide) {
            ARouter.getInstance().build("/manage/activity/PesticideInfoActivity")
                    .withBoolean("isAdd", true)
                    .navigation();
        } else if (item.getItemId() == R.id.action_manage_resource_add_fertilizer) {
            ARouter.getInstance().build("/manage/activity/FertilizerInfoActivity")
                    .withBoolean("isAdd", true)
                    .navigation();

        } else if (item.getItemId() == R.id.action_manage_resource_add_seed) {
            ARouter.getInstance().build("/manage/activity/SeedInfoActivity")
                    .withBoolean("isAdd", true)
                    .navigation();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_resource_add, menu);
        return true;
    }

    /**
     * PagerAdapter
     */
    public class ResourcePagerAdapter extends FragmentPagerAdapter {

        public ResourcePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

    }
}
