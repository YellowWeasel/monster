package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceInfoByEntAdapter;
import com.erayic.agr.service.adapter.entity.ServiceInfoByEntEntity;
import com.erayic.agr.service.presenter.IServiceInfoByEntPresenter;
import com.erayic.agr.service.presenter.impl.ServiceInfoByEntPresenterImpl;
import com.erayic.agr.service.view.IServiceInfoByEntView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/activity/ServiceInfoByEntActivity", name = "服务详情")
public class ServiceInfoByEntActivity extends BaseActivity implements IServiceInfoByEntView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @Autowired
    String serviceName;
    @Autowired
    String serviceID;
    @Autowired
    String serviceIcon;
    @Autowired
    int serviceType;
    @BindView(R2.id.service_info_recycler)
    RecyclerView serviceInfoRecycler;
    @BindView(R2.id.service_info_swipe)
    SwipeRefreshLayout serviceInfoSwipe;


    private IServiceInfoByEntPresenter presenter;
    private ServiceInfoByEntAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info_ent);
    }

    @Override
    public void initView() {
        toolbar.setTitle("服务详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        serviceInfoSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        serviceInfoRecycler.setLayoutManager(manager);
        adapter = new ServiceInfoByEntAdapter(ServiceInfoByEntActivity.this, null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnBuyClickListener(new ServiceInfoByEntAdapter.OnBuyClickListener() {
            @Override
            public void onBuyClick(View view, CommonPriceBean bean) {
                ARouter.getInstance().build("/service/activity/ServiceBuyActivity").withString("serviceName", serviceName)
                        .withString("serviceID", serviceID).withString("serviceIcon", serviceIcon)
                        .withInt("serviceType", serviceType).withSerializable("priceBean", bean).navigation();
            }
        });
        adapter.isFirstOnly(false);
        serviceInfoRecycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter = new ServiceInfoByEntPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getSpecifyServiceByEnt(serviceID);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceInfoSwipe != null && !serviceInfoSwipe.isRefreshing())
                    serviceInfoSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceInfoSwipe != null && serviceInfoSwipe.isRefreshing())
                    serviceInfoSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshServiceView(final List<ServiceInfoByEntEntity> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
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
