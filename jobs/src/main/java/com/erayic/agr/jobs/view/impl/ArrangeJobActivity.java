package com.erayic.agr.jobs.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.work.CommonJobListBean;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.view.IArrangeJobView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/jobs/activity/ArrangeJobActivity", name = "工作安排列表")
public class ArrangeJobActivity extends BaseActivity implements IArrangeJobView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.arrange_job_list_RecyclerView)
    RecyclerView arrangeJobListRecyclerView;
    @BindView(R2.id.arrange_job_list_swipe)
    SwipeRefreshLayout arrangeJobListSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_arrange_job);
    }

    @Override
    public void initView() {
        toolbar.setTitle("工作安排");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        arrangeJobListSwipe.setOnRefreshListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (arrangeJobListSwipe != null && !arrangeJobListSwipe.isRefreshing())
                    arrangeJobListSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (arrangeJobListSwipe != null && arrangeJobListSwipe.isRefreshing())
                    arrangeJobListSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshJobView(List<CommonJobListBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {

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
        getMenuInflater().inflate(R.menu.menu_jobs_arrange_job, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_bar_jobs_arrange_job) {
            ARouter.getInstance().build("/jobs/activity/JobInfoActivity").withString("strTitle", "增加工作安排").withBoolean("isAdd", true).navigation();
        }
        return super.onOptionsItemSelected(item);
    }


}
