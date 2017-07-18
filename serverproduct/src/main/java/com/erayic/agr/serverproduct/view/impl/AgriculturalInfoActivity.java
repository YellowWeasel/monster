package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.AgriculturalInfosAdapter;
import com.erayic.agr.serverproduct.adapter.PoliciesRegulationsAdapter;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;
import com.erayic.agr.serverproduct.presenter.IAgriculturalInfoPresenter;
import com.erayic.agr.serverproduct.presenter.impl.AgriculturalInfoPresenterImpl;
import com.erayic.agr.serverproduct.view.IAgriculturalInfoView;
import com.erayic.agr.serverproduct.view.custom.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/19.
 */
@Route(path = "/serverproduct/activity/AgriculturalInfoActivity", name = "农业资讯列表")
public class AgriculturalInfoActivity extends BaseActivity implements IAgriculturalInfoView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.serverproduct_agricultural_info_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.serverproduct_agricultural_info_recycler)
    RecyclerView agriculturalRecyclerview;
    AgriculturalInfosAdapter adapter;
    IAgriculturalInfoPresenter presenter;
    int pageSize = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agricultural_info);
    }

    @Override
    public void initView() {
        toolbar.setTitle("农业资讯");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        swipeRefreshLayout.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        agriculturalRecyclerview.setLayoutManager(manager);
        agriculturalRecyclerview.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new AgriculturalInfosAdapter(null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setItemOnclickListener(new AgriculturalInfosAdapter.AgriculturalItemOnclickListener() {
            @Override
            public void doItemOnclick(AgriculturalInfoDatas item, View view) {
                ARouter.getInstance().build("/serverproduct/activity/AgriculturalInfoDetailActivity").withInt("Id", item.getId()).navigation();
            }
        });
        adapter.isFirstOnly(false);
        agriculturalRecyclerview.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, agriculturalRecyclerview);
    }
    @Override
    public void initData() {
        presenter = new AgriculturalInfoPresenterImpl(this);
        presenter.initAgriculturalInfo(pageSize);
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(AgriculturalInfoActivity.this, msg);
            }
        });
    }

    @Override
    public void refreshAgriculturatlInfoView(List<AgriculturalInfoDatas> beans) {
        adapter.setNewData(beans);
        adapter.notifyDataSetChanged();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            ErayicStack.getInstance().finishCurrentActivity();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadMoreSure(final List<AgriculturalInfoDatas> beans) {
            MainLooperManage.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.loadMoreComplete();
                    if (beans != null)
                    adapter.addData(beans);
                    if (beans.size() < pageSize)
                        adapter.loadMoreEnd();
                }
            });
    }

    @Override
    public void loadMoreFailure() {
        adapter.loadMoreFail();
    }

    @Override
    public void onRefresh() {
        presenter.initAgriculturalInfo(pageSize);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.getAgriculturalInfo(adapter.getData().size() / pageSize + 1, pageSize);
        adapter.setEnableLoadMore(true);
    }
}
