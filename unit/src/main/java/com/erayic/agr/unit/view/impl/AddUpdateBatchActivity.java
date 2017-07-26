package com.erayic.agr.unit.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.erayic.agr.common.event.UnitRefreshMessage;
import com.erayic.agr.common.net.back.enums.EnumRequestType;
import com.erayic.agr.common.net.back.enums.EnumResourceType;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.codbking.DatePickDialog;
import com.erayic.agr.common.view.codbking.OnSureLisener;
import com.erayic.agr.common.view.codbking.bean.DateType;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitAddUpdateBatchItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitAddUpdateBatchItemEntity;
import com.erayic.agr.unit.presenter.IAddUpdateBatchPresenter;
import com.erayic.agr.unit.presenter.impl.AddUpdateBatchPresenterImpl;
import com.erayic.agr.unit.view.IAddUpdateBatchView;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/AddUpdateBatchActivity", name = "增加批次")
public class AddUpdateBatchActivity extends BaseActivity implements IAddUpdateBatchView {

    private final static int ACTIVITY_REQUEST = 9000;

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_batch_add_recycler)
    RecyclerView unitBatchAddRecycler;

    @Autowired
    boolean isAdd;//是否新增
    @Autowired
    String unitID;//管理单元ID
    @Autowired
    String batchName;//批次名称

    private CommonUnitBatchInfoBean.Batch batchInfo;

    private UnitAddUpdateBatchItemAdapter adapter;
    private IAddUpdateBatchPresenter presenter;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_unit_batch_add);
    }

    @Override
    public void initView() {
        if (isAdd) {
            toolbar.setTitle("增加批次");
            batchInfo = new CommonUnitBatchInfoBean.Batch();
        } else {
            toolbar.setTitle(TextUtils.isEmpty(batchName) ? "未命名批次" : batchName);
            batchInfo = (CommonUnitBatchInfoBean.Batch) getIntent().getSerializableExtra("batchInfo");
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(AddUpdateBatchActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        unitBatchAddRecycler.setLayoutManager(manager);
        adapter = new UnitAddUpdateBatchItemAdapter(AddUpdateBatchActivity.this, isAdd, null);
        adapter.setOnBatchClickListener(new UnitAddUpdateBatchItemAdapter.OnBatchItemClickListener() {
            @Override
            public void onClick(View view, UnitAddUpdateBatchItemEntity entity, final int position) {
                switch (entity.getItemType()) {
                    case UnitAddUpdateBatchItemEntity.TYPE_PRODUCT://产品
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_PRODUCT)
                                .navigation(AddUpdateBatchActivity.this, ACTIVITY_REQUEST);
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_SEED://种苗
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_SEED)
                                .navigation(AddUpdateBatchActivity.this, ACTIVITY_REQUEST);
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_AREA://面积
                        new ErayicEditDialog.Builder(AddUpdateBatchActivity.this)
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
                                            adapter.getData().get(position).setData(s.toString());
                                            adapter.notifyItemChanged(position);
                                        } else {
                                            showToast("不能为空");
                                        }
                                        dialog.dismiss();
                                    }
                                }).show();
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_DATE://时间
                    {
                        DatePickDialog dialog = new DatePickDialog(AddUpdateBatchActivity.this);
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
                                adapter.getData().get(position).setData(ctime);
                                adapter.notifyItemChanged(position);
                            }
                        });
                        dialog.show();
                    }
                    break;
                    case UnitAddUpdateBatchItemEntity.TYPE_PERSONNEL://种植人员
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_USER)
                                .navigation(AddUpdateBatchActivity.this, ACTIVITY_REQUEST);
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_DELETE://删除
                        new ErayicTextDialog.Builder(AddUpdateBatchActivity.this)
                                .setMessage("将要删除该批次的一切信息\n确定删除吗？", null)
                                .setTitle("温馨提示")
                                .setButton1("取消", new ErayicTextDialog.OnClickListener() {
                                    @Override
                                    public void onClick(Dialog dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setButton2("确定", new ErayicTextDialog.OnClickListener() {
                                    @Override
                                    public void onClick(Dialog dialog, int which) {
                                        dialog.dismiss();
                                        presenter.deleteBatch(batchInfo.getBatchID());
                                    }
                                }).show();

                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_FINISH://完成
                    {
                        new ErayicTextDialog.Builder(AddUpdateBatchActivity.this)
                                .setMessage("结束批次后不能再对该批次进行任何操作\n确定结束吗？", null)
                                .setTitle("温馨提示")
                                .setButton1("取消", new ErayicTextDialog.OnClickListener() {
                                    @Override
                                    public void onClick(Dialog dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setButton2("确定", new ErayicTextDialog.OnClickListener() {
                                    @Override
                                    public void onClick(Dialog dialog, int which) {
                                        dialog.dismiss();
                                        {
                                            DatePickDialog dialog1 = new DatePickDialog(AddUpdateBatchActivity.this);
                                            //设置上下年分限制
                                            dialog1.setYearLimt(5);
                                            //设置标题
                                            dialog1.setTitle("选择时间");
                                            //设置类型
                                            dialog1.setType(DateType.TYPE_YMD);
                                            //设置消息体的显示格式，日期格式
                                            dialog1.setMessageFormat("yyyy-MM-dd");
                                            //设置选择回调
                                            dialog1.setOnChangeLisener(null);
                                            //设置点击确定按钮回调
                                            dialog1.setOnSureLisener(new OnSureLisener() {
                                                @Override
                                                public void onSure(Date date) {
                                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                                    String ctime = format.format(date);
                                                    presenter.finishBatch(batchInfo.getBatchID(), ctime);
                                                }
                                            });
                                            dialog1.show();
                                        }
                                    }
                                }).show();


                    }
                    break;
                }
            }
        });
        unitBatchAddRecycler.setAdapter(adapter);
        unitBatchAddRecycler.addItemDecoration(new DividerItemDecoration(AddUpdateBatchActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new AddUpdateBatchPresenterImpl(this);
        List<UnitAddUpdateBatchItemEntity> list = new ArrayList<>();
        if (isAdd) {
            //产品
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_PRODUCT);
                list.add(entity);
            }

            //种苗
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_SEED);
                list.add(entity);
            }

            //面积
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_AREA);
                list.add(entity);
            }

            //时间
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_DATE);
                list.add(entity);
            }

            //人员
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_PERSONNEL);
                list.add(entity);
            }
        } else {
            //产品
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_PRODUCT);
                entity.setSubName(batchInfo.getProductName());
                entity.setData(batchInfo.getProductID());
                list.add(entity);
            }

            //种苗
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_SEED);
                entity.setSubName(batchInfo.getSeedName());
                entity.setData(batchInfo.getSeedID());
                list.add(entity);
            }

            //面积
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_AREA);
                entity.setSubName(batchInfo.getQuantity() + "");
                entity.setData(batchInfo.getQuantity() + "");
                list.add(entity);
            }

            //时间
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_DATE);
                entity.setSubName(new DateTime(ErayicNetDate.getLongDates(batchInfo.getStartTime())).toString("yyyy-MM-dd"));
                entity.setData(new DateTime(ErayicNetDate.getLongDates(batchInfo.getStartTime())).toString("yyyy-MM-dd"));
                list.add(entity);
            }

            //人员
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_PERSONNEL);
                entity.setSubName(batchInfo.getOpeName());
                entity.setData(batchInfo.getOperater());
                list.add(entity);
            }
            //分割线
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_DIVIDER);
                list.add(entity);
            }
            //删除
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_DELETE);
                list.add(entity);
            }
            //完成
            {
                UnitAddUpdateBatchItemEntity entity = new UnitAddUpdateBatchItemEntity();
                entity.setItemType(UnitAddUpdateBatchItemEntity.TYPE_FINISH);
                list.add(entity);
            }
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
                        UnitAddUpdateBatchItemEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == UnitAddUpdateBatchItemEntity.TYPE_PRODUCT) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                entity.setData(entry.getKey());
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
                        UnitAddUpdateBatchItemEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == UnitAddUpdateBatchItemEntity.TYPE_SEED) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                entity.setData(entry.getKey());
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
                        UnitAddUpdateBatchItemEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == UnitAddUpdateBatchItemEntity.TYPE_PERSONNEL) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                entity.setData(entry.getKey());
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
            for (UnitAddUpdateBatchItemEntity entity : adapter.getData()) {
                switch (entity.getItemType()) {
                    case UnitAddUpdateBatchItemEntity.TYPE_PRODUCT:
                        if (TextUtils.isEmpty((CharSequence) entity.getData())) {
                            showToast("请选择产品");
                            return super.onOptionsItemSelected(item);
                        } else {
                            proID = entity.getData().toString();
                        }
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_SEED:
                        if (TextUtils.isEmpty((CharSequence) entity.getData())) {
                            showToast("请选择种苗");
                            return super.onOptionsItemSelected(item);
                        } else {
                            seedID = entity.getData().toString();
                            seedName = entity.getSubName();
                        }
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_AREA:
                        if (TextUtils.isEmpty((CharSequence) entity.getData())) {
                            showToast("请输入种植面积");
                            return super.onOptionsItemSelected(item);
                        } else {
                            quantity = entity.getData().toString();
                        }
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_DATE:
                        if (TextUtils.isEmpty((CharSequence) entity.getData())) {
                            showToast("请输入种植时间");
                            return super.onOptionsItemSelected(item);
                        } else {
                            stTime = entity.getData().toString();
                        }
                        break;
                    case UnitAddUpdateBatchItemEntity.TYPE_PERSONNEL:
                        if (TextUtils.isEmpty((CharSequence) entity.getData())) {
                            showToast("请选择种植人员");
                            return super.onOptionsItemSelected(item);
                        } else {
                            ope = entity.getData().toString();
                        }
                        break;
                }
            }
            final String final_proID = proID;
            final String final_seedID = seedID;
            final String final_seedName = seedName;
            final String final_quantity = quantity;
            final String final_stTime = stTime;
            final String final_ope = ope;

            if (isAdd) {
                presenter.createBatch(final_proID, final_seedID, final_seedName, final_quantity, final_stTime, final_ope, unitID);
            } else {
                presenter.updateBatch(batchInfo.getBatchID(), final_proID, final_seedID, final_seedName, final_quantity, final_stTime, final_ope, unitID);
            }

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
                    dialog = new LoadingDialog(AddUpdateBatchActivity.this);
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
                    dialog = new LoadingDialog(AddUpdateBatchActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void addBatchSure() {
        UnitRefreshMessage message = new UnitRefreshMessage();
        message.setMsgType(UnitRefreshMessage.UNIT_MASTER_LIST);
        EventBus.getDefault().post(message);//通知刷新
        ErayicStack.getInstance().finishCurrentActivity();
    }

    @Override
    public void updateBatchSure() {
        UnitRefreshMessage message1 = new UnitRefreshMessage();
        message1.setMsgType(UnitRefreshMessage.UNIT_MASTER_LIST);
        EventBus.getDefault().post(message1);//通知刷新
        UnitRefreshMessage message2 = new UnitRefreshMessage();
        message2.setMsgType(UnitRefreshMessage.UNIT_MASTER_STATUE);
        message2.setSubType(UnitRefreshMessage.TYPE_REFRESH);
        EventBus.getDefault().post(message2);//通知刷新
        ErayicStack.getInstance().finishCurrentActivity();
    }

    @Override
    public void deleteBatchSure() {
        UnitRefreshMessage message1 = new UnitRefreshMessage();
        message1.setMsgType(UnitRefreshMessage.UNIT_MASTER_LIST);
        EventBus.getDefault().post(message1);//通知刷新
        UnitRefreshMessage message2 = new UnitRefreshMessage();
        message2.setMsgType(UnitRefreshMessage.UNIT_MASTER_STATUE);
        message2.setSubType(UnitRefreshMessage.TYPE_DELETE);
        EventBus.getDefault().post(message2);//通知刷新
        ErayicStack.getInstance().finishCurrentActivity();
    }
}
