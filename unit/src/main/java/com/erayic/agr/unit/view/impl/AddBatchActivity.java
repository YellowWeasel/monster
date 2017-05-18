package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitAddBatchItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitAddBatchItemEntity;
import com.erayic.agr.unit.view.IAddBatchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/AddBatchActivity", name = "增加批次")
public class AddBatchActivity extends BaseActivity implements IAddBatchView {


    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.unit_batch_add_recycler)
    RecyclerView unitBatchAddRecycler;

    @Autowired
    String unitID;//管理单元ID

    private UnitAddBatchItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_add);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("增加批次");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(AddBatchActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        unitBatchAddRecycler.setLayoutManager(manager);
        adapter = new UnitAddBatchItemAdapter(AddBatchActivity.this, null);
        adapter.setOnBatchClickListener(new UnitAddBatchItemAdapter.OnBatchItemClickListener() {
            @Override
            public void onClick(View view, int itemType, int position) {
                switch (itemType) {
                    case UnitAddBatchItemEntity.TYPE_PRODUCT://产品
                        break;
                    case UnitAddBatchItemEntity.TYPE_SEED://种苗
                        break;
                    case UnitAddBatchItemEntity.TYPE_AREA://面积
                        break;
                    case UnitAddBatchItemEntity.TYPE_DATE://时间
                        break;
                    case UnitAddBatchItemEntity.TYPE_PERSONNEL://种植人员
                        break;
                }
            }
        });
        unitBatchAddRecycler.setAdapter(adapter);
        unitBatchAddRecycler.addItemDecoration(new DividerItemDecoration(AddBatchActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {

        List<UnitAddBatchItemEntity> list = new ArrayList<>();

        //产品
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_PRODUCT);
            list.add(entity);
        }

        //种苗
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_SEED);
            list.add(entity);
        }

        //面积
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_AREA);
            list.add(entity);
        }

        //时间
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_DATE);
            list.add(entity);
        }

        //人员
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_PERSONNEL);
            list.add(entity);
        }
        adapter.setNewData(list);
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        if (item.getItemId() == R.id.action_unit_batch_save) {//保存

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.unit_batch_add, menu);
        return true;
    }
}
