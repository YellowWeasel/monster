package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
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
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicIdentifier;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageBaseListAdapter;
import com.erayic.agr.manage.presenter.IBaseListPresenter;
import com.erayic.agr.manage.presenter.impl.BaseListPresenterImpl;
import com.erayic.agr.manage.view.IBaseListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/BaseListActivity", name = "基地列表")
public class BaseListActivity extends BaseActivity implements IBaseListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_base_RecyclerView)
    RecyclerView manageBaseRecyclerView;
    @BindView(R2.id.manage_base_swipe)
    SwipeRefreshLayout manageBaseSwipe;

    private LoadingDialog dialog;
    private ManageBaseListAdapter adapter;

    private IBaseListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_base_list);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("基地信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageBaseSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(BaseListActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageBaseRecyclerView.setLayoutManager(manager);
        adapter = new ManageBaseListAdapter(BaseListActivity.this, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonBaseListBean baseListBean = (CommonBaseListBean) adapter.getData().get(position);
                ARouter.getInstance().build("/manage/activity/BaseInfoActivity").withString("baseID", baseListBean.getBaseID()).withString("baseName", baseListBean.getBaseName()).navigation();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                return true;
            }
        });
        manageBaseRecyclerView.setAdapter(adapter);
        manageBaseRecyclerView.addItemDecoration(new DividerItemDecoration(BaseListActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new BaseListPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getBaseByEnt();
    }

    @Subscribe
    public void onMessageEvent(ManageRefreshMessage event) {
        if (event.getMsgType() == ManageRefreshMessage.MANAGE_MASTER_BASE_LIST) {
            onRefresh();
        }
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageBaseSwipe != null && !manageBaseSwipe.isRefreshing())
                    manageBaseSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageBaseSwipe != null && manageBaseSwipe.isRefreshing())
                    manageBaseSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshBaseListView(final List<CommonBaseListBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(BaseListActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(BaseListActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void addBaseSure() {
        //全局更新，以后改局部更新
        onRefresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_base_add) {
            new ErayicEditDialog.Builder(BaseListActivity.this)
                    .setMessage("", null)
                    .setTitle("设置基地名称")
                    .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which, CharSequence s) {
                            dialog.dismiss();
                        }
                    })
                    .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which, CharSequence s) {
                            presenter.addBaseByEnt(s.toString(), ErayicIdentifier.getInstance(BaseListActivity.this).getErayicdentifier());
                            dialog.dismiss();
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_base_add, menu);
        return true;
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
