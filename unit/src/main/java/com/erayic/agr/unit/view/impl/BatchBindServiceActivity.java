package com.erayic.agr.unit.view.impl;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.UnitRefreshMessage;
import com.erayic.agr.common.net.back.enums.EnumBatchByBindType;
import com.erayic.agr.common.net.back.enums.EnumOrderType;
import com.erayic.agr.common.net.back.enums.EnumUnitType;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBindServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBuyServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchServiceBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.UnitBatchBindServiceItemAdapter;
import com.erayic.agr.unit.adapter.entity.UnitBatchBindServiceEntity;
import com.erayic.agr.unit.presenter.IBatchBindServicePresenter;
import com.erayic.agr.unit.presenter.impl.BatchBindServicePresenterImpl;
import com.erayic.agr.unit.view.IBatchBindServiceView;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/unit/activity/BatchBindServiceActivity", name = "批次绑定服务")
public class BatchBindServiceActivity extends BaseActivity implements IBatchBindServiceView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_batch_bind_icon)
    CircleImageView unitBatchBindIcon;
    @BindView(R2.id.unit_batch_bind_name)
    TextView unitBatchBindName;
    @BindView(R2.id.unit_batch_bind_close)
    Button unitBatchBindClose;
    @BindView(R2.id.unit_batch_bind_recycler)
    RecyclerView unitBatchBindRecycler;

    private LoadingDialog dialog;

    @Autowired
    String unitID;
    @Autowired
    String batchID;
    @Autowired
    String batchName;
    @Autowired
    int serType;

    private IBatchBindServicePresenter presenter;
    private UnitBatchBindServiceItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_bind_service);
    }

    @Override
    public void initView() {
        switch (serType) {
            case EnumBatchByBindType.TYPE_Adaptability:
                toolbar.setTitle("绑定生长适应性评价服务");
                break;
            case EnumBatchByBindType.TYPE_cycle:
                toolbar.setTitle("绑定生长周期预测服务");
                break;
            case EnumBatchByBindType.TYPE_produce:
                toolbar.setTitle("绑定产量预测服务");
                break;
            case EnumBatchByBindType.TYPE_price:
                toolbar.setTitle("绑定市场价格服务");
                break;
            default:
                toolbar.setTitle("绑定未知参数服务");
                break;
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(BatchBindServiceActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        unitBatchBindRecycler.setLayoutManager(manager);
        adapter = new UnitBatchBindServiceItemAdapter(BatchBindServiceActivity.this, null);
        adapter.setOnItemClickListener(new UnitBatchBindServiceItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int type, String serviceID) {
                switch (type) {
                    case UnitBatchBindServiceEntity.TYPE_SERVICE:
                        presenter.bindServiceByBatch(unitID, EnumUnitType.TYPE_PLOTS, batchID, serType, serviceID);
                        break;
                    case UnitBatchBindServiceEntity.TYPE_MORE:
                        presenter.getBindServiceOfSubject(serType);
                        break;
                }
            }
        });
        unitBatchBindRecycler.setAdapter(adapter);
        unitBatchBindRecycler.addItemDecoration(new DividerItemDecoration(BatchBindServiceActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new BatchBindServicePresenterImpl(this);
        presenter.getSingleSeviceList(serType);
        presenter.getBindService(batchID, serType);
    }


    @OnClick(R2.id.unit_batch_bind_close)
    public void onViewClicked() {
        presenter.cancelServieBind(batchID, serType);
    }


    @Override
    public void refreshServiceBindView(final CommonUnitBatchBindServiceBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bean == null) {
                    unitBatchBindName.setTextColor(ContextCompat.getColor(BatchBindServiceActivity.this, R.color.app_base_text_status_red));
                    unitBatchBindName.setText("未绑定");
                    unitBatchBindClose.setVisibility(View.GONE);
                } else {
                    unitBatchBindName.setTextColor(ContextCompat.getColor(BatchBindServiceActivity.this, R.color.app_base_text_title_1));
                    unitBatchBindName.setText(TextUtils.isEmpty(bean.getCropName()) ? "未命名" : bean.getCropName());
                    unitBatchBindClose.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void refreshServiceListView(final List<CommonUnitBatchServiceBean> list) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<UnitBatchBindServiceEntity> listData = new ArrayList<>();
                for (CommonUnitBatchServiceBean bean : list) {
                    UnitBatchBindServiceEntity entity = new UnitBatchBindServiceEntity();
                    entity.setItemType(UnitBatchBindServiceEntity.TYPE_SERVICE);
                    entity.setServiceID(bean.getServiceID());
                    entity.setName(bean.getCrop());
                    if (bean.getStatus() == EnumOrderType.ORDER_SUCCESS)
                        entity.setSubName(new DateTime(ErayicNetDate.getLongDates(bean.getEndTime())).toString("yyyy-MM-dd") + " 到期");
                    listData.add(entity);
                }
                UnitBatchBindServiceEntity entity = new UnitBatchBindServiceEntity();
                entity.setItemType(UnitBatchBindServiceEntity.TYPE_MORE);
                entity.setName("更多");
                listData.add(entity);

                adapter.setNewData(listData);
            }
        });
    }

    @Override
    public void saveSure() {
        UnitRefreshMessage message = new UnitRefreshMessage();
        message.setMsgType(UnitRefreshMessage.UNIT_MASTER_STATUE);
        EventBus.getDefault().post(message);//通知刷新
        ErayicStack.getInstance().finishCurrentActivity();
    }

    @Override
    public void toServiceBuy(CommonUnitBatchBuyServiceBean bean) {
        if (bean.getMasterPrice() != null)
            ARouter.getInstance().build("/service/activity/ServiceBuyActivity")
                    .withString("serviceID", bean.getServiceID())
                    .withString("serviceName", bean.getServiceName())
                    .withString("serviceIcon", bean.getIcon())
                    .withInt("serviceType", bean.getType())
                    .withParcelable("priceBean", bean.getMasterPrice()).navigation();
        else
            showToast("没有更多数据了");
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(BatchBindServiceActivity.this);
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
                    dialog = new LoadingDialog(BatchBindServiceActivity.this);
                dialog.dismiss();
            }
        });
    }

}
