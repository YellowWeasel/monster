package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.UnitRefreshMessage;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchInfoByLogItemAdapter;
import com.erayic.agr.unit.adapter.UnitBatchInfoByResumeItemAdapter;
import com.erayic.agr.unit.presenter.IBatchInfoByLogPresenter;
import com.erayic.agr.unit.presenter.impl.BatchInfoByLogPresenterImpl;
import com.erayic.agr.unit.view.IBatchInfoByLogView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/unit/fragment/BatchInfoByLogFragment", name = "工作日志")
public class BatchInfoByLogFragment extends BaseFragment implements IBatchInfoByLogView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R2.id.unit_batch_log_RecyclerView)
    RecyclerView unitBatchLogRecyclerView;
    @BindView(R2.id.unit_batch_log_swipe)
    SwipeRefreshLayout unitBatchLogSwipe;
    @BindView(R2.id.unit_batch_log_add)
    FloatingActionButton unitBatchLogAdd;

    @Autowired
    String batchID;

    private static final int PAGE_SIZE = 5;
    private int currentPage = 0;//当前页数

    private IBatchInfoByLogPresenter presenter;
    private UnitBatchInfoByLogItemAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unit_batch_by_log;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unitBatchLogSwipe.setOnRefreshListener(this);
        unitBatchLogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/unit/activity/AddLogActivity").withString("batchID", batchID).navigation();
            }
        });
        //使用线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        unitBatchLogRecyclerView.setLayoutManager(manager);
        adapter = new UnitBatchInfoByLogItemAdapter(getActivity(), null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        unitBatchLogRecyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, unitBatchLogRecyclerView);
//        unitBatchLogRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData() {
        presenter = new BatchInfoByLogPresenterImpl(this);
        onRefresh();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onRefresh() {
        presenter.getWorkLogByBatch(batchID, PAGE_SIZE);
    }


    @Override
    public void onLoadMoreRequested() {
        //加载更多
        presenter.getWorkLogByBatch(batchID, currentPage, PAGE_SIZE);
        adapter.setEnableLoadMore(true);
    }

    @Subscribe
    public void onMessageEvent(UnitRefreshMessage event) {
        if (event.getMsgType() == UnitRefreshMessage.UNIT_MASTER_LOG) {
            ErayicLog.i("event-refresh", "/unit/activity/AddLogActivity");
            onRefresh();
        }
    }


    @Override
    public void refreshLogsView(final List<CommonUnitBatchLogsBean> list) {
        currentPage = 2;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void loadMoreSure(final List<CommonUnitBatchLogsBean> list) {
        currentPage++;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.loadMoreComplete();//加载完成
                if (list != null)
                    adapter.addData(list);
                if (list.size() == 0)//没有更多数据了
                    adapter.loadMoreEnd();
            }
        });
    }

    @Override
    public void loadMoreFailure() {
        adapter.loadMoreFail();
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity().getApplicationContext(), msg);
            }
        });
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchLogSwipe != null && !unitBatchLogSwipe.isRefreshing())
                    unitBatchLogSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchLogSwipe != null && unitBatchLogSwipe.isRefreshing())
                    unitBatchLogSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
