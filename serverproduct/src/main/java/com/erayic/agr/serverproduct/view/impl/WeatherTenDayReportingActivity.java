package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.PagerSlidingTabStrip;
import com.erayic.agr.common.view.codbking.bean.DateType;
import com.erayic.agr.serverproduct.DateFormatUtils;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;
import com.erayic.agr.serverproduct.presenter.IWeatherTenDayReportingPresenter;
import com.erayic.agr.serverproduct.presenter.impl.WeatherTenDayReportingPresenterImpl;
import com.erayic.agr.serverproduct.view.ITenDayReportingView;
import com.erayic.agr.serverproduct.view.ITendayReportingUpdateView;
import com.erayic.agr.serverproduct.view.custom.ReportingSortDailogManage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/8.
 */
@Route(path = "/serverproduct/activity/WeatherTenDayReportingActivity", name = "旬报")
public class WeatherTenDayReportingActivity extends BaseActivity implements ITenDayReportingView, View.OnClickListener {
    @BindView(R2.id.serverproduct_tendayreporting_resource_tab)
    PagerSlidingTabStrip slidingTabStrip;
    @BindView(R2.id.serverproduct_tendayreporting_content_viewpager)
    ViewPager viewPager;
    @BindView(R2.id.serverproduct_tendayreporting_toolbar)
    Toolbar mToolbar;
    WeatherTenDayReporingViewPagerAdapter adapter;


    IWeatherTenDayReportingPresenter weatherTenDayReportingPresenter;
    private String[] TITLES;
    private Fragment[] fragments;
    private ITendayReportingUpdateView[] updateViews;
    private LoadingDialog dialog;
    private int year;
    private int month;
    private int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_ten_day);
    }

    @Override
    public void refreshTenDayReportingDatas(List<WeatherTendayReportingData> beans) {
                    refreshData();
                    for (int i=0;i<updateViews.length;i++)
                       updateViews[i].updateReportingDatas((beans.size()>=i+1)?((beans==null)?null:beans.get(i)):null);
    }

    @Override
    public void updateTenDayReportingDatas(Date date) {
        int mYear = DateFormatUtils.formatDate(date,"yyyy");
        int mMonth =DateFormatUtils.formatDate(date,"MM");
        if (mYear != year || mMonth != month) {
            year = mYear;
            month = mMonth;
            weatherTenDayReportingPresenter.getWeatherTenDayReportingData(year,month);
        }
    }

    int index;

    @Override
    public void showLoading() {
        index++;
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        if (!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void dismissLoading() {
        index--;
        if (index == 0) {
            dialog.dismiss();
            dialog=null;
        }
    }

    @Override
    public void showToast() {

    }

    @Override
    public void initView() {

        viewPager.setOffscreenPageLimit(2);
        mToolbar.setTitle("旬报");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (adapter==null)
            adapter = new WeatherTenDayReporingViewPagerAdapter(this.getSupportFragmentManager());
        fragments = new Fragment[]{
                (Fragment) ARouter.getInstance()
                        .build("/serverproduct/fragment/WeatherTenDayReportingFragment")
                        .navigation(),
                (Fragment) ARouter.getInstance()
                        .build("/serverproduct/fragment/WeatherTenDayReportingFragment")
                        .navigation(),
                (Fragment) ARouter.getInstance()
                        .build("/serverproduct/fragment/WeatherTenDayReportingFragment")
                       .navigation(),
        };
        updateViews=new WeatherTenDayReportingFragment[]{(WeatherTenDayReportingFragment) fragments[0]
                ,(WeatherTenDayReportingFragment) fragments[1],(WeatherTenDayReportingFragment) fragments[2]};
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_serverproduct_tendayreporting, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.serverproduct_tendayreporting_picktimer) {
            ReportingSortDailogManage.getInstance(this).
                    setting(5,"已选日期", DateType.TYPE_YM,"yyyy年 MM月 ").showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {
        Calendar ca = new GregorianCalendar(TimeZone.getTimeZone("GMT+8"));
        ca.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        year = ca.get(Calendar.YEAR);
        month = ((month = ca.get(Calendar.MONTH)) == 12) ? (year = year + 1) / year : month + 1;
        date = ca.get(Calendar.DAY_OF_MONTH);
        month = (date <= 11) ? ((month == 1) ? 12 * (year = year - 1) / year : month - 1) : month;
        TITLES = new String[]{month + "月上旬", month + "月中旬", month + "月下旬"};

        viewPager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
        slidingTabStrip.setViewPager(viewPager);
        slidingTabStrip.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        slidingTabStrip.setIndicatorColorResource(R.color.app_base_tab_strip_indicator);//滑动条的颜色
        //serviceOrderTab.setUnderlineColorResource(R.color.colorBase);//滑动条所在的那个全宽线的颜色
        slidingTabStrip.setTextColor(R.color.app_base_text_title_2);
        slidingTabStrip.setDividerColorResource(R.color.app_base_tab_strip_divider);//每个标签的分割线的颜色
        slidingTabStrip.setIndicatorHeight(10);//滑动条的高度
        slidingTabStrip.setUnderlineHeight(1);//滑动条所在的那个全宽线的高度
        weatherTenDayReportingPresenter = new WeatherTenDayReportingPresenterImpl(this);
        weatherTenDayReportingPresenter.getWeatherTenDayReportingData(year, month);
    }

    public void refreshData(){
        TITLES = new String[]{month + "月上旬", month + "月中旬", month + "月下旬"};
        adapter.notifyDataSetChanged();
        slidingTabStrip.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        ReportingSortDailogManage.destoryManage();
        super.onDestroy();
    }

    public class WeatherTenDayReporingViewPagerAdapter extends FragmentPagerAdapter {
        public WeatherTenDayReporingViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return (fragments == null) ? 0 : fragments.length;
        }
    }

}
