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
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchCycleInfoItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitBatchCycleInfoEntity;
import com.erayic.agr.unit.presenter.IBatchCycleInfoPresenter;
import com.erayic.agr.unit.presenter.impl.BatchCycleInfoPresenterImpl;
import com.erayic.agr.unit.view.IBatchCycleInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/BatchCycleInfoActivity", name = "生长期预测详情")
public class BatchCycleInfoActivity extends BaseActivity implements IBatchCycleInfoView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_batch_cycle_recycler)
    RecyclerView unitBatchCycleRecycler;

    private LoadingDialog dialog;

    @Autowired
    String unitID;
    @Autowired
    String batchID;
    @Autowired
    String batchName;

    List<CommonUnitBatchInfoBean.CropCycle> cycles;

    private IBatchCycleInfoPresenter presenter;
    private UnitBatchCycleInfoItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cycles = (List<CommonUnitBatchInfoBean.CropCycle>) getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_unit_batch_cycle_info);

    }

    @Override
    public void initView() {
        toolbar.setTitle("生长期预测");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(BatchCycleInfoActivity.this);
        unitBatchCycleRecycler.setLayoutManager(manager);
        adapter = new UnitBatchCycleInfoItemAdapter(BatchCycleInfoActivity.this, null);
        unitBatchCycleRecycler.setAdapter(adapter);
        unitBatchCycleRecycler.addItemDecoration(new DividerItemDecoration(BatchCycleInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new BatchCycleInfoPresenterImpl(this);
        presenter.getCycleDetailByBatch(batchID, unitID, EnumUnitType.TYPE_PLOTS);
//        toolbar.setTitle(cycles.get(0).getEndDay());
    }

    @Override
    public void refreshBatchCycleView(final CommonUnitBatchCycleBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<UnitBatchCycleInfoEntity> list = new ArrayList<>();
                //生长期Title
                {
                    UnitBatchCycleInfoEntity entity = new UnitBatchCycleInfoEntity();
                    entity.setItemType(UnitBatchCycleInfoEntity.TYPE_TITLE);
                    entity.setName("生长期");
                    list.add(entity);
                }
                //生长期
                for (CommonUnitBatchInfoBean.CropCycle cycle : cycles) {
                    UnitBatchCycleInfoEntity entity = new UnitBatchCycleInfoEntity();
                    entity.setItemType(UnitBatchCycleInfoEntity.TYPE_CYCLE);
                    entity.setData1(cycle);
                    list.add(entity);
                }
                //积温Title
                {
                    UnitBatchCycleInfoEntity entity = new UnitBatchCycleInfoEntity();
                    entity.setItemType(UnitBatchCycleInfoEntity.TYPE_TITLE);
                    entity.setName("积温");
                    entity.setSubName("当前累积温度\n" + bean.getHistoryTemp().getTotalTemp() + "℃");
                    list.add(entity);
                }
                //积温
                {
                    UnitBatchCycleInfoEntity entity = new UnitBatchCycleInfoEntity();
                    entity.setItemType(UnitBatchCycleInfoEntity.TYPE_ACCTEM);
                    entity.setData1(bean.getHistoryTemp() == null ? new CommonUnitBatchCycleBean.TempInfo() : bean.getHistoryTemp());
                    entity.setData2(bean.getCurAccTemp() == null ? new CommonUnitBatchCycleBean.TempInfo() : bean.getCurAccTemp());
                    list.add(entity);
                }
                //降水Title
                {
                    UnitBatchCycleInfoEntity entity = new UnitBatchCycleInfoEntity();
                    entity.setItemType(UnitBatchCycleInfoEntity.TYPE_TITLE);
                    entity.setName("累积降水");
                    entity.setSubName("当前累积降水量\n" + bean.getHistoryRain().getTotalRain() + "ml");
                    list.add(entity);
                }
                //累积降水
                {
                    UnitBatchCycleInfoEntity entity = new UnitBatchCycleInfoEntity();
                    entity.setItemType(UnitBatchCycleInfoEntity.TYPE_ACCRAIN);
                    entity.setData1(bean.getHistoryRain() == null ? new CommonUnitBatchCycleBean.RainInfo() : bean.getHistoryRain());
                    entity.setData2(bean.getCurAccRain() == null ? new CommonUnitBatchCycleBean.RainInfo() : bean.getCurAccRain());
                    list.add(entity);
                }
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
                    dialog = new LoadingDialog(BatchCycleInfoActivity.this);
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
                    dialog = new LoadingDialog(BatchCycleInfoActivity.this);
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
