package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageUserCheckItemAdapter;
import com.erayic.agr.manage.event.UserCheckEvent;
import com.erayic.agr.manage.presenter.ISelectUserPresenter;
import com.erayic.agr.manage.presenter.impl.SelectUserPresenterImpl;
import com.erayic.agr.manage.view.ISelectUserView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/SelectUserActivity", name = "根据基地ID获取基地的所有人员")
public class SelectUserActivity extends BaseActivity implements ISelectUserView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_user_RecyclerView)
    RecyclerView manageUserRecyclerView;
    @BindView(R2.id.manage_user_swipe)
    SwipeRefreshLayout manageUserSwipe;

    @Autowired
    String baseID;//基地ID

    private ISelectUserPresenter presenter;
    private ManageUserCheckItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_select);
    }

    @Override
    public void initView() {
        toolbar.setTitle("选择负责人");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageUserSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(SelectUserActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageUserRecyclerView.setLayoutManager(manager);
        adapter = new ManageUserCheckItemAdapter(SelectUserActivity.this, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonPersonnelBean bean = (CommonPersonnelBean) adapter.getData().get(position);
                bean.setCheck(!bean.isCheck());
                adapter.setData(position, bean);
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                return true;
            }
        });
        manageUserRecyclerView.setAdapter(adapter);
        manageUserRecyclerView.addItemDecoration(new DividerItemDecoration(SelectUserActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new SelectUserPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getAllUserBySpecifyBase(baseID);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageUserSwipe != null && !manageUserSwipe.isRefreshing())
                    manageUserSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageUserSwipe != null && manageUserSwipe.isRefreshing())
                    manageUserSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshUserView(final List<CommonPersonnelBean> bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(bean);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_user_save) {
            UserCheckEvent event = new UserCheckEvent();
            Map<String, String> map = new ArrayMap<>();
            for (CommonPersonnelBean bean : adapter.getData()) {
                if (bean.isCheck()) {
                    map.put(bean.getUserID(), bean.getName());
                }
            }
            if (map.size() == 0) {
                showToast("请选择负责人");
            } else {
                event.setUserMap(map);
                EventBus.getDefault().post(event);
                ErayicStack.getInstance().finishCurrentActivity();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_user_select, menu);
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


}
