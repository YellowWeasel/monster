package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.MarketInfoParamter;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/15.
 */

public class DynamicPriceDetailActivity extends BaseActivity {
    @Autowired
    MarketInfoParamter paramter;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    Fragment[] fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        toolbar.setTitle("市场价格");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initData() {

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class DynamicPriceDetailViewPagerAdapter extends FragmentPagerAdapter {
        public DynamicPriceDetailViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments[position/fragments.length];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            return super.instantiateItem(container, position);
        }

        @Override
        public int getCount() {
            return (fragments == null) ? 0 : fragments.length;
        }
    }
}
