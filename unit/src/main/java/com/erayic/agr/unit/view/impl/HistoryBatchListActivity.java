package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchHistoryItemAdapter;
import com.erayic.agr.unit.presenter.IHistoryBatchPresenter;
import com.erayic.agr.unit.presenter.impl.HistoryBatchPresenterImpl;
import com.erayic.agr.unit.view.IHistoryBatchListView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/unit/activity/HistoryBatchListActivity", name = "历史批次列表")
public class HistoryBatchListActivity extends BaseActivity implements IHistoryBatchListView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_batch_history_recycler)
    RecyclerView unitBatchHistoryRecycler;
    @BindView(R2.id.unit_batch_history_swipe)
    SwipeRefreshLayout unitBatchHistorySwipe;

    @Autowired
    String unitID;

    private static final int PAGE_SIZE = 5;
    private int currentPage = 0;//当前页数

    private IHistoryBatchPresenter presenter;
    private UnitBatchHistoryItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_history_list);
    }

    @Override
    public void initView() {
        toolbar.setTitle("历史批次");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        unitBatchHistorySwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(HistoryBatchListActivity.this);
        unitBatchHistoryRecycler.setLayoutManager(manager);
        adapter = new UnitBatchHistoryItemAdapter(HistoryBatchListActivity.this, null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.isFirstOnly(false);
        unitBatchHistoryRecycler.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, unitBatchHistoryRecycler);
        unitBatchHistoryRecycler.addItemDecoration(new DividerItemDecoration(HistoryBatchListActivity.this, DividerItemDecoration.VERTICAL_LIST, DividerItemDecoration.DividerType.TYPE_F2F2F2));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonUnitBatchInfoBean.Batch batch = (CommonUnitBatchInfoBean.Batch) adapter.getData().get(position);
                ARouter.getInstance().build("/unit/activity/BatchInfoActivity")
                        .withString("unitID", unitID)
                        .withString("batchID", batch.getBatchID())
                        .withString("batchName", batch.getProductName())
                        .withString("imgUrl", batch.getProductIcon())//图片地址
                        .withBoolean("history", true)
                        .navigation();
            }
        });
    }

    @Override
    public void initData() {
        presenter = new HistoryBatchPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getAllBatchByHistory(unitID, PAGE_SIZE);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.getAllBatchByHistory(unitID, currentPage, PAGE_SIZE);
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
            ErayicStack.getInstance().finishCurrentActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchHistorySwipe != null && !unitBatchHistorySwipe.isRefreshing())
                    unitBatchHistorySwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchHistorySwipe != null && unitBatchHistorySwipe.isRefreshing())
                    unitBatchHistorySwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshBatchView(final List<CommonUnitBatchInfoBean.Batch> list) {
        currentPage = 2;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void loadMoreSure(final List<CommonUnitBatchInfoBean.Batch> list) {
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
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.loadMoreFail();
            }
        });
    }
}
