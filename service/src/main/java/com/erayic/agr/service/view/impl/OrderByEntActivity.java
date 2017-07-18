package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.PagerSlidingTabStrip;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.view.IOrderByEntView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/activity/OrderByEntActivity", name = "购买记录")
public class OrderByEntActivity extends BaseActivity implements IOrderByEntView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.service_order_tab)
    PagerSlidingTabStrip serviceOrderTab;
    @BindView(R2.id.service_order_viewPage)
    ViewPager serviceOrderViewPage;

    private ServicePagerAdapter adapter;

    private final Fragment[] fragments = new Fragment[]{(Fragment) ARouter.getInstance().build("/service/fragment/OrderByPayingFragment").withString("titleName", "待支付").navigation(),
            (Fragment) ARouter.getInstance().build("/service/fragment/OrderByHistoryFragment").withString("titleName", "订购记录").navigation()};
    private final String[] TITLES = {"待支付", "订购记录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);
    }

    @Override
    public void initView() {
        toolbar.setTitle("购买记录");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapter = new ServicePagerAdapter(this.getSupportFragmentManager());
        serviceOrderViewPage.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                .getDisplayMetrics());
        serviceOrderViewPage.setPageMargin(pageMargin);
        serviceOrderTab.setViewPager(serviceOrderViewPage);
        serviceOrderTab.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        serviceOrderTab.setIndicatorColorResource(R.color.app_base_tab_strip_indicator);//滑动条的颜色
        //serviceOrderTab.setUnderlineColorResource(R.color.colorBase);//滑动条所在的那个全宽线的颜色
        serviceOrderTab.setTextColor(R.color.app_base_text_title_2);
        serviceOrderTab.setDividerColorResource(R.color.app_base_tab_strip_divider);//每个标签的分割线的颜色
        serviceOrderTab.setIndicatorHeight(10);//滑动条的高度
        serviceOrderTab.setUnderlineHeight(1);//滑动条所在的那个全宽线的高度
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

        switch (item.getItemId()) {
            case android.R.id.home://返回
            {
                finish();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ServicePagerAdapter extends FragmentPagerAdapter {

        public ServicePagerAdapter(FragmentManager fm) {
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
