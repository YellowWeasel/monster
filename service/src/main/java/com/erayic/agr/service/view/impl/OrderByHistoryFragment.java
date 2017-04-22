package com.erayic.agr.service.view.impl;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.OrderByHistoryAdapter;
import com.erayic.agr.service.presenter.IOrderByHistoryPresenter;
import com.erayic.agr.service.presenter.impl.OrderByHistoryPresenterImpl;
import com.erayic.agr.service.view.IOrderByHistoryView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/fragment/OrderByHistoryFragment", name = "订购记录")
public class OrderByHistoryFragment extends BaseFragment implements IOrderByHistoryView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Autowired
    String titleName;
    @BindView(R2.id.order_history_recycler)
    RecyclerView orderHistoryRecycler;
    @BindView(R2.id.order_history_swipe)
    SwipeRefreshLayout orderHistorySwipe;

    private OrderByHistoryAdapter adapter;
    private IOrderByHistoryPresenter presenter;

    private int PAGE_SIZE = 5;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_history;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        orderHistorySwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getActivity());
        manager.setScrollEnabled(true);//滑动监听
        orderHistoryRecycler.setLayoutManager(manager);
        adapter = new OrderByHistoryAdapter(getActivity(), null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnOrderClickListener(new OrderByHistoryAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(View view, String orderID, int type) {
                ARouter.getInstance().build("/service/activity/OrderInfoActivity").withString("orderID", orderID)
                        .withInt("isBuy", type).navigation();
            }
        });
        adapter.isFirstOnly(false);
        orderHistoryRecycler.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, orderHistoryRecycler);
    }

    @Override
    protected void initData() {
        presenter = new OrderByHistoryPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getHistoryOrderListByUser(PAGE_SIZE);
    }


    @Override
    public void onLoadMoreRequested() {
        //加载更多
        presenter.getHistoryOrderListByUser(adapter.getData().size() / PAGE_SIZE + 1, PAGE_SIZE);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orderHistorySwipe != null && !orderHistorySwipe.isRefreshing())
                    orderHistorySwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orderHistorySwipe != null && orderHistorySwipe.isRefreshing())
                    orderHistorySwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshOrderView(final List<CommonOrderBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void loadMoreSure(final List<CommonOrderBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.loadMoreComplete();//加载完成
                if (list != null)
                    adapter.addData(list);
                if (list.size() < PAGE_SIZE)
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

}
