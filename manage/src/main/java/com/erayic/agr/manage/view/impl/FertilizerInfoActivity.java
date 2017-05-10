package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageFertilizerInfoAdapter;
import com.erayic.agr.manage.adapter.entity.ManageFertilizerEntity;
import com.erayic.agr.manage.presenter.IFertilizerInfoPresenter;
import com.erayic.agr.manage.presenter.impl.FertilizerInfoPresenterImpl;
import com.erayic.agr.manage.view.IFertilizerInfoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/FertilizerInfoActivity", name = "化肥详情")
public class FertilizerInfoActivity extends BaseActivity implements IFertilizerInfoView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_fertilizer_RecyclerView)
    RecyclerView manageFertilizerRecyclerView;
    @BindView(R2.id.manage_content_pid)
    EditText manageContentPid;
    @BindView(R2.id.manage_content_layout)
    LinearLayout manageContentLayout;
    @BindView(R2.id.manage_content_bt_query)
    Button manageContentBtQuery;
    @BindView(R2.id.manage_content_bt_manual)
    Button manageContentBtManual;

    @Autowired
    boolean isAdd;//增加或者查看
    @Autowired
    String resID;//资源ID
    @Autowired
    String resName;//资源名称

    private boolean isUpdater;//是否在修改状态
    private boolean isAuto;//是否查询回来的数据

    private LoadingDialog dialog;

    private IFertilizerInfoPresenter presenter;
    private ManageFertilizerInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_fertilizer_info);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("资源详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(FertilizerInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageFertilizerRecyclerView.setLayoutManager(manager);
        adapter = new ManageFertilizerInfoAdapter(FertilizerInfoActivity.this, null);

        manageFertilizerRecyclerView.setAdapter(adapter);
        manageFertilizerRecyclerView.addItemDecoration(new DividerItemDecoration(FertilizerInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new FertilizerInfoPresenterImpl(this);
        if (isAdd) {
            toolbar.setTitle("增加化肥");
            manageContentLayout.setVisibility(View.VISIBLE);
        } else {
            toolbar.setTitle(resName);
            manageContentLayout.setVisibility(View.GONE);
            presenter.getSpecifyResources(resID, EnumResourceType.TYPE_FERTILIZER);
        }
    }

    @OnClick(R2.id.manage_content_bt_query)
    public void onManageContentBtQueryClicked() {//查询输入
        if (!TextUtils.isEmpty(manageContentPid.getText().toString())) {
            isAuto = true;
            presenter.fertilizerCheck(manageContentPid.getText().toString());
        } else {
            showToast("请输入登记证号");
        }
    }

    @OnClick(R2.id.manage_content_bt_manual)
    public void onManageContentBtManualClicked() {//手动输入
        isAuto = false;
        CommonFertilizerBean bean = new CommonFertilizerBean();
        List<ManageFertilizerEntity> list = new ArrayList<>();
        //分割线
        ManageFertilizerEntity entityDivider = new ManageFertilizerEntity();
        entityDivider.setItemType(ManageFertilizerEntity.TYPE_DIVIDER);
        list.add(entityDivider);

        //产品输入名称
        {
            ManageFertilizerEntity entity = new ManageFertilizerEntity();
            entity.setItemType(ManageFertilizerEntity.TYPE_IMPORT_NAME);
            Map<String, String> map = new ArrayMap<>();
            map.put("key1", "");
            entity.setSubMap(map);
            list.add(entity);
        }
        //生产厂家
        {
            ManageFertilizerEntity entity = new ManageFertilizerEntity();
            entity.setItemType(ManageFertilizerEntity.TYPE_IMPORT_MANUFACTURER);
            Map<String, String> map = new ArrayMap<>();
            map.put("key1", bean.getManufacturer());
            entity.setSubMap(map);
            list.add(entity);
        }
        adapter.setNewData(list);
        adapter.setBean(bean);
    }


    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(FertilizerInfoActivity.this);
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
                    dialog = new LoadingDialog(FertilizerInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure(final CommonFertilizerBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageFertilizerEntity> list = new ArrayList<>();
                //分割线
                ManageFertilizerEntity entityDivider = new ManageFertilizerEntity();
                entityDivider.setItemType(ManageFertilizerEntity.TYPE_DIVIDER);
                list.add(entityDivider);

                //产品PID
                if (bean.getPID() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_PID);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getPID());
                    entity.setSubMap(map);
                    list.add(entity);
                }

                //产品通用名称
                if (bean.getCommonName() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_COMMON_NAME);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getCommonName());
                    entity.setSubMap(map);
                    list.add(entity);
                }

                //产品商家名称
                if (bean.getProductName() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_PRODUCT_NAME);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getProductName());
                    entity.setSubMap(map);
                    list.add(entity);
                }

                //产品生产厂家
                if (bean.getManufacturer() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_MANUFACTURER);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getManufacturer());
                    entity.setSubMap(map);
                    list.add(entity);
                }

                //产品适宜作物
                if (bean.getCrops() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_CROPS);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getCrops());
                    entity.setSubMap(map);
                    list.add(entity);
                }

                //产品技术指标
                if (bean.getNorm() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_NORM);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getNorm());
                    entity.setSubMap(map);
                    list.add(entity);
                }

                //产品形态
                if (bean.getShape() != null) {
                    ManageFertilizerEntity entity = new ManageFertilizerEntity();
                    entity.setItemType(ManageFertilizerEntity.TYPE_SHAPE);
                    Map<String, String> map = new ArrayMap<>();
                    map.put("key1", bean.getShape());
                    entity.setSubMap(map);
                    list.add(entity);
                }
                adapter.setNewData(list);
                adapter.setBean(bean);
            }
        });

    }

    @Override
    public void saveSure() {
        ErayicStack.getInstance().finishCurrentActivity();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_resource, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem save = menu.findItem(R.id.action_manage_resource_save);
        MenuItem modify = menu.findItem(R.id.action_manage_resource_update);
        MenuItem delete = menu.findItem(R.id.action_manage_resource_delete);
        if (isAdd) {
            save.setVisible(true);
            modify.setVisible(false);
            delete.setVisible(false);
        } else {
            if (isUpdater) {
                isUpdater = false;
                save.setVisible(true);
                modify.setVisible(true);
                modify.setTitle("取消编辑");
                delete.setVisible(false);
                manageContentLayout.setVisibility(View.VISIBLE);
            } else {
                isUpdater = true;
                save.setVisible(false);
                modify.setVisible(true);
                modify.setTitle("编辑");
                delete.setVisible(true);
                manageContentLayout.setVisibility(View.GONE);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_resource_save) {
            if (!TextUtils.isEmpty(adapter.getBean().getProductName())) {
                if (isAuto)
                    presenter.saveFertilizer(adapter.getBean());
                else
                    presenter.saveFertilizerByUserDefine(adapter.getBean());

            } else {
                showToast("请完善信息");
            }
        } else if (item.getItemId() == R.id.action_manage_resource_update) {
            if (adapter.getBean() != null && !adapter.getBean().isReadOnly()) {
                supportInvalidateOptionsMenu();
            } else {
                showToast("不可编辑");
            }
        } else if (item.getItemId() == R.id.action_manage_resource_delete) {
            presenter.deleteResource(resID, EnumResourceType.TYPE_FERTILIZER);
        }
        return super.onOptionsItemSelected(item);
    }


}
