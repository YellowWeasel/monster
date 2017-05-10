package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageResourceListAdapter;
import com.erayic.agr.manage.presenter.IResourceListPresenter;
import com.erayic.agr.manage.presenter.impl.ResourceListPresenterImpl;
import com.erayic.agr.manage.view.IResourceListView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/manage/fragment/ResourceListFragment", name = "资源列表")
public class ResourceListFragment extends BaseFragment implements IResourceListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.manage_pesticide_recycler)
    RecyclerView managePesticideRecycler;
    @BindView(R2.id.manage_pesticide_swipe)
    SwipeRefreshLayout managePesticideSwipe;

    @Autowired
    int type = -1;//资源类型

    private IResourceListPresenter presenter;
    private ManageResourceListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_pesticide;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        managePesticideSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getActivity());
        manager.setScrollEnabled(true);//滑动监听
        managePesticideRecycler.setLayoutManager(manager);
        adapter = new ManageResourceListAdapter(getActivity(), null);
        adapter.setOnItemClickListener(new ManageResourceListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, CommonResourceBean bean) {
                switch (bean.getType()) {
                    case EnumResourceType.TYPE_PESTICIDE://农药
                        ARouter.getInstance().build("/manage/activity/PesticideInfoActivity")
                                .withBoolean("isAdd", false)
                                .withString("resID", bean.getResID())
                                .withString("resName", bean.getName())
                                .navigation();
                        break;
                    case EnumResourceType.TYPE_FERTILIZER://化肥
                        ARouter.getInstance().build("/manage/activity/FertilizerInfoActivity")
                                .withBoolean("isAdd", false)
                                .withString("resID", bean.getResID())
                                .withString("resName", bean.getName())
                                .navigation();
                        break;
                    case EnumResourceType.TYPE_FEED://饲料
                        break;
                    case EnumResourceType.TYPE_SEED://种子
                        ARouter.getInstance().build("/manage/activity/SeedInfoActivity")
                                .withBoolean("isAdd", false)
                                .withString("resID", bean.getResID())
                                .withString("resName", bean.getName())
                                .navigation();
                        break;
                }

            }
        });
        managePesticideRecycler.setAdapter(adapter);
        managePesticideRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData() {
        presenter = new ResourceListPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getResourceByType(type);
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
    public void refreshPersonnelView(final List<CommonResourceBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(list);
            }
        });
    }


}
