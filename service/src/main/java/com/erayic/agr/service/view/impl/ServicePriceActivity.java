package com.erayic.agr.service.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServicePriceByEntAdapter;
import com.erayic.agr.service.presenter.IServicePriceByEntPresenter;
import com.erayic.agr.service.presenter.impl.ServicePriceByEntPresenterImpl;
import com.erayic.agr.service.view.IServicePriceView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/ServicePriceActivity", name = "获取服务的所有价格信息")
public class ServicePriceActivity extends BaseActivity implements IServicePriceView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.service_price_recycler)
    RecyclerView servicePriceRecycler;
    @BindView(R2.id.service_price_swipe)
    SwipeRefreshLayout servicePriceSwipe;

    @Autowired
    String serviceID;
    @Autowired
    CommonPriceBean price;

    private int checkPosition;

    private IServicePriceByEntPresenter presenter;
    private ServicePriceByEntAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_price_ent);
    }

    @Override
    public void initView() {
        toolbar.setTitle("选择价格");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        servicePriceSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        servicePriceRecycler.setLayoutManager(manager);
        adapter = new ServicePriceByEntAdapter(ServicePriceActivity.this, null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (((CommonPriceBean) adapter.getData().get(position)).isCheck()) {
                    showToast("您已选择该价格");
                } else {
                    ((CommonPriceBean) adapter.getData().get(position)).setCheck(true);
                    price = ((CommonPriceBean) adapter.getData().get(position));
                    adapter.notifyItemChanged(position);
                    ((CommonPriceBean) adapter.getData().get(checkPosition)).setCheck(false);
                    adapter.notifyItemChanged(checkPosition);
                    checkPosition = position;
                }
            }
        });
        servicePriceRecycler.setAdapter(adapter);

    }

    @Override
    public void initData() {
//        price = (CommonPriceBean) getIntent().getExtras().getSerializable("price");
        presenter = new ServicePriceByEntPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getAllPriceByService(serviceID);
    }


    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (servicePriceSwipe != null && !servicePriceSwipe.isRefreshing())
                    servicePriceSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (servicePriceSwipe != null && servicePriceSwipe.isRefreshing())
                    servicePriceSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshServiceView(List<CommonPriceBean> list) {
        int position = 0;
        for (CommonPriceBean bean : list) {
            if (bean.getPriceID() == price.getPriceID()) {
                checkPosition = position;
                bean.setCheck(true);
            }
            position++;
        }
        final List<CommonPriceBean> newList = list;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(newList);
            }
        });
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
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_service_price_sure) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("price", price);
            intent.putExtras(bundle);
            setResult(AgrConstant.ACTIVITY_RESULT_Service_PriceByEntActivity, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_price_sure, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
