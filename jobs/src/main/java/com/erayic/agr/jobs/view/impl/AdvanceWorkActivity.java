package com.erayic.agr.jobs.view.impl;

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
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.WorkInfoItemAdapter;
import com.erayic.agr.jobs.adapter.WorkListItemAdapter;
import com.erayic.agr.jobs.presenter.IAdvanceWorkPresenter;
import com.erayic.agr.jobs.presenter.impl.AdvanceWorkPresenterImpl;
import com.erayic.agr.jobs.view.IAdvanceWorkView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/jobs/activity/AdvanceWorkActivity", name = "预设作业列表")
public class AdvanceWorkActivity extends BaseActivity implements IAdvanceWorkView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.advance_work_list_RecyclerView)
    RecyclerView advanceWorkListRecyclerView;
    @BindView(R2.id.advance_work_list_swipe)
    SwipeRefreshLayout advanceWorkListSwipe;

    private IAdvanceWorkPresenter presenter;
    private WorkListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_advance_work);
    }

    @Override
    public void initView() {
        toolbar.setTitle("预设作业");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        advanceWorkListSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(AdvanceWorkActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        advanceWorkListRecyclerView.setLayoutManager(manager);
        adapter = new WorkListItemAdapter(AdvanceWorkActivity.this,null);
        adapter.setOnItemClickListener(new WorkListItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, String jobID, String jobName) {
                ARouter.getInstance().build("/jobs/activity/WorkInfoActivity")
                        .withBoolean("isAdd", false)
                        .withString("JobID", jobID)
                        .withString("strTitle", jobName)
                        .navigation();
            }
        });
        advanceWorkListRecyclerView.setAdapter(adapter);
        advanceWorkListRecyclerView.addItemDecoration(new DividerItemDecoration(AdvanceWorkActivity.this, DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    public void initData() {
        presenter = new AdvanceWorkPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getJobList();
    }


    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (advanceWorkListSwipe != null && !advanceWorkListSwipe.isRefreshing())
                    advanceWorkListSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (advanceWorkListSwipe != null && advanceWorkListSwipe.isRefreshing())
                    advanceWorkListSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshWorkView(final List<CommonWorkListBean> list) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jobs_advance_work, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_bar_jobs_advance_work) {
            ARouter.getInstance().build("/jobs/activity/WorkInfoActivity")
                    .withBoolean("isAdd", true)
                    .withString("strTitle", "新增预设作业")
                    .navigation();
        }
        return super.onOptionsItemSelected(item);
    }


}
