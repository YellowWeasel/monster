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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonImageBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceListByEntAdapter;
import com.erayic.agr.service.adapter.entity.ServiceListByEntEntity;
import com.erayic.agr.service.presenter.IServiceListByEntPresenter;
import com.erayic.agr.service.presenter.impl.ServiceListByEntPresenterImpl;
import com.erayic.agr.service.view.IServiceListByEntView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/ServiceListByEntActivity", name = "服务市场")
public class ServiceListByEntActivity extends BaseActivity implements IServiceListByEntView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.service_market_recycler)
    RecyclerView serviceMarketRecycler;
    @BindView(R2.id.service_market_swipe)
    SwipeRefreshLayout serviceMarketSwipe;

    private IServiceListByEntPresenter presenter;
    private ServiceListByEntAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list_ent);
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
        adapter = new ServiceListByEntAdapter(ServiceListByEntActivity.this, null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        adapter.setOnItemServiceMarketClickListener(new ServiceListByEntAdapter.OnItemServiceMarketClickListener() {
            @Override
            public void onItemClick(View view, ServiceListByEntEntity entity) {
                ARouter.getInstance().build("/service/activity/ServiceInfoByEntActivity").withString("serviceName", entity.getServicesInfo().getServiceName())
                        .withString("serviceID", entity.getServicesInfo().getServiceID()).withString("serviceIcon", entity.getServicesInfo().getIcon())
                        .withInt("serviceType", entity.getServicesInfo().getType()).navigation();
            }
        });
        adapter.setOnBannerServiceMarketClickListener(new ServiceListByEntAdapter.OnBannerServiceMarketClickListener() {
            @Override
            public void onItemClick(View view, int position, CommonImageBean bean) {
                showToast(position + "");
            }
        });
        serviceMarketRecycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter = new ServiceListByEntPresenterImpl(this);
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
    public void refreshServiceView(final List<ServiceListByEntEntity> list) {

        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_list_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_service_admin) {//企业账户
            ARouter.getInstance().build("/service/activity/BusinessAccountActivity").navigation();
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
