package com.erayic.agr.manage.view.impl;

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
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManagePersonnelItemAdapter;
import com.erayic.agr.manage.presenter.IPersonnelListPresenter;
import com.erayic.agr.manage.presenter.impl.PersonnelListPresenterImpl;
import com.erayic.agr.manage.view.IPersonnelListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/PersonnelListActivity", name = "人员管理")
public class PersonnelListActivity extends BaseActivity implements IPersonnelListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_personnel_RecyclerView)
    RecyclerView managePersonnelRecyclerView;
    @BindView(R2.id.manage_personnel_swipe)
    SwipeRefreshLayout managePersonnelSwipe;

    private IPersonnelListPresenter presenter;
    private ManagePersonnelItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_personnel);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("人员信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        managePersonnelSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(PersonnelListActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        managePersonnelRecyclerView.setLayoutManager(manager);
        adapter = new ManagePersonnelItemAdapter(PersonnelListActivity.this, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonPersonnelBean bean = (CommonPersonnelBean) adapter.getData().get(position);
                ARouter.getInstance()
                        .build("/manage/activity/PersonnelInfoActivity")
                        .withBoolean("isAdd", false)
                        .withParcelable("bean", bean)
                        .navigation();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                return true;
            }
        });
        managePersonnelRecyclerView.setAdapter(adapter);
        managePersonnelRecyclerView.addItemDecoration(new DividerItemDecoration(PersonnelListActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new PersonnelListPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.GetAllUserByBase();
    }

    @Subscribe
    public void onMessageEvent(ManageRefreshMessage event) {
        if (event.getMsgType() == ManageRefreshMessage.MANAGE_MASTER_PERSONNEL_LIST) {
            onRefresh();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_personnel_add) {
            ARouter.getInstance().build("/manage/activity/PersonnelInfoActivity").withBoolean("isAdd", true).navigation();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_personnel_add, menu);
        return true;
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (managePersonnelSwipe != null && !managePersonnelSwipe.isRefreshing())
                    managePersonnelSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (managePersonnelSwipe != null && managePersonnelSwipe.isRefreshing())
                    managePersonnelSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshPersonnelView(final List<CommonPersonnelBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
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
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
