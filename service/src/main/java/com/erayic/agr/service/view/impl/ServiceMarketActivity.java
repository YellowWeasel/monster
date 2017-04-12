package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceMarketAdapter;
import com.erayic.agr.service.presenter.IServiceMarketPresenter;
import com.erayic.agr.service.presenter.impl.ServiceMarketPresenterImpl;
import com.erayic.agr.service.view.IServiceMarketView;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/ServiceMarketActivity", name = "服务市场")
public class ServiceMarketActivity extends BaseActivity implements IServiceMarketView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.service_market_recycler)
    RecyclerView serviceMarketRecycler;
    @BindView(R2.id.service_market_swipe)
    SwipeRefreshLayout serviceMarketSwipe;

    private IServiceMarketPresenter presenter;
    private ServiceMarketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_market);
    }

    @Override
    public void initView() {
        toolbar.setTitle("信息服务");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        serviceMarketSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        serviceMarketRecycler.setLayoutManager(manager);
        adapter = new ServiceMarketAdapter(this);
        adapter.setOnItemClickListener(new ServiceMarketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ServiceSystemBean.ServicesInfo servicesInfo) {
                ARouter.getInstance().build("/service/activity/ServiceInfoActivity").withString("serviceName", servicesInfo.getServiceName())
                        .withString("serviceID", servicesInfo.getServiceID()).withString("serviceUrl", "").navigation();
            }
        });
        serviceMarketRecycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter = new ServiceMarketPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getAllSystemServiceByEnt();
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceMarketSwipe != null && !serviceMarketSwipe.isRefreshing())
                    serviceMarketSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceMarketSwipe != null && serviceMarketSwipe.isRefreshing())
                    serviceMarketSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshServiceView(final ServiceSystemBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setList(bean);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_market_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_service_admin) {
            showToast("企业账户");
        }
        return super.onOptionsItemSelected(item);
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

}
