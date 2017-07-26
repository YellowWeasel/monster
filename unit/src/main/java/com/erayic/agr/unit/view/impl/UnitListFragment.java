package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.UnitRefreshMessage;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;
import com.erayic.agr.common.net.back.enums.EnumCategoryType;
import com.erayic.agr.common.net.back.enums.EnumControlRelayStatus;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitListItemAdapter;
import com.erayic.agr.unit.adapter.UnitListItemByBatchAdapter;
import com.erayic.agr.unit.adapter.UnitListItemByControlAdapter;
import com.erayic.agr.unit.adapter.UnitListItemByMonitorAdapter;
import com.erayic.agr.unit.adapter.entity.UnitListItemByControlEntity;
import com.erayic.agr.unit.event.BatchInfoEvent;
import com.erayic.agr.unit.presenter.IUnitListPresenter;
import com.erayic.agr.unit.presenter.impl.UnitListPresenterImpl;
import com.erayic.agr.unit.view.IUnitListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/fragment/UnitListFragment", name = "管理单元列表")
public class UnitListFragment extends BaseFragment implements IUnitListView, SwipeRefreshLayout.OnRefreshListener {

    //    /* 标题栏 */
    @Autowired
    String titleName;

    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_list_RecyclerView)
    RecyclerView unitListRecyclerView;
    @BindView(R2.id.unit_list_swipe)
    SwipeRefreshLayout unitListSwipe;

    private IUnitListPresenter presenter;

    private UnitListItemAdapter adapter;
    private UnitListItemByControlAdapter controlAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unit_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        toolbar.setTitle(titleName);
        unitListSwipe.setOnRefreshListener(this);
        adapter = new UnitListItemAdapter(getActivity());
        controlAdapter = new UnitListItemByControlAdapter(getActivity(), null);
        adapter.setControlAdapter(controlAdapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
        adapter.setOnItemScrollToPositionWithOffset(new UnitListItemAdapter.OnItemScrollToPositionWithOffset() {
            @Override
            public void scrollToPositionWithOffset(int position) {
                unitListRecyclerView.smoothScrollToPosition(position);
            }
        });
        adapter.setOnItemBatchClickListener(new UnitListItemByBatchAdapter.OnItemBatchClickListener() {
            @Override
            public void onBatchInfo(String unitID, String batchID, String batchName, String imgUrl) {
                ARouter.getInstance().build("/unit/activity/BatchInfoActivity")
                        .withString("unitID", unitID)
                        .withString("batchID", batchID)
                        .withString("batchName", batchName)
                        .withString("imgUrl", imgUrl)//图片地址
                        .withBoolean("history", false)
                        .navigation();
            }

            @Override
            public void onAddBatch(String unitID) {
                ARouter.getInstance()
                        .build("/unit/activity/AddUpdateBatchActivity")
                        .withBoolean("isAdd", true)
                        .withString("unitID", unitID)
                        .navigation();
            }

            @Override
            public void onHistoryBatch(String unitID) {
                ARouter.getInstance()
                        .build("/unit/activity/HistoryBatchListActivity")
                        .withString("unitID", unitID)
                        .navigation();
            }
        });
        //控制设备
        controlAdapter.setOnDeviceClickListener(new UnitListItemByControlAdapter.OnDeviceClickListener() {
            @Override
            public void onDeviceClick(String serialNum, String deviceName) {
                ARouter.getInstance()
                        .build("/device/activity/DeviceInfoActivity")
                        .withString("serialNum", serialNum)
                        .withString("deviceName", deviceName)
                        .navigation();
            }

            @Override
            public void onControlClick(CommonUnitListBean.UnitListCtrlItemsBean bean, int cmd, int position) {
                presenter.opeCtrDevice(bean, position, cmd);
            }
        });
        //监控
        adapter.setOnMonitorClickListener(new UnitListItemByMonitorAdapter.OnMonitorClickListener() {

            @Override
            public void onMonitorClick(String serialNum) {
//                presenter.getMonitorInfo(serialNum);//请求设备信息
                ARouter.getInstance()
                        .build("/unit/activity/BatchDahuaVideoActivity")
                        .withString("serialNum", serialNum)
                        .navigation();//跳转到具体的页面
            }
        });
        unitListRecyclerView.setLayoutManager(manager);
        unitListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter = new UnitListPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getAllUnit();
    }

    @Subscribe
    public void onBatchInfoMessage(UnitRefreshMessage event) {
        if (event.getMsgType() == UnitRefreshMessage.UNIT_MASTER_LIST) {
            onRefresh();
        }
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitListSwipe != null && !unitListSwipe.isRefreshing())
                    unitListSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unitListSwipe != null && unitListSwipe.isRefreshing())
                    unitListSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshUnitView(final List<CommonUnitListBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void refreshEquCtrSure(CommonUnitListBean.UnitListCtrlItemsBean bean, final int position) {
        switch (bean.getRelayType()) {
            case 1://启停
            {
                if (bean.getStatus() == EnumControlRelayStatus.TYPE_TurnOn) {
                    bean.setStatus(EnumControlRelayStatus.TYPE_TurnOff);
                } else if (bean.getStatus() == EnumControlRelayStatus.TYPE_TurnOff) {
                    bean.setStatus(EnumControlRelayStatus.TYPE_TurnOn);
                } else {
                    bean.setStatus(EnumControlRelayStatus.TYPE_ErrorSta);
                }
                bean.setStatusDesc(EnumControlRelayStatus.getStatueDes(bean.getStatus()));
                List<UnitListItemByControlEntity> listControl = controlAdapter.getData();
                if (listControl.size() > position) {
                    CommonUnitListBean.UnitListCtrlItemsBean bean0 = (CommonUnitListBean.UnitListCtrlItemsBean) listControl.get(position).getData();
                    if (TextUtils.equals(bean0.getSerialNum(), bean.getSerialNum())) {
                        listControl.get(position).setData(bean);
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                controlAdapter.notifyItemChanged(position);
                            }
                        }, 1000);//1秒延时
                    }
                }
            }
            break;
            case 2://正反转
                break;
        }
    }

    @Override
    public void refreshEquCtrFail(CommonUnitListBean.UnitListCtrlItemsBean bean, final int position) {
        switch (bean.getRelayType()) {
            case 1://启停
            {
                List<UnitListItemByControlEntity> listControl = controlAdapter.getData();
                if (listControl.size() > position) {
                    CommonUnitListBean.UnitListCtrlItemsBean bean0 = (CommonUnitListBean.UnitListCtrlItemsBean) listControl.get(position).getData();
                    if (TextUtils.equals(bean0.getSerialNum(), bean.getSerialNum())) {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                controlAdapter.notifyItemChanged(position);
                            }
                        }, 1000);//1秒延时
                    }
                }
            }
            break;
            case 2://正反转
                break;
        }
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
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
