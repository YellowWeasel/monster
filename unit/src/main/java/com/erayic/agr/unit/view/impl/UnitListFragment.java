package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitListItemAdapter;
import com.erayic.agr.unit.adapter.UnitListItemByBatchAdapter;
import com.erayic.agr.unit.presenter.IUnitListPresenter;
import com.erayic.agr.unit.presenter.impl.UnitListPresenterImpl;
import com.erayic.agr.unit.view.IUnitListView;

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
    @BindView(R2.id.toolbar_title_name)
    TextView toolbarTitleName;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.unit_list_RecyclerView)
    RecyclerView unitListRecyclerView;
    @BindView(R2.id.unit_list_swipe)
    SwipeRefreshLayout unitListSwipe;

    private IUnitListPresenter presenter;

    private UnitListItemAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unit_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        toolbar.setTitle("");
        toolbarTitleName.setText(titleName);
        unitListSwipe.setOnRefreshListener(this);
        adapter = new UnitListItemAdapter(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
        adapter.setOnItemScrollToPositionWithOffset(new UnitListItemAdapter.onItemScrollToPositionWithOffset() {
            @Override
            public void scrollToPositionWithOffset(int position) {
                unitListRecyclerView.smoothScrollToPosition(position);
            }
        });
        adapter.setOnItemBatchClickListener(new UnitListItemByBatchAdapter.OnItemBatchClickListener() {
            @Override
            public void onBatchInfo(String batchID) {
                showToast("暂未实现");
            }

            @Override
            public void onAddBatch(String unitID) {
                ARouter.getInstance().build("/unit/activity/AddBatchActivity").withString("unitID", unitID).navigation();
            }
        });
        unitListRecyclerView.setLayoutManager(manager);
        unitListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter = new UnitListPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getAllUnit();
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
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity().getApplicationContext(), msg);
            }
        });
    }


}
