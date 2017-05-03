package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageBaseInfoAdapter;
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

    @Autowired
    boolean isAdd;

    CommonPesticideBean pesticideBean;

    private LoadingDialog dialog;

    private ManagePesticideInfoAdapter adapter;
    private IPesticideInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pesticide_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        pesticideBean = (CommonPesticideBean) getIntent().getExtras().getSerializable("pesticideBean");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("增加农药");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(PesticideInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        managePesticideRecyclerView.setLayoutManager(manager);
        adapter = new ManagePesticideInfoAdapter(PesticideInfoActivity.this, null);
        adapter.setOnPesticideItemListener(new ManagePesticideInfoAdapter.OnPesticideItemListener() {
            @Override
            public void onSelect(View view, int position, String pesID) {
                if (!TextUtils.isEmpty(pesID))
                    presenter.pestilizerCheck(pesID);
                else
                    showToast("请输入登记证号");
            }
        });
        managePesticideRecyclerView.setAdapter(adapter);
        managePesticideRecyclerView.addItemDecoration(new DividerItemDecoration(PesticideInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new PesticideInfoPresenterImpl(this);
        if (isAdd) {
            initAdd();
        } else {
            updateSure(pesticideBean);
        }

    }

    /**
     * 初始化为增加状态
     */
    private void initAdd() {
        adapter.setEdit(true);
        List<ManagePesticideEntity> list = new ArrayList<>();
        //分割线
        ManagePesticideEntity entityDivider = new ManagePesticideEntity();
        entityDivider.setItemType(ManagePesticideEntity.TYPE_DIVIDER);
        list.add(entityDivider);

        //农药登记证号
        ManagePesticideEntity entityNum = new ManagePesticideEntity();
        entityNum.setItemType(ManagePesticideEntity.TYPE_NUM);
        entityNum.setName("登记证号");
        Map<String, String> mapNum = new ArrayMap<>();
        mapNum.put("key1", pesticideBean.getPID());
        entityNum.setSubMap(mapNum);
        list.add(entityNum);

        //分割线
        list.add(entityDivider);

        //农药名称
        ManagePesticideEntity entityName = new ManagePesticideEntity();
        entityName.setItemType(ManagePesticideEntity.TYPE_NAME);
        entityName.setName("农药名称");
        Map<String, String> mapName = new ArrayMap<>();
        mapName.put("key1", pesticideBean.getRegisterName());
        entityName.setSubMap(mapName);
        list.add(entityName);

        //农药毒性
        ManagePesticideEntity entityToxicity = new ManagePesticideEntity();
        entityToxicity.setItemType(ManagePesticideEntity.TYPE_TOXICITY);
        entityToxicity.setName("农药毒性");
        Map<String, String> mapToxicity = new ArrayMap<>();
        mapToxicity.put("key1", pesticideBean.getToxicity());
        entityToxicity.setSubMap(mapToxicity);
        list.add(entityToxicity);
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
    public void updateSure(CommonPesticideBean bean) {
        pesticideBean = bean;
        adapter.setEdit(false);//不可编辑状态
        List<ManagePesticideEntity> list = new ArrayList<>();
        //分割线
        ManagePesticideEntity entityDivider = new ManagePesticideEntity();
        entityDivider.setItemType(ManagePesticideEntity.TYPE_DIVIDER);
        list.add(entityDivider);

        //农药登记证号
        ManagePesticideEntity entityNum = new ManagePesticideEntity();
        entityNum.setItemType(ManagePesticideEntity.TYPE_NUM);
        entityNum.setName("登记证号");
        Map<String, String> mapNum = new ArrayMap<>();
        mapNum.put("key1", bean.getPID());
        entityNum.setSubMap(mapNum);
        list.add(entityNum);

        //分割线
        list.add(entityDivider);

        //农药名称
        ManagePesticideEntity entityName = new ManagePesticideEntity();
        entityName.setItemType(ManagePesticideEntity.TYPE_NAME);
        entityName.setName("农药名称");
        Map<String, String> mapName = new ArrayMap<>();
        mapName.put("key1", bean.getRegisterName());
        entityName.setSubMap(mapName);
        list.add(entityName);

        //农药毒性
        ManagePesticideEntity entityToxicity = new ManagePesticideEntity();
        entityToxicity.setItemType(ManagePesticideEntity.TYPE_TOXICITY);
        entityToxicity.setName("农药毒性");
        Map<String, String> mapToxicity = new ArrayMap<>();
        mapToxicity.put("key1", bean.getToxicity());
        entityToxicity.setSubMap(mapToxicity);
        list.add(entityToxicity);

        //农药剂型
        ManagePesticideEntity entityDosage = new ManagePesticideEntity();
        entityDosage.setItemType(ManagePesticideEntity.TYPE_DOSAGE);
        entityDosage.setName("农药剂型");
        Map<String, String> mapDosage = new ArrayMap<>();
        mapDosage.put("key1", bean.getFormulations());
        entityDosage.setSubMap(mapDosage);
        list.add(entityDosage);

        //生产厂家
        ManagePesticideEntity entityFactory = new ManagePesticideEntity();
        entityFactory.setItemType(ManagePesticideEntity.TYPE_FACTORY);
        entityFactory.setName("生产厂家");
        Map<String, String> mapFactory = new ArrayMap<>();
        mapFactory.put("key1", bean.getManufacturer());
        entityFactory.setSubMap(mapFactory);
        list.add(entityFactory);

        //分割线
        list.add(entityDivider);

        //有效起始日
        ManagePesticideEntity entityStartDate = new ManagePesticideEntity();
        entityStartDate.setItemType(ManagePesticideEntity.TYPE_START_TIME);
        entityStartDate.setName("有效起始日");
        Map<String, String> mapStartDate = new ArrayMap<>();
        mapStartDate.put("key1", bean.getValidStart());
        entityStartDate.setSubMap(mapStartDate);
        list.add(entityStartDate);

        //有效截止日
        ManagePesticideEntity entityEndDate = new ManagePesticideEntity();
        entityEndDate.setItemType(ManagePesticideEntity.TYPE_END_TIME);
        entityEndDate.setName("有效截止日");
        Map<String, String> mapEndDate = new ArrayMap<>();
        mapEndDate.put("key1", bean.getValidEnd());
        entityEndDate.setSubMap(mapEndDate);
        list.add(entityEndDate);

        //分割线
        list.add(entityDivider);

        //有效成分ADD
        ManagePesticideEntity entityActiveAdd = new ManagePesticideEntity();
        entityActiveAdd.setItemType(ManagePesticideEntity.TYPE_ACTIVE_ADD);
        entityActiveAdd.setName("有效成分");
        list.add(entityActiveAdd);

        if (bean.getActives() != null)
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

        //用药指导ADD
        ManagePesticideEntity entityMethodAdd = new ManagePesticideEntity();
        entityMethodAdd.setItemType(ManagePesticideEntity.TYPE_METHOD_ADD);
        entityMethodAdd.setName("用药量信息");
        list.add(entityMethodAdd);

        if (bean.getApplys() != null)
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

        adapter.setNewData(list);
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
        getMenuInflater().inflate(R.menu.menu_manage_pesticide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_manage_pesticide_save) {
            presenter.addPesticide(pesticideBean);
        }
        return super.onOptionsItemSelected(item);
    }


}
