package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.KeyListener;
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
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManagePesticideInfoAdapter;
import com.erayic.agr.manage.adapter.entity.ManagePesticideEntity;
import com.erayic.agr.manage.presenter.IPesticideInfoPresenter;
import com.erayic.agr.manage.presenter.impl.PesticideInfoPresenterImpl;
import com.erayic.agr.manage.view.IPesticideInfoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/manage/activity/PesticideInfoActivity", name = "农药详情页面")
public class PesticideInfoActivity extends BaseActivity implements IPesticideInfoView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.manage_pesticide_RecyclerView)
    RecyclerView managePesticideRecyclerView;
    @BindView(R2.id.manage_content_pid)
    EditText manageContentPid;
    @BindView(R2.id.manage_content_bt_query)
    Button manageContentBtQuery;
    @BindView(R2.id.manage_content_bt_manual)
    Button manageContentBtManual;
    @BindView(R2.id.manage_content_layout)
    LinearLayout manageContentLayout;

    @Autowired
    boolean isAdd;//增加或者查看
    @Autowired
    String resID;//资源ID
    @Autowired
    String resName;//资源名称

    private boolean isUpdater;//是否在修改状态
    private KeyListener keyListener;

    private LoadingDialog dialog;

    private ManagePesticideInfoAdapter adapter;
    private IPesticideInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pesticide_info);
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
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(PesticideInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        managePesticideRecyclerView.setLayoutManager(manager);
        adapter = new ManagePesticideInfoAdapter(PesticideInfoActivity.this, null);

        managePesticideRecyclerView.setAdapter(adapter);
        managePesticideRecyclerView.addItemDecoration(new DividerItemDecoration(PesticideInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
        keyListener = manageContentPid.getKeyListener();
    }

    @Override
    public void initData() {
        presenter = new PesticideInfoPresenterImpl(this);
        if (isAdd) {
            toolbar.setTitle("增加农药");
            manageContentLayout.setVisibility(View.VISIBLE);
        } else {
            toolbar.setTitle(resName);
            manageContentLayout.setVisibility(View.GONE);
            presenter.getSpecifyResources(resID, EnumResourceType.TYPE_PESTICIDE);
        }

    }


    @OnClick(R2.id.manage_content_bt_query)
    public void onManageContentBtQueryClicked() {
        if (!TextUtils.isEmpty(manageContentPid.getText().toString())) {
            presenter.pestilizerCheck(manageContentPid.getText().toString());
        } else {
            showToast("请输入登记证号");
        }
    }

    @OnClick(R2.id.manage_content_bt_manual)
    public void onManageContentBtManualClicked() {
        initAdd();
    }

    /**
     * 初始化为增加状态
     */
    private void initAdd() {
        CommonPesticideBean bean = new CommonPesticideBean();
        List<ManagePesticideEntity> list = new ArrayList<>();
        //分割线
        ManagePesticideEntity entityDivider = new ManagePesticideEntity();
        entityDivider.setItemType(ManagePesticideEntity.TYPE_DIVIDER);
        list.add(entityDivider);

        //农药名称
        {
            ManagePesticideEntity entity = new ManagePesticideEntity();
            entity.setItemType(ManagePesticideEntity.TYPE_IMPORT_NAME);
            entity.setName("农药名称");
            Map<String, String> map = new ArrayMap<>();
            map.put("key1", "");
            entity.setSubMap(map);
            list.add(entity);
        }
        //农药毒性
        {
            ManagePesticideEntity entity = new ManagePesticideEntity();
            entity.setItemType(ManagePesticideEntity.TYPE_IMPORT_TOXICITY);
            entity.setName("农药毒性");
            Map<String, String> map = new ArrayMap<>();
            map.put("key1", "");
            entity.setSubMap(map);
            list.add(entity);
        }

        //生产厂家
        {
            ManagePesticideEntity entity = new ManagePesticideEntity();
            entity.setItemType(ManagePesticideEntity.TYPE_IMPORT_FACTORY);
            entity.setName("生产厂家");
            Map<String, String> map = new ArrayMap<>();
            map.put("key1", "");
            entity.setSubMap(map);
            list.add(entity);
        }
        adapter.setKeyListener(keyListener);
        adapter.setBean(bean);
        adapter.setNewData(list);
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(PesticideInfoActivity.this);
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
                    dialog = new LoadingDialog(PesticideInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure(final CommonPesticideBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManagePesticideEntity> list = new ArrayList<>();
                //分割线
                ManagePesticideEntity entityDivider = new ManagePesticideEntity();
                entityDivider.setItemType(ManagePesticideEntity.TYPE_DIVIDER);
                list.add(entityDivider);

                if (bean.isReadOnly()) {
                    //农药登记证号
                    if (bean.getPID() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_NUM);
                        entity.setName("登记证号");
                        Map<String, String> mapNum = new ArrayMap<>();
                        mapNum.put("key1", bean.getPID());
                        entity.setSubMap(mapNum);
                        list.add(entity);
                        //分割线
                        list.add(entityDivider);
                    }

                    //农药名称
                    if (bean.getRegisterName() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_NAME);
                        entity.setName("农药名称");
                        Map<String, String> mapName = new ArrayMap<>();
                        mapName.put("key1", bean.getRegisterName());
                        entity.setSubMap(mapName);
                        list.add(entity);
                    }

                    //农药毒性
                    if (bean.getToxicity() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_TOXICITY);
                        entity.setName("农药毒性");
                        Map<String, String> mapToxicity = new ArrayMap<>();
                        mapToxicity.put("key1", bean.getToxicity());
                        entity.setSubMap(mapToxicity);
                        list.add(entity);
                    }

                    //农药剂型
                    if (bean.getFormulations() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_DOSAGE);
                        entity.setName("农药剂型");
                        Map<String, String> mapDosage = new ArrayMap<>();
                        mapDosage.put("key1", bean.getFormulations());
                        entity.setSubMap(mapDosage);
                        list.add(entity);
                    }

                    //生产厂家
                    if (bean.getManufacturer() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_FACTORY);
                        entity.setName("生产厂家");
                        Map<String, String> mapFactory = new ArrayMap<>();
                        mapFactory.put("key1", bean.getManufacturer());
                        entity.setSubMap(mapFactory);
                        list.add(entity);
                        //分割线
                        list.add(entityDivider);
                    }

                    //有效起始日
                    if (bean.getValidStart() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_START_TIME);
                        entity.setName("有效起始日");
                        Map<String, String> mapStartDate = new ArrayMap<>();
                        mapStartDate.put("key1", bean.getValidStart());
                        entity.setSubMap(mapStartDate);
                        list.add(entity);
                    }

                    //有效截止日
                    if (bean.getValidEnd() != null) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_END_TIME);
                        entity.setName("有效截止日");
                        Map<String, String> mapEndDate = new ArrayMap<>();
                        mapEndDate.put("key1", bean.getValidEnd());
                        entity.setSubMap(mapEndDate);
                        list.add(entity);
                        //分割线
                        list.add(entityDivider);
                    }

                    //有效成分ADD
                    if (bean.getActives() != null && bean.getActives().size() > 0) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_ACTIVE_ADD);
                        entity.setName("有效成分");
                        list.add(entity);
                        for (CommonPesticideBean.ActivesInfo activesInfo : bean.getActives()) {
                            ManagePesticideEntity entityActive = new ManagePesticideEntity();
                            entityActive.setItemType(ManagePesticideEntity.TYPE_ACTIVE);
                            entityActive.setName("有效成分");
                            Map<String, String> mapActive = new ArrayMap<>();
                            mapActive.put("key1", activesInfo.getActiveName());
                            mapActive.put("key2", activesInfo.getContent());
                            entityActive.setSubMap(mapActive);
                            list.add(entityActive);
                        }

                        //分割线
                        list.add(entityDivider);
                    }

                    //用药指导ADD
                    if (bean.getApplys() != null && bean.getApplys().size() > 0) {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_METHOD_ADD);
                        entity.setName("用药量信息");
                        list.add(entity);

                        for (CommonPesticideBean.ApplysInfo applysInfo : bean.getApplys()) {
                            ManagePesticideEntity entityApply = new ManagePesticideEntity();
                            entityApply.setItemType(ManagePesticideEntity.TYPE_METHOD);
                            entityApply.setName("有效成分");
                            Map<String, String> mapApply = new ArrayMap<>();
                            mapApply.put("key1", applysInfo.getCrop());
                            mapApply.put("key2", applysInfo.getPrevention());
                            mapApply.put("key3", applysInfo.getDosage());
                            mapApply.put("key4", applysInfo.getMethod());
                            entityApply.setSubMap(mapApply);
                            list.add(entityApply);
                        }
                    }
                } else {
                    //农药名称
                    {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_IMPORT_NAME);
                        entity.setName("农药名称");
                        Map<String, String> map = new ArrayMap<>();
                        map.put("key1", bean.getRegisterName() == null ? "" : bean.getRegisterName());
                        entity.setSubMap(map);
                        list.add(entity);
                    }
                    //农药毒性
                    {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_IMPORT_TOXICITY);
                        entity.setName("农药毒性");
                        Map<String, String> map = new ArrayMap<>();
                        map.put("key1", bean.getToxicity() == null ? "" : bean.getToxicity());
                        entity.setSubMap(map);
                        list.add(entity);
                    }

                    //生产厂家
                    {
                        ManagePesticideEntity entity = new ManagePesticideEntity();
                        entity.setItemType(ManagePesticideEntity.TYPE_IMPORT_FACTORY);
                        entity.setName("生产厂家");
                        Map<String, String> map = new ArrayMap<>();
                        map.put("key1", bean.getManufacturer() == null ? "" : bean.getManufacturer());
                        entity.setSubMap(map);
                        list.add(entity);
                    }
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
                adapter.setKeyListener(keyListener);
                adapter.notifyDataSetChanged();
                save.setVisible(true);
                modify.setVisible(true);
                modify.setTitle("取消编辑");
                delete.setVisible(false);
            } else {
                isUpdater = true;
                adapter.setKeyListener(null);
                adapter.notifyDataSetChanged();
                save.setVisible(false);
                modify.setVisible(true);
                modify.setTitle("编辑");
                delete.setVisible(true);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_resource_save) {
            if (!TextUtils.isEmpty(adapter.getBean().getRegisterName())) {
                if (adapter.getBean().isReadOnly())
                    presenter.savePesticide(adapter.getBean());
                else
                    presenter.savePesticideByUserDefine(adapter.getBean());

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
            presenter.deleteResource(resID, EnumResourceType.TYPE_PESTICIDE);
        }
        return super.onOptionsItemSelected(item);
    }

}
