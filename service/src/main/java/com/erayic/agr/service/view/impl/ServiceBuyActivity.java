package com.erayic.agr.service.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.net.back.enums.EnumPayType;
import com.erayic.agr.common.net.back.enums.EnumServiceType;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.MoneyView;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.ServiceBuyByEntAdapter;
import com.erayic.agr.service.adapter.entity.ServiceBuyByEntEntity;
import com.erayic.agr.service.presenter.IServiceBuyByEntPresenter;
import com.erayic.agr.service.presenter.impl.ServiceBuyByEntPresenterImpl;
import com.erayic.agr.service.view.IServiceBuyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/service/activity/ServiceBuyActivity", name = "订单确认页面")
public class ServiceBuyActivity extends BaseActivity implements IServiceBuyView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.service_buy_recycler)
    RecyclerView serviceBuyRecycler;
    @BindView(R2.id.service_buy_total_price)
    MoneyView serviceBuyTotalPrice;
    @BindView(R2.id.service_buy_total_order)
    Button serviceBuyTotalOrder;


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

    List<CommonSubServiceBean> serviceList = new ArrayList<>();

    private LoadingDialog dialog;

    private ServiceBuyByEntAdapter adapter;

    private IServiceBuyByEntPresenter presenter;

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
//        priceBean = (CommonPriceBean) getIntent().getSerializableExtra("priceBean");
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(true);//滑动监听
        serviceBuyRecycler.setLayoutManager(manager);
        adapter = new ServiceBuyByEntAdapter(ServiceBuyActivity.this, null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnSelectPriceClickListener(new ServiceBuyByEntAdapter.OnSelectPriceClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/service/activity/ServicePriceActivity")
                        .withString("serviceID", serviceID).withParcelable("price", priceBean)
                        .navigation(ServiceBuyActivity.this, AgrConstant.ACTIVITY_REQUEST_ServiceBuyActivity);
            }
        });
        adapter.setOnSelectTopicClickListener(new ServiceBuyByEntAdapter.OnSelectTopicClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/service/activity/ServiceTopicActivity")
                        .withString("serviceID", serviceID).withParcelableArrayList("serviceList", (ArrayList<? extends Parcelable>) serviceList)
                        .navigation(ServiceBuyActivity.this, AgrConstant.ACTIVITY_REQUEST_ServiceBuyActivity);
            }
        });
        adapter.isFirstOnly(false);
        serviceBuyRecycler.setAdapter(adapter);

    }

    @Override
    public void initData() {
        presenter = new ServiceBuyByEntPresenterImpl(this);
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

        updateTotalPrice();
    }

    /**
     * 更新实付款价格
     */
    private void updateTotalPrice() {
        if (adapter.getData().get(0).getBuyContent().getServiceType() == EnumServiceType.Subject) {
            serviceBuyTotalPrice.setMoney(adapter.getData().get(0).getBuyContent().getPriceBean().getPrice() *
                    (adapter.getData().get(0).getBuyContent().getTopiceServices() == null ? 0 : adapter.getData().get(0).getBuyContent().getTopiceServices().size()), "");
        } else {
            serviceBuyTotalPrice.setMoney(adapter.getData().get(0).getBuyContent().getPriceBean().getPrice(), "");
        }

    }


    @OnClick(R2.id.service_buy_total_order)
    public void onViewClicked() {//提交订购
        if (adapter.getData().get(1).getBuyType() == EnumPayType.PAY_DEFAULT) {
            showToast("请选择支付方式");
            return;
        }
        if (adapter.getData().get(0).getBuyContent().getServiceType() == EnumServiceType.Subject) {
            if (adapter.getData().get(0).getBuyContent().getTopiceServices() == null || adapter.getData().get(0).getBuyContent().getTopiceServices().size() == 0) {
                showToast("请选择您要购买的服务品种");
                return;
            }
        }
        presenter.orderServiceByBuyOfEnt(serviceID, priceBean.getPriceID(), serviceList, adapter.getData().get(1).getBuyType());
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
        switch (requestCode) {
            case AgrConstant.ACTIVITY_REQUEST_ServiceBuyActivity:
                switch (resultCode) {
                    case AgrConstant.ACTIVITY_RESULT_Service_PriceByEntActivity: {//返回选择价格信息
                        priceBean = data.getParcelableExtra("price");
                        adapter.getData().get(0).getBuyContent().setPriceBean(priceBean);
                        adapter.notifyItemChanged(0);
                    }
                    break;
                    case AgrConstant.ACTIVITY_RESULT_Service_TopicByEntActivity: {//返回品种选择
                        serviceList = data.getParcelableArrayListExtra("serviceList");
                        adapter.getData().get(0).getBuyContent().setTopiceServices(serviceList);
                        adapter.notifyItemChanged(0);
                    }
                    break;
                    default:
                        break;
                }
                updateTotalPrice();
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(ServiceBuyActivity.this);
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
                    dialog = new LoadingDialog(ServiceBuyActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void submitSure() {
        showToast("提交成功！");
        ServiceBuyActivity.this.finish();
    }
}
