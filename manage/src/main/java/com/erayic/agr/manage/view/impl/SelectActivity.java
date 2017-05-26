package com.erayic.agr.manage.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.enums.EnumRequestType;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.net.back.enums.EnumTipType;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageSelectAdapter;
import com.erayic.agr.manage.adapter.entity.ManageNoticeEntity;
import com.erayic.agr.manage.adapter.entity.ManageSelectEntity;
import com.erayic.agr.manage.presenter.ISelectPresenter;
import com.erayic.agr.manage.presenter.impl.SelectPresenterImpl;
import com.erayic.agr.manage.view.ISelectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：单选，多选 （农药、化肥、种苗、产品、用户）
 */

@Route(path = "/manage/activity/SelectActivity", name = "单选，多选 （农药、化肥、种苗、产品、用户）")
public class SelectActivity extends BaseActivity implements ISelectView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_produce_RecyclerView)
    RecyclerView manageRecyclerView;
    @BindView(R2.id.manage_produce_swipe)
    SwipeRefreshLayout manageProduceSwipe;

    @Autowired
    boolean isRadio;//是否单选

    @Autowired
    int selectType = 0;

    private ISelectPresenter presenter;
    private ManageSelectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_produce_list);
        ButterKnife.bind(this);
    }


    @Override
    public void initView() {
        toolbar.setTitle("选择");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageProduceSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(SelectActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageRecyclerView.setLayoutManager(manager);
        adapter = new ManageSelectAdapter(SelectActivity.this, null, isRadio);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                CommonPersonnelBean bean = (CommonPersonnelBean) adapter.getData().get(position);
//                bean.setCheck(!bean.isCheck());
//                adapter.setData(position, bean);
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                return true;
            }
        });
        manageRecyclerView.setAdapter(adapter);
        manageRecyclerView.addItemDecoration(new DividerItemDecoration(SelectActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        toolbar.setTitle(EnumRequestType.getTypeDes(selectType));

        presenter = new SelectPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        switch (selectType) {
            case EnumRequestType.TYPE_RETURN_PRODUCT:
                presenter.getAllProduct();
                break;
            case EnumRequestType.TYPE_RETURN_USER:
                presenter.getAllUserByBase(PreferenceUtils.getParam("ActiveBaseID"));
                break;
            case EnumRequestType.TYPE_RETURN_FERTILIZER:
                presenter.getResourceByType(EnumResourceType.TYPE_FERTILIZER);
                break;
            case EnumRequestType.TYPE_RETURN_PESTICIDE:
                presenter.getResourceByType(EnumResourceType.TYPE_PESTICIDE);
                break;
            case EnumRequestType.TYPE_RETURN_SEED:
                presenter.getResourceByType(EnumResourceType.TYPE_SEED);
                break;
            case EnumRequestType.TYPE_RETURN_WORK:
                presenter.getJobList();
                break;
            case EnumRequestType.TYPE_RETURN_UNIT:
                presenter.getUnitList();
                break;
            case EnumRequestType.TYPE_RETURN_NOTICE:
                presenter.getNoticeList();
                break;
        }

    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageProduceSwipe != null && !manageProduceSwipe.isRefreshing())
                    manageProduceSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageProduceSwipe != null && manageProduceSwipe.isRefreshing())
                    manageProduceSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshProductView(final List<CommonProduceListBean> list) {

        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageSelectEntity> listProduct = new ArrayList<>();
                for (CommonProduceListBean bean : list) {
                    ManageSelectEntity entity = new ManageSelectEntity();
                    entity.setItemType(ManageSelectEntity.TYPE_SELECT_PRODUCT);
                    entity.setIcon(bean.getIcon());
                    entity.setID(bean.getProID());
                    entity.setName(bean.getProductName());
                    entity.setSubName(bean.getClassifyName());
                    listProduct.add(entity);
                }
                adapter.setNewData(listProduct);
            }
        });
    }

    @Override
    public void refreshResourceView(final List<CommonResourceBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageSelectEntity> listResource = new ArrayList<>();
                for (CommonResourceBean bean : list) {
                    ManageSelectEntity entity = new ManageSelectEntity();
                    switch (selectType) {
                        case EnumRequestType.TYPE_RETURN_FERTILIZER:
                            entity.setItemType(ManageSelectEntity.TYPE_SELECT_FERTILIZER);
                            break;
                        case EnumRequestType.TYPE_RETURN_PESTICIDE:
                            entity.setItemType(ManageSelectEntity.TYPE_SELECT_PESTICIDE);
                            break;
                        case EnumRequestType.TYPE_RETURN_SEED:
                            entity.setItemType(ManageSelectEntity.TYPE_SELECT_SEED);
                    }
//                    entity.setIcon(bean.getIcon());
                    entity.setID(bean.getResID());
                    entity.setName(bean.getName());
                    entity.setSubName(bean.getCommonDesc());
                    listResource.add(entity);
                }
                adapter.setNewData(listResource);
            }
        });
    }

    @Override
    public void refreshUserView(final List<CommonPersonnelBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageSelectEntity> listResource = new ArrayList<>();
                for (CommonPersonnelBean bean : list) {
                    ManageSelectEntity entity = new ManageSelectEntity();
                    entity.setItemType(ManageSelectEntity.TYPE_SELECT_USER);
                    entity.setIcon(bean.getIcon());
                    entity.setID(bean.getUserID());
                    entity.setName(bean.getName());
                    entity.setSubName(EnumUserRole.getRoleDes(bean.getRole()));
                    listResource.add(entity);
                }
                adapter.setNewData(listResource);
            }
        });
    }

    @Override
    public void refreshWorkView(final List<CommonWorkListBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageSelectEntity> listResource = new ArrayList<>();
                for (CommonWorkListBean bean : list) {
                    ManageSelectEntity entity = new ManageSelectEntity();
                    entity.setItemType(ManageSelectEntity.TYPE_SELECT_USER);
                    entity.setID(bean.getJobID());
                    entity.setName(bean.getJobName());
                    entity.setSubName("");
                    listResource.add(entity);
                }
                adapter.setNewData(listResource);
            }
        });
    }

    @Override
    public void refreshUnitView(final List<CommonUnitListByBaseBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageSelectEntity> listResource = new ArrayList<>();
                for (CommonUnitListByBaseBean bean : list) {
                    ManageSelectEntity entity = new ManageSelectEntity();
                    entity.setItemType(ManageSelectEntity.TYPE_SELECT_USER);
                    entity.setID(bean.getUnitID());
                    entity.setName(bean.getName());
                    if (bean.getWorkers() != null && bean.getWorkers().size() > 0) {
                        String s = "";
                        for (CommonUnitListByBaseBean.WorkersInfo workersInfo : bean.getWorkers()) {
                            s += workersInfo.getValue() + " ";
                        }
                        entity.setSubName(s);
                    }
                    listResource.add(entity);
                }
                adapter.setNewData(listResource);
            }
        });
    }

    @Override
    public void refreshNoticeView(final List<ManageNoticeEntity> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageSelectEntity> listResource = new ArrayList<>();
                for (ManageNoticeEntity bean : list) {
                    ManageSelectEntity entity = new ManageSelectEntity();
                    entity.setItemType(ManageSelectEntity.TYPE_SELECT_USER);
                    entity.setID(bean.getType());
                    entity.setName(bean.getName());
                    entity.setSubName("");
                    listResource.add(entity);
                }
                adapter.setNewData(listResource);
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_sure) {
            HashMap<String, Object> map = new HashMap<>();
            switch (selectType) {
                case EnumRequestType.TYPE_RETURN_NOTICE://通知
                    int id = 0;
                    for (ManageSelectEntity entity : adapter.getData()) {
                        if (entity.isCheck()) {
                            if (entity.getID() instanceof Integer) {
                                int idBuffer = Integer.valueOf(entity.getID().toString());
                                if (idBuffer == 0) {
                                    id = 0;
                                    break;
                                } else {
                                    id += idBuffer;
                                }
                            }
                        }
                    }
                    map.put(id + "", EnumTipType.getTypeDes(id));
                    if (map.size() == 0) {
                        showToast("您还未选择");
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("map", map);
                        setResult(selectType, intent);
                        SelectActivity.this.finish();
                    }
                    break;
                default:
                    for (ManageSelectEntity entity : adapter.getData()) {
                        if (entity.isCheck()) {
                            map.put(entity.getID().toString(), entity.getName());
                        }
                    }
                    if (map.size() == 0) {
                        showToast("您还未选择");
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("map", map);
                        setResult(selectType, intent);
                        SelectActivity.this.finish();
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_sure, menu);
        return true;
    }

}
