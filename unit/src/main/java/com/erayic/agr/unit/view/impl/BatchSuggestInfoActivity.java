package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSuggestBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchSuggestInfoItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitBatchSuggestInfoEntity;
import com.erayic.agr.unit.presenter.IBatchSuggestInfoPresenter;
import com.erayic.agr.unit.presenter.impl.BatchSuggestInfoPresenterImpl;
import com.erayic.agr.unit.view.IBatchSuggestInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/BatchSuggestInfoActivity", name = "生长适应性评价")
public class BatchSuggestInfoActivity extends BaseActivity implements IBatchSuggestInfoView {


    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_batch_give_recycler)
    RecyclerView unitBatchGiveRecycler;

    private LoadingDialog dialog;

    @Autowired
    String unitID;
    @Autowired
    String batchID;
    @Autowired
    String batchName;

//    CommonUnitBatchInfoBean.FarmingSuggestionResult resultList;

    private UnitBatchSuggestInfoItemAdapter adapter;
    private IBatchSuggestInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        resultList = (CommonUnitBatchInfoBean.FarmingSuggestionResult) getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_unit_batch_give_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("生长适应性评价");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(BatchSuggestInfoActivity.this);
        unitBatchGiveRecycler.setLayoutManager(manager);
        adapter = new UnitBatchSuggestInfoItemAdapter(BatchSuggestInfoActivity.this, null);
        unitBatchGiveRecycler.setAdapter(adapter);
        unitBatchGiveRecycler.addItemDecoration(new DividerItemDecoration(BatchSuggestInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new BatchSuggestInfoPresenterImpl(this);
        presenter.getSuggestDetail(unitID, EnumUnitType.TYPE_PLOTS, batchID);
    }

    @Override
    public void refreshBatchSuggestView(final CommonUnitBatchSuggestBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //测试
                List<UnitBatchSuggestInfoEntity> list = new ArrayList<>();
                //适应性评价
                {
                    UnitBatchSuggestInfoEntity entity = new UnitBatchSuggestInfoEntity();
                    entity.setItemType(UnitBatchSuggestInfoEntity.TYPE_TITLE);
                    entity.setName("适应性评价");
                    list.add(entity);
                }
                //适应性
                for (CommonUnitBatchSuggestBean.SuggestionResult result : bean.getSuggest().getSuggestionResultList()) {
                    UnitBatchSuggestInfoEntity entity = new UnitBatchSuggestInfoEntity();
                    entity.setItemType(UnitBatchSuggestInfoEntity.TYPE_SUGGEST);
                    entity.setData(result);
                    list.add(entity);
                }

                //24小时标题
                {
                    UnitBatchSuggestInfoEntity entity = new UnitBatchSuggestInfoEntity();
                    entity.setItemType(UnitBatchSuggestInfoEntity.TYPE_TITLE);
                    entity.setName("未来24小时天气情况(小时)");
                    list.add(entity);
                }

                //24小时天气情况
                {
                    UnitBatchSuggestInfoEntity entity = new UnitBatchSuggestInfoEntity();
                    entity.setItemType(UnitBatchSuggestInfoEntity.TYPE_WEATHER);
                    entity.setData(bean.getFeatures());
                    entity.setSubData(bean.getRealTime());
                    list.add(entity);
                }

                //环境标题
//                {
//                    UnitBatchSuggestInfoEntity entity = new UnitBatchSuggestInfoEntity();
//                    entity.setItemType(UnitBatchSuggestInfoEntity.TYPE_TITLE);
//                    list.add(entity);
//                }
//
//                //当前环境
//                {
//                    UnitBatchSuggestInfoEntity entity = new UnitBatchSuggestInfoEntity();
//                    entity.setItemType(UnitBatchSuggestInfoEntity.TYPE_ENVIRONMENT);
//                    list.add(entity);
//                }

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
                    dialog = new LoadingDialog(BatchSuggestInfoActivity.this);
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
                    dialog = new LoadingDialog(BatchSuggestInfoActivity.this);
                dialog.dismiss();
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
