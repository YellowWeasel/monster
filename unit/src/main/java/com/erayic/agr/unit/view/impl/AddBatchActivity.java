package com.erayic.agr.unit.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.enums.EnumRequestType;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.codbking.DatePickDialog;
import com.erayic.agr.common.view.codbking.OnSureLisener;
import com.erayic.agr.common.view.codbking.bean.DateType;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitAddBatchItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitAddBatchItemEntity;
import com.erayic.agr.unit.presenter.IAddBatchPresenter;
import com.erayic.agr.unit.presenter.impl.AddBatchPresenterImpl;
import com.erayic.agr.unit.view.IAddBatchView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/AddBatchActivity", name = "增加批次")
public class AddBatchActivity extends BaseActivity implements IAddBatchView {

    private final static int ACTIVITY_REQUEST = 9000;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.unit_batch_add_recycler)
    RecyclerView unitBatchAddRecycler;

    @Autowired
    String unitID;//管理单元ID

    private UnitAddBatchItemAdapter adapter;
    private IAddBatchPresenter presenter;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_add);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("增加批次");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(AddBatchActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        unitBatchAddRecycler.setLayoutManager(manager);
        adapter = new UnitAddBatchItemAdapter(AddBatchActivity.this, null);
        adapter.setOnBatchClickListener(new UnitAddBatchItemAdapter.OnBatchItemClickListener() {
            @Override
            public void onClick(View view, UnitAddBatchItemEntity entity, final int position) {
                switch (entity.getItemType()) {
                    case UnitAddBatchItemEntity.TYPE_PRODUCT://产品
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_PRODUCT)
                                .navigation(AddBatchActivity.this, ACTIVITY_REQUEST);
                        break;
                    case UnitAddBatchItemEntity.TYPE_SEED://种苗
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_SEED)
                                .navigation(AddBatchActivity.this, ACTIVITY_REQUEST);
                        break;
                    case UnitAddBatchItemEntity.TYPE_AREA://面积
                        new ErayicEditDialog.Builder(AddBatchActivity.this)
                                .setMessage(TextUtils.isEmpty(entity.getSubName()) ? "" : entity.getSubName(), null)
                                .setTitle("设置种植面积（亩）")
                                .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                                    @Override
                                    public void onClick(Dialog dialog, int which, CharSequence s) {
                                        dialog.dismiss();
                                    }
                                })
                                .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                                    @Override
                                    public void onClick(Dialog dialog, int which, CharSequence s) {
                                        if (!TextUtils.isEmpty(s)) {
                                            double doubleBuffer;
                                            try {
                                                doubleBuffer = Double.valueOf(s.toString()).doubleValue();
                                            } catch (Exception e) {
                                                showToast("类型错误，请输入字符");
                                                return;
                                            }
                                            adapter.getData().get(position).setSubName(s.toString());
                                            adapter.getData().get(position).setValue(s.toString());
                                            adapter.notifyItemChanged(position);
                                        } else {
                                            showToast("不能为空");
                                        }
                                        dialog.dismiss();
                                    }
                                }).show();
                        break;
                    case UnitAddBatchItemEntity.TYPE_DATE://时间
                        DatePickDialog dialog = new DatePickDialog(AddBatchActivity.this);
                        //设置上下年分限制
                        dialog.setYearLimt(5);
                        //设置标题
                        dialog.setTitle("选择时间");
                        //设置类型
                        dialog.setType(DateType.TYPE_YMD);
                        //设置消息体的显示格式，日期格式
                        dialog.setMessageFormat("yyyy-MM-dd");
                        //设置选择回调
                        dialog.setOnChangeLisener(null);
                        //设置点击确定按钮回调
                        dialog.setOnSureLisener(new OnSureLisener() {
                            @Override
                            public void onSure(Date date) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String ctime = format.format(date);
                                adapter.getData().get(position).setSubName(ctime);
                                adapter.getData().get(position).setValue(ctime);
                                adapter.notifyItemChanged(position);
                            }
                        });
                        dialog.show();
                        break;
                    case UnitAddBatchItemEntity.TYPE_PERSONNEL://种植人员
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_USER)
                                .navigation(AddBatchActivity.this, ACTIVITY_REQUEST);
                        break;
                }
            }
        });
        unitBatchAddRecycler.setAdapter(adapter);
        unitBatchAddRecycler.addItemDecoration(new DividerItemDecoration(AddBatchActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new AddBatchPresenterImpl(this);
        List<UnitAddBatchItemEntity> list = new ArrayList<>();

        //产品
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_PRODUCT);
            list.add(entity);
        }

        //种苗
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_SEED);
            list.add(entity);
        }

        //面积
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_AREA);
            list.add(entity);
        }

        //时间
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_DATE);
            list.add(entity);
        }

        //人员
        {
            UnitAddBatchItemEntity entity = new UnitAddBatchItemEntity();
            entity.setItemType(UnitAddBatchItemEntity.TYPE_PERSONNEL);
            list.add(entity);
        }
        adapter.setNewData(list);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST) {
            switch (resultCode) {
                case EnumRequestType.TYPE_RETURN_PRODUCT: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        UnitAddBatchItemEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == UnitAddBatchItemEntity.TYPE_PRODUCT) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                entity.setValue(entry.getKey());
                                entity.setSubName(entry.getValue().toString());
                                adapter.setData(i, entity);
                            }
                        }
                    }
                }
                break;
                case EnumRequestType.TYPE_RETURN_SEED: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        UnitAddBatchItemEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == UnitAddBatchItemEntity.TYPE_SEED) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                entity.setValue(entry.getKey());
                                entity.setSubName(entry.getValue().toString());
                                adapter.setData(i, entity);
                            }
                        }
                    }

                }
                break;
                case EnumRequestType.TYPE_RETURN_USER: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        UnitAddBatchItemEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == UnitAddBatchItemEntity.TYPE_PERSONNEL) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                entity.setValue(entry.getKey());
                                entity.setSubName(entry.getValue().toString());
                                adapter.setData(i, entity);
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        if (item.getItemId() == R.id.action_unit_batch_save) {//保存
            String proID = "", seedID = "", seedName = "", quantity = "", stTime = "", ope = "";
            for (UnitAddBatchItemEntity entity : adapter.getData()) {
                switch (entity.getItemType()) {
                    case UnitAddBatchItemEntity.TYPE_PRODUCT:
                        if (TextUtils.isEmpty(entity.getValue())) {
                            showToast("请选择产品");
                            return super.onOptionsItemSelected(item);
                        } else {
                            proID = entity.getValue();
                        }
                        break;
                    case UnitAddBatchItemEntity.TYPE_SEED:
                        if (TextUtils.isEmpty(entity.getValue())) {
                            showToast("请选择种苗");
                            return super.onOptionsItemSelected(item);
                        } else {
                            seedID = entity.getValue();
                            seedName = entity.getSubName();
                        }
                        break;
                    case UnitAddBatchItemEntity.TYPE_AREA:
                        if (TextUtils.isEmpty(entity.getValue())) {
                            showToast("请输入种植面积");
                            return super.onOptionsItemSelected(item);
                        } else {
                            quantity = entity.getValue();
                        }
                        break;
                    case UnitAddBatchItemEntity.TYPE_DATE:
                        if (TextUtils.isEmpty(entity.getValue())) {
                            showToast("请输入种植时间");
                            return super.onOptionsItemSelected(item);
                        } else {
                            stTime = entity.getValue();
                        }
                        break;
                    case UnitAddBatchItemEntity.TYPE_PERSONNEL:
                        if (TextUtils.isEmpty(entity.getValue())) {
                            showToast("请选择种植人员");
                            return super.onOptionsItemSelected(item);
                        } else {
                            ope = entity.getValue();
                        }
                        break;
                }
            }
            presenter.createBatch(proID, seedID, seedName, quantity, stTime, ope, unitID);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.unit_batch_add, menu);
        return true;
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(AddBatchActivity.this);
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
                    dialog = new LoadingDialog(AddBatchActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void addBatchSure() {
        AddBatchActivity.this.finish();
    }
}
