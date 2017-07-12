package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchResumeBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchInfoByResumeItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitBatchInfoByResumeEntity;
import com.erayic.agr.unit.presenter.IBatchInfoByResumePresenter;
import com.erayic.agr.unit.presenter.impl.BatchInfoByResumePresenterImpl;
import com.erayic.agr.unit.view.IBatchInfoByResumeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/fragment/BatchInfoByResumeFragment", name = "生产履历")
public class BatchInfoByResumeFragment extends BaseFragment implements IBatchInfoByResumeView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R2.id.unit_batch_resume_RecyclerView)
    RecyclerView unitBatchResumeRecyclerView;
    @BindView(R2.id.unit_batch_resume_swipe)
    SwipeRefreshLayout unitBatchResumeSwipe;
    @Autowired
    String batchID;

    private static final int PAGE_SIZE = 5;
    private int currentPage = 0;//当前页数

    private UnitBatchInfoByResumeItemAdapter adapter;
    private IBatchInfoByResumePresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unit_batch_by_resume;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //使用线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        unitBatchResumeRecyclerView.setLayoutManager(manager);
        adapter = new UnitBatchInfoByResumeItemAdapter(getActivity(), null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        unitBatchResumeRecyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, unitBatchResumeRecyclerView);
    }

    @Override
    protected void initData() {
        presenter = new BatchInfoByResumePresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getPorduceHistoryByBatch(batchID, PAGE_SIZE);
    }


    @Override
    public void onLoadMoreRequested() {
        presenter.getPorduceHistoryByBatch(batchID, currentPage, PAGE_SIZE);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void refreshLogsView(final List<CommonUnitBatchResumeBean> list) {
        currentPage = 2;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void loadMoreSure(final List<CommonUnitBatchResumeBean> list) {
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
                if (unitBatchResumeSwipe != null && !unitBatchResumeSwipe.isRefreshing())
                    unitBatchResumeSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchResumeSwipe != null && unitBatchResumeSwipe.isRefreshing())
                    unitBatchResumeSwipe.setRefreshing(false);
            }
        });
    }


}
