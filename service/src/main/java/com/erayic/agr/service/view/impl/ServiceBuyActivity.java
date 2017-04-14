package com.erayic.agr.service.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceBuyByEntAdapter;
import com.erayic.agr.service.adapter.entity.ServiceBuyByEntEntity;
import com.erayic.agr.service.view.IServiceBuyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/ServiceBuyActivity", name = "订单确认页面")
public class ServiceBuyActivity extends BaseActivity implements IServiceBuyView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.service_buy_recycler)
    RecyclerView serviceBuyRecycler;

    @Autowired
    String serviceName;
    @Autowired
    String serviceID;
    @Autowired
    String serviceIcon;
    @Autowired
    int serviceType;
    @Autowired
    CommonPriceBean priceBean;

    private ServiceBuyByEntAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_buy_ent);
    }

    @Override
    public void initView() {
        toolbar.setTitle("确认订单");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        priceBean = (CommonPriceBean) getIntent().getSerializableExtra("priceBean");
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        serviceBuyRecycler.setLayoutManager(manager);
        adapter = new ServiceBuyByEntAdapter(ServiceBuyActivity.this, null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnSelectPriceClickListener(new ServiceBuyByEntAdapter.OnSelectPriceClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/service/activity/ServicePriceByEntActivity")
                        .withString("serviceID", serviceID).withInt("priceID", priceBean.getPriceID())
                        .navigation(ServiceBuyActivity.this, AgrConstant.ACTIVITY_REQUEST_ServiceBuyActivity);
            }
        });
        adapter.isFirstOnly(false);
        serviceBuyRecycler.setAdapter(adapter);

    }

    @Override
    public void initData() {
        List<ServiceBuyByEntEntity> list = new ArrayList<>();
        ServiceBuyByEntEntity entityContent = new ServiceBuyByEntEntity();
        entityContent.setItemType(ServiceBuyByEntEntity.TYPE_CONTENT);
        ServiceBuyByEntEntity.BuyContent content = new ServiceBuyByEntEntity.BuyContent();
        content.setServiceID(serviceID);
        content.setServiceName(serviceName);
        content.setServiceType(serviceType);
        content.setPriceBean(priceBean);
        entityContent.setBuyContent(content);
        list.add(entityContent);

        ServiceBuyByEntEntity entityPay = new ServiceBuyByEntEntity();
        entityPay.setItemType(ServiceBuyByEntEntity.TYPE_PAY);
        list.add(entityPay);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (AgrConstant.ACTIVITY_REQUEST_ServiceBuyActivity) {//返回选择价格信息
            case AgrConstant.ACTIVITY_RESULT_Service_PriceByEntActivity:
                priceBean = (CommonPriceBean) data.getSerializableExtra("price");
                adapter.getData().get(0).getBuyContent().setPriceBean(priceBean);
                adapter.notifyItemChanged(0);
                //待测试
                break;
            default:
                break;
        }
    }

}
