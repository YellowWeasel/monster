package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageBaseListAdapter;
import com.erayic.agr.manage.adapter.ManageProduceListAdapter;
import com.erayic.agr.manage.presenter.IProduceListPresenter;
import com.erayic.agr.manage.presenter.impl.ProduceListPresenterImpl;
import com.erayic.agr.manage.view.IProduceListView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/ProduceListActivity", name = "产品列表")
public class ProduceListActivity extends BaseActivity implements IProduceListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_produce_RecyclerView)
    RecyclerView manageProduceRecyclerView;
    @BindView(R2.id.manage_produce_swipe)
    SwipeRefreshLayout manageProduceSwipe;

    private LoadingDialog dialog;
    private IProduceListPresenter presenter;
    private ManageProduceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_produce_list);
    }

    @Override
    public void initView() {
        toolbar.setTitle("产品信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageProduceSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(ProduceListActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageProduceRecyclerView.setLayoutManager(manager);
        adapter = new ManageProduceListAdapter(ProduceListActivity.this, null);

        manageProduceRecyclerView.setAdapter(adapter);
        manageProduceRecyclerView.addItemDecoration(new DividerItemDecoration(ProduceListActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new ProduceListPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getAllProduct();
    }


    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageProduceSwipe != null && !manageProduceSwipe.isRefreshing())
                    manageProduceSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageProduceSwipe != null && manageProduceSwipe.isRefreshing())
                    manageProduceSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshPersonnelView(final List<CommonProduceListBean> list) {
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
                    dialog = new LoadingDialog(ProduceListActivity.this);
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
                    dialog = new LoadingDialog(ProduceListActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void addSure() {
        ManageRefreshMessage message = new ManageRefreshMessage();
        message.setMsgType(ManageRefreshMessage.MANAGE_MASTER_PRODUCE_LIST);
        EventBus.getDefault().post(message);
        onRefresh();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_produce_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_produce_add) {
            new ErayicEditDialog.Builder(ProduceListActivity.this)
                    .setMessage("", null)
                    .setTitle("设置产品名称")
                    .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which, CharSequence s) {
                            dialog.dismiss();
                        }
                    })
                    .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which, CharSequence s) {
                            if (!TextUtils.isEmpty(s)) {
                                presenter.createProduct(s.toString());
                            } else {
                                showToast("名称不能为空");
                            }
                            dialog.dismiss();
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
