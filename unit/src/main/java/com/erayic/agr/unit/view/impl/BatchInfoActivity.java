package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchInfoItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitBatchItemEntity;
import com.erayic.agr.unit.presenter.IBatchInfoPresenter;
import com.erayic.agr.unit.presenter.impl.BatchInfoPresenterImpl;
import com.erayic.agr.unit.view.IBatchInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/BatchInfoActivity", name = "批次详情")
public class BatchInfoActivity extends BaseActivity implements IBatchInfoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_batch_info_recycler)
    RecyclerView unitBatchInfoRecycler;
    @BindView(R2.id.unit_batch_info_swipe)
    SwipeRefreshLayout unitBatchInfoSwipe;

    @Autowired
    String batchID;
    @Autowired
    String batchName;

    private UnitBatchInfoItemAdapter adapter;
    private IBatchInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle(batchName);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        unitBatchInfoSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(BatchInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        unitBatchInfoRecycler.setLayoutManager(manager);
        adapter = new UnitBatchInfoItemAdapter(BatchInfoActivity.this, null);
        unitBatchInfoRecycler.setAdapter(adapter);
        unitBatchInfoRecycler.addItemDecoration(new DividerItemDecoration(BatchInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new BatchInfoPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getBatchInfo(batchID);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchInfoSwipe != null && !unitBatchInfoSwipe.isRefreshing())
                    unitBatchInfoSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchInfoSwipe != null && unitBatchInfoSwipe.isRefreshing())
                    unitBatchInfoSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshBatchView(Object bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //测试数据

                List<UnitBatchItemEntity> list = new ArrayList<>();
                //标题
                UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
                entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE);
                entityTitle.setName("批次信息");
                list.add(entityTitle);

                //批次信息
                {
                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
                    entity.setItemType(UnitBatchItemEntity.TYPE_BATCH);
                    list.add(entity);
                }

                entityTitle.setName("生长模型与产量评估");
                list.add(entityTitle);

                //生长模型与产量评估
                {
                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
                    entity.setItemType(UnitBatchItemEntity.TYPE_MODEL);
                    list.add(entity);
                }

                entityTitle.setName("生长适应性");
                list.add(entityTitle);

                //生长适应性
                {
                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
                    entity.setItemType(UnitBatchItemEntity.TYPE_SUGGEST);
                    list.add(entity);
                }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
