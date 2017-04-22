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
import com.erayic.agr.service.adapter.OrderByPayingAdapter;
import com.erayic.agr.service.presenter.IOrderByPayingPresenter;
import com.erayic.agr.service.presenter.impl.OrderByPayingPresenterImpl;
import com.erayic.agr.service.view.IOrderByPayingView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/fragment/OrderByPayingFragment", name = "待支付")
public class OrderByPayingFragment extends BaseFragment implements IOrderByPayingView, SwipeRefreshLayout.OnRefreshListener {

    @Autowired
    String titleName;
    @BindView(R2.id.order_paying_recycler)
    RecyclerView orderPayingRecycler;
    @BindView(R2.id.order_paying_swipe)
    SwipeRefreshLayout orderPayingSwipe;

    private OrderByPayingAdapter adapter;
    private IOrderByPayingPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_paying;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        orderPayingSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getActivity());
        manager.setScrollEnabled(true);//滑动监听
        orderPayingRecycler.setLayoutManager(manager);
        adapter = new OrderByPayingAdapter(getActivity(), null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnOrderClickListener(new OrderByPayingAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(View view, String orderID, int type) {
                ARouter.getInstance().build("/service/activity/OrderInfoActivity").withString("orderID", orderID)
                        .withInt("isBuy", type).navigation();
            }
        });
        adapter.isFirstOnly(false);
        orderPayingRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter = new OrderByPayingPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getUnPayOrderListByUser();
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orderPayingSwipe != null && !orderPayingSwipe.isRefreshing())
                    orderPayingSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orderPayingSwipe != null && orderPayingSwipe.isRefreshing())
                    orderPayingSwipe.setRefreshing(false);
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
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity().getApplicationContext(), msg);
            }
        });
    }


}
