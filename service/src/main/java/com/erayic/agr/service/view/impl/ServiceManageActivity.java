package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceManageAdapter;
import com.erayic.agr.service.event.ServiceEntranceEvent;
import com.erayic.agr.service.presenter.IServiceManagePresenter;
import com.erayic.agr.service.presenter.impl.ServiceManagePresenterImpl;
import com.erayic.agr.service.view.IServiceManageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/activity/ServiceManageActivity", name = "服务管理")
public class ServiceManageActivity extends BaseActivity implements IServiceManageView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.service_manage_recycler)
    RecyclerView serviceManageRecycler;
    @BindView(R2.id.service_manage_swipe)
    SwipeRefreshLayout serviceManageSwipe;

    private LoadingDialog dialog;

    private IServiceManagePresenter presenter;
    private ServiceManageAdapter adapter;

    private int switchGroupPosition = -1;
    private int switchChildPosition = -1;
    private boolean isSwitchSub;//是否为主题子服务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_manage);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("服务管理");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        serviceManageSwipe.setOnRefreshListener(this);

        adapter = new ServiceManageAdapter(ServiceManageActivity.this);
        adapter.setOnSwitchStatueListener(new ServiceManageAdapter.OnSwitchStatueListener() {
            @Override
            public void switchChecked(boolean isSub, String serviceID, String subServiceID, int section, int position, boolean isChecked) {

                if (isChecked) {
                    switchGroupPosition = section;
                    switchChildPosition = position;
                    isSwitchSub = isSub;
                    if (isSub)
                        presenter.orderServiceByBuyOfEntUser(subServiceID);//关注
                    else
                        presenter.orderServiceByBuyOfEntUser(serviceID);//关注
                } else {
                    switchGroupPosition = section;
                    switchChildPosition = position;
                    isSwitchSub = isSub;
                    if (isSub)
                        presenter.cancelUserService(subServiceID);//取消关注
                    else
                        presenter.cancelUserService(serviceID);//取消关注
                }
            }
        });
        GridLayoutManager manager = new GridLayoutManager(ServiceManageActivity.this, 1);
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
        serviceManageRecycler.setLayoutManager(manager);
        serviceManageRecycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter = new ServiceManagePresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getAllSystemServiceByUser();
    }


    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceManageSwipe != null && !serviceManageSwipe.isRefreshing())
                    serviceManageSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serviceManageSwipe != null && serviceManageSwipe.isRefreshing())
                    serviceManageSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(ServiceManageActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        new Handler(getMainLooper()).postDelayed(new TimerTask() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(ServiceManageActivity.this);
                dialog.dismiss();
            }
        }, 1000);
    }

    @Override
    public void updateSwitchFailure() {
        new Handler(getMainLooper()).postDelayed(new TimerTask() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void updateSwitchStatue() {
        new Handler(getMainLooper()).postDelayed(new TimerTask() {
            @Override
            public void run() {
                if (isSwitchSub) {
                    adapter.getList().get(switchGroupPosition).getSpecifys().get(switchChildPosition).setOwner(
                            !adapter.getList().get(switchGroupPosition).getSpecifys().get(switchChildPosition).isOwner());
                    EventBus.getDefault().post(new ServiceEntranceEvent(adapter.getList().get(switchGroupPosition).getSpecifys().get(switchChildPosition).getServiceID(),
                            adapter.getList().get(switchGroupPosition).getSpecifys().get(switchChildPosition).isOwner()));
                } else {
                    adapter.getList().get(switchGroupPosition).setOwner(!adapter.getList().get(switchGroupPosition).isOwner());
                    EventBus.getDefault().post(new ServiceEntranceEvent(adapter.getList().get(switchGroupPosition).getServiceID(),
                            adapter.getList().get(switchGroupPosition).isOwner()));
                }
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void refreshView(final List<ServiceBuyByUserBean> list) {
        if (list == null)
            return;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home://返回
            {
                finish();
            }
            break;
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
