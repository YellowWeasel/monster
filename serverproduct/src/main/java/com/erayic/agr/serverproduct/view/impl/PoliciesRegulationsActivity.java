package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.PoliciesRegulationsAdapter;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsTitleDatas;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.presenter.impl.PoliciesRegulationsPresenterImpl;
import com.erayic.agr.serverproduct.view.IPoliciesRegulartionsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wxk on 2017/5/8.
 */
@Route(path = "/serverproduct/activity/PoliciesRegulationsActivity", name = "政策法规")
public class PoliciesRegulationsActivity extends BaseActivity implements IPoliciesRegulartionsView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R2.id.serverproduct_polices_regulations_title_textview)
    TextView titleTextView;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    PoliciesRegulationsAdapter adapter;
    IPoliciesRegulationsPresenter presenter;
    List<PoliciesRegulationsTitleDatas> titleDatas;
    int pageIndex=1;
    int pageSize=15;
    @BindView(R2.id.serverproduct_policies_regulations_recycler)
    RecyclerView serverproductPoliciesRegulationsRecycler;
    @BindView(R2.id.serverproduct_policies_regulations_swipe)
    SwipeRefreshLayout serverproductPoliciesRegulationsSwipe;
    int showType=0;
    List<PoliciesRegulationsTitleDatas> policiesRegulationsTitleDatasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies_regulations);
//        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("政策法规");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        serverproductPoliciesRegulationsSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        serverproductPoliciesRegulationsRecycler.setLayoutManager(manager);
        serverproductPoliciesRegulationsRecycler.addItemDecoration(new PoliciesRegulationsAdapter.SpaceItemDecoration(5));
        adapter=new PoliciesRegulationsAdapter(null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setRegulationsItemClickListener(new PoliciesRegulationsAdapter.PoliciesRegulationsItemClickListener() {
            @Override
            public void doOnClick(View view, int Id) {
                ARouter.getInstance().build("/serverproduct/activity/PoliciesRegulationsDetailActivity").withInt("Id",Id).navigation();
            }
        });
        adapter.isFirstOnly(false);
        serverproductPoliciesRegulationsRecycler.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this,serverproductPoliciesRegulationsRecycler);
    }

    @Override
    public void initData() {
        presenter = new PoliciesRegulationsPresenterImpl(this);
        presenter.initPoliciesRegulationsDatas(pageSize);
    }

    @Override
    public void refreshPoliciesRegulartionsDataView(List<PoliciesRegulationsDetailDatas> mBeans) {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serverproductPoliciesRegulationsSwipe != null && !serverproductPoliciesRegulationsSwipe.isRefreshing())
                    serverproductPoliciesRegulationsSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (serverproductPoliciesRegulationsSwipe != null && serverproductPoliciesRegulationsSwipe.isRefreshing())
                    serverproductPoliciesRegulationsSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshPoliciesRegulartionsView(List<PoliciesRegulationsTitleDatas> list) {
                 policiesRegulationsTitleDatasList=list;
                 adapter.setNewData(presenter.sortPoliciesRegulationsDatasByProvince("海南省",policiesRegulationsTitleDatasList,showType));
    }

    @Override
    public void loadMoreSure(final  List<PoliciesRegulationsTitleDatas> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.loadMoreComplete();//加载完成
                if (list != null)
                    if (policiesRegulationsTitleDatasList==null){
                        policiesRegulationsTitleDatasList=new ArrayList<>();
                    }
                    policiesRegulationsTitleDatasList.addAll(list);
                    adapter.addData(presenter.sortPoliciesRegulationsDatasByProvince("海南省",list,showType));
                if (list.size() < pageSize)
                    adapter.loadMoreEnd();
            }
        });
    }

    @Override
    public void loadMoreFailure() {
        adapter.loadMoreFail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_serverproduct_policiesregulation, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.serverproduct_policies_province) {
                showType=1;
        } else if (item.getItemId() == R.id.serverproduct_policies_national) {
                showType=2;
        }else if (item.getItemId() == R.id.serverproduct_policies_all) {
                showType=0;
        }


        adapter.setNewData(presenter.
                sortPoliciesRegulationsDatasByProvince("海南省",
                        policiesRegulationsTitleDatasList,showType));
        adapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        presenter.initPoliciesRegulationsDatas(pageSize);
    }


    @Override
    public void onLoadMoreRequested() {
        presenter.getPoliciesRegulationsDatas(adapter.getData().size()/pageSize+1,pageSize);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void showToast(String msg) {

    }
}
