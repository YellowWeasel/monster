package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.AgriculturalDetailInfosAdapter;
import com.erayic.agr.serverproduct.adapter.AgriculturalInfosAdapter;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalDetailInfoDatas;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;
import com.erayic.agr.serverproduct.presenter.IAgriculturalDetailInfoPresenter;
import com.erayic.agr.serverproduct.presenter.impl.AgriculturalDetailInfoPresenterImpl;
import com.erayic.agr.serverproduct.view.IAgriculturalDetailInfoView;
import com.erayic.agr.serverproduct.view.custom.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/19.
 */
@Route(path = "/serverproduct/activity/AgriculturalInfoDetailActivity", name = "农业资讯详情")
public class AgriculturalInfoDetailActivity extends BaseActivity implements IAgriculturalDetailInfoView, SwipeRefreshLayout.OnRefreshListener {
    IAgriculturalDetailInfoPresenter presenter;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.serverproduct_agricultural_detail_info_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.serverproduct_agricultural_detail_info_recycler)
    RecyclerView agriculturalDetailRecyclerview;
    AgriculturalDetailInfosAdapter adapter;
    @Autowired
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agricultural_detail_info);
    }
    @Override
    public void initView() {
        toolbar.setTitle("农业资讯详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        swipeRefreshLayout.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        agriculturalDetailRecyclerview.setLayoutManager(manager);
        agriculturalDetailRecyclerview.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new AgriculturalDetailInfosAdapter(null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        agriculturalDetailRecyclerview.setAdapter(adapter);
    }
    @Override
    public void initData() {
        presenter=new AgriculturalDetailInfoPresenterImpl(this);
        presenter.getAgriculturalDetailInfo(Id);
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(AgriculturalInfoDetailActivity.this,msg);
            }
        });
    }

    @Override
    public void refreshAgriculturatlDetailInfoView(List<AgriculturalDetailInfoDatas> beans) {
            adapter.setNewData(beans);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout!=null&&!swipeRefreshLayout.isRefreshing()){
                      swipeRefreshLayout.setRefreshing(true);
                }
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout!=null&&swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        presenter.getAgriculturalDetailInfo(Id);
    }
}
