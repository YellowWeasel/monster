package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.PagerSlidingTabStrip;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;
import com.erayic.agr.serverproduct.presenter.IWeatherTenDayReportingPresenter;
import com.erayic.agr.serverproduct.presenter.impl.WeatherTenDayReportingPresenter;
import com.erayic.agr.serverproduct.view.ITenDayReportingView;

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
public class WeatherTenDayReportingActivity extends BaseActivity implements ITenDayReportingView {
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
        adapter = new WeatherTenDayReporingViewPagerAdapter(this.getSupportFragmentManager());
        fragments = new Fragment[]{
                (Fragment) ARouter.getInstance()
                        .build("/serverproduct/fragment/WeatherTenDayReportingFragment")
                        .withParcelable("reportingData",(beans.size()>=1)?beans.get(0):null)
                        .navigation(),
                (Fragment) ARouter.getInstance()
                        .build("/serverproduct/fragment/WeatherTenDayReportingFragment")
                        .withParcelable("reportingData", (beans.size()>=2)?beans.get(1):null).navigation(),
                (Fragment) ARouter.getInstance()
                        .build("/serverproduct/fragment/WeatherTenDayReportingFragment")
                        .withParcelable("reportingData", (beans.size()>=3)?beans.get(2):null).navigation(),
        };
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
    }

    int index;

    @Override
    public void showLoading() {
        index++;
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        if (!dialog.isShowing()) dialog.show();
    }

    @Override
    public void dismissLoading() {
        index--;
        if (index == 0) dialog.dismiss();
    }

    @Override
    public void showToast() {

    }

    @Override
    public void initView() {
        mToolbar.setTitle("旬报");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initData() {
        Calendar ca=new GregorianCalendar(TimeZone.getTimeZone("GMT+8"));
        ca.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        year=ca.get(Calendar.YEAR);
        month=((month=ca.get(Calendar.MONTH))==12)?1:month+1;
        date=ca.get(Calendar.DAY_OF_MONTH);
        month=(date<=11)?((month==1)?12*(year=year-1)/year:month-1):month;
        TITLES = new String[]{month + "月上旬", month + "月上旬", month+ "月下旬"};
        weatherTenDayReportingPresenter = new WeatherTenDayReportingPresenter(this);
        weatherTenDayReportingPresenter.getWeatherTenDayReportingData(year,month);
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
