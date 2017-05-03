package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.presenter.IPesticideListPresenter;
import com.erayic.agr.manage.presenter.impl.PesticideListPresenterImpl;
import com.erayic.agr.manage.view.IPesticideListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/manage/fragment/PesticideListFragment", name = "农药列表")
public class PesticideListFragment extends BaseFragment implements IPesticideListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.manage_pesticide_recycler)
    RecyclerView managePesticideRecycler;
    @BindView(R2.id.manage_pesticide_swipe)
    SwipeRefreshLayout managePesticideSwipe;

    private IPesticideListPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_pesticide;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        managePesticideSwipe.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        presenter = new PesticideListPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getResourceByType();
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
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (managePesticideSwipe != null && !managePesticideSwipe.isRefreshing())
                    managePesticideSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (managePesticideSwipe != null && managePesticideSwipe.isRefreshing())
                    managePesticideSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshPersonnelView(List<Object> list) {

    }


}
