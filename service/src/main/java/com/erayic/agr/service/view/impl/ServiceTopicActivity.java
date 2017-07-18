package com.erayic.agr.service.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceTopicByEntAdapter;
import com.erayic.agr.service.presenter.IServiceTopicByEntPresenter;
import com.erayic.agr.service.presenter.impl.ServiceTopicByEntPresenterImpl;
import com.erayic.agr.service.view.IServiceTopicView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/ServiceTopicActivity", name = "获取服务的所有子服务")
public class ServiceTopicActivity extends BaseActivity implements IServiceTopicView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.service_topic_recycler)
    RecyclerView serviceTopicRecycler;
    @BindView(R2.id.service_topic_swipe)
    SwipeRefreshLayout serviceTopicSwipe;

    @Autowired
    String serviceID;

    List<CommonSubServiceBean> serviceList;

    private IServiceTopicByEntPresenter presenter;

    private ServiceTopicByEntAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_topic_ent);
    }

    @Override
    public void initView() {
        serviceList = getIntent().getParcelableArrayListExtra("serviceList");
        toolbar.setTitle("选择作物品种");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        serviceTopicSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        serviceTopicRecycler.setLayoutManager(manager);
        adapter = new ServiceTopicByEntAdapter(ServiceTopicActivity.this,null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((CommonSubServiceBean) adapter.getData().get(position)).setCheck(!((CommonSubServiceBean) adapter.getData().get(position)).isCheck());
                adapter.notifyItemChanged(position);
            }
        });
        serviceTopicRecycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter = new ServiceTopicByEntPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getRderDetailBySubInfo(serviceID);
    }


    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceTopicSwipe != null && !serviceTopicSwipe.isRefreshing())
                    serviceTopicSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceTopicSwipe != null && serviceTopicSwipe.isRefreshing())
                    serviceTopicSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshServiceView(List<CommonSubServiceBean> list) {
        if (serviceList != null && serviceList.size() > 0) {
            for (CommonSubServiceBean bean : list) {
                for (CommonSubServiceBean serviceBean : serviceList) {
                    if (bean.getServiceID().equals(serviceBean.getServiceID()))
                        bean.setCheck(true);
                }
            }
        }
        final List<CommonSubServiceBean> listBuffer = list;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(listBuffer);
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
        } else if (item.getItemId() == R.id.action_service_topic_sure) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            List<CommonSubServiceBean> serviceBuffer = new ArrayList<>();

            for (CommonSubServiceBean bean :adapter.getData()){
                if (bean.isCheck()){
                    serviceBuffer.add(bean);
                }
            }
            bundle.putParcelableArrayList("serviceList", (ArrayList<? extends Parcelable>) serviceBuffer);
            intent.putExtras(bundle);
            setResult(AgrConstant.ACTIVITY_RESULT_Service_TopicByEntActivity, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_topic_sure, menu);
        return true;
    }

}
