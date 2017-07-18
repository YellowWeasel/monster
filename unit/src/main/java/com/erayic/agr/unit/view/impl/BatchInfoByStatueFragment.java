package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.UnitRefreshMessage;
import com.erayic.agr.common.net.back.enums.EnumBatchByBindType;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchInfoByStatusItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitBatchItemEntity;
import com.erayic.agr.unit.event.BatchInfoEvent;
import com.erayic.agr.unit.presenter.IBatchInfoByStatuePresenter;
import com.erayic.agr.unit.presenter.impl.BatchInfoByStatuePresenterImpl;
import com.erayic.agr.unit.view.IBatchInfoByStatueView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/fragment/BatchInfoByStatueFragment", name = "批次详情生长数据")
public class BatchInfoByStatueFragment extends BaseFragment implements IBatchInfoByStatueView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.unit_batch_statue_RecyclerView)
    RecyclerView unitBatchStatueRecyclerView;
    @BindView(R2.id.unit_batch_statue_swipe)
    SwipeRefreshLayout unitBatchStatueSwipe;

    @Autowired
    String unitID;
    @Autowired
    String batchID;
    @Autowired
    String batchName;

    private UnitBatchInfoByStatusItemAdapter adapter;
    private IBatchInfoByStatuePresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unit_batch_by_statue;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unitBatchStatueSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        unitBatchStatueRecyclerView.setLayoutManager(manager);
        adapter = new UnitBatchInfoByStatusItemAdapter(getActivity(), null);
        adapter.setOnItemChartClickListener(new UnitBatchInfoByStatusItemAdapter.OnItemChartClickListener() {
            @Override
            public void onBind(int type) {
                switch (type) {
                    case UnitBatchItemEntity.TYPE_TITLE_MODEL:
                    case UnitBatchItemEntity.TYPE_MODEL:
                        ARouter.getInstance().build("/unit/activity/BatchBindServiceActivity")
                                .withString("unitID", unitID)
                                .withString("batchID", batchID)
                                .withString("batchName", batchName)
                                .withInt("serType", EnumBatchByBindType.TYPE_cycle)
                                .navigation();
                        break;
                    case UnitBatchItemEntity.TYPE_TITLE_SUGGEST:
                    case UnitBatchItemEntity.TYPE_SUGGEST:
                        ARouter.getInstance().build("/unit/activity/BatchBindServiceActivity")
                                .withString("unitID", unitID)
                                .withString("batchID", batchID)
                                .withString("batchName", batchName)
                                .withInt("serType", EnumBatchByBindType.TYPE_Adaptability)
                                .navigation();
                        break;
                    case UnitBatchItemEntity.TYPE_TITLE_PRODUCE:
                    case UnitBatchItemEntity.TYPE_PRODUCE:
//                        ARouter.getInstance().build("/unit/activity/BatchBindServiceActivity")
//                                .withString("unitID", unitID)
//                                .withString("batchID", batchID)
//                                .withString("batchName", batchName)
//                                .withInt("serType", EnumBatchByBindType.TYPE_produce)
//                                .navigation();
                        showToast("暂未开放，敬请期待");
                        break;
                    default:
                        showToast("未知数据类型");
                        break;
                }
            }

            @Override
            public void onToInfo(int type, Object object) {
                switch (type) {
                    case UnitBatchItemEntity.TYPE_MODEL:
                        ARouter.getInstance().build("/unit/activity/BatchCycleInfoActivity")
                                .withSerializable("data", (Serializable) object)
                                .withString("unitID", unitID)
                                .withString("batchID", batchID).withString("batchName", batchName).navigation();
                        break;
                    case UnitBatchItemEntity.TYPE_SUGGEST:
                        ARouter.getInstance().build("/unit/activity/BatchSuggestInfoActivity")
                                .withSerializable("data", (Serializable) object)
                                .withString("unitID", unitID)
                                .withString("batchID", batchID).withString("batchName", batchName).navigation();
                        break;
                    case UnitBatchItemEntity.TYPE_PRODUCE:
                        showToast("暂未开放，敬请期待");
                        break;
                    default:
                        showToast("未知数据类型");
                        break;
                }
            }
        });
        unitBatchStatueRecyclerView.setAdapter(adapter);
        unitBatchStatueRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData() {
        presenter = new BatchInfoByStatuePresenterImpl(this);
        onRefresh();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onRefresh() {
        presenter.getBatchInfo(unitID, EnumUnitType.TYPE_PLOTS, batchID);
    }

    @Subscribe
    public void onMessageEvent(UnitRefreshMessage event) {
        if (event.getMsgType() == UnitRefreshMessage.UNIT_MASTER_STATUE) {
            onRefresh();
        }
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchStatueSwipe != null && !unitBatchStatueSwipe.isRefreshing())
                    unitBatchStatueSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitBatchStatueSwipe != null && unitBatchStatueSwipe.isRefreshing())
                    unitBatchStatueSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshBatchView(final CommonUnitBatchInfoBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //回传toolbar数据
                BatchInfoEvent event = new BatchInfoEvent();
                event.setProductID(bean.getBatchInfo().getProductID());
                event.setProductName(bean.getBatchInfo().getProductName());
                event.setProductIcon(bean.getBatchInfo().getProductIcon());
                event.setStartTime(bean.getBatchInfo().getStartTime());
                event.setEndTime(bean.getBatchInfo().getEndTime());
                event.setQuantity(bean.getBatchInfo().getQuantity());
                event.setOpeName(bean.getBatchInfo().getOpeName());
                event.setStatus(bean.getBatchInfo().getStatus());
                EventBus.getDefault().post(event);

                List<UnitBatchItemEntity> list = new ArrayList<>();
                //生长期评估
                {
                    UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
                    entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE_MODEL);
                    entityTitle.setName("生长周期预测");
                    list.add(entityTitle);

                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
                    entity.setItemType(UnitBatchItemEntity.TYPE_MODEL);
                    entity.setBind(bean.isBindCycle());
                    entity.setData(bean.getCycles());
                    list.add(entity);
                }

                //生长建议
                {
                    UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
                    entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE_SUGGEST);
                    entityTitle.setName("生长适应性评价");
                    list.add(entityTitle);

                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
                    entity.setItemType(UnitBatchItemEntity.TYPE_SUGGEST);
                    entity.setBind(bean.isBindSuggest());
                    entity.setData(bean.getSuggest());
                    list.add(entity);
                }

                //产量评估
                {
                    UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
                    entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE_PRODUCE);
                    entityTitle.setName("产量预测");
                    list.add(entityTitle);

                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
                    entity.setItemType(UnitBatchItemEntity.TYPE_PRODUCE);
                    entity.setBind(bean.isBindFeild());
                    list.add(entity);
                }
                adapter.setNewData(list);

                //测试数据
//                List<UnitBatchItemEntity> list = new ArrayList<>();
//                //生长期评估
//                {
//                    UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
//                    entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE_MODEL);
//                    entityTitle.setName("生长周期预测");
//                    list.add(entityTitle);
//
//                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
//                    entity.setItemType(UnitBatchItemEntity.TYPE_MODEL);
//                    list.add(entity);
//                }
//
//                //生长适应性
//                {
//                    UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
//                    entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE_SUGGEST);
//                    entityTitle.setName("生长适应性评价");
//                    list.add(entityTitle);
//
//                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
//                    entity.setItemType(UnitBatchItemEntity.TYPE_SUGGEST);
//                    list.add(entity);
//                }
//
//                //产量评估
//                {
//                    UnitBatchItemEntity entityTitle = new UnitBatchItemEntity();
//                    entityTitle.setItemType(UnitBatchItemEntity.TYPE_TITLE_PRODUCE);
//                    entityTitle.setName("产量预测");
//                    list.add(entityTitle);
//
//                    UnitBatchItemEntity entity = new UnitBatchItemEntity();
//                    entity.setItemType(UnitBatchItemEntity.TYPE_PRODUCE);
//                    list.add(entity);
//                }
//
//                adapter.setNewData(list);


            }
        });
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity(), msg);
            }
        });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
