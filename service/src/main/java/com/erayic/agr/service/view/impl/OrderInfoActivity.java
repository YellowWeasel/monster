package com.erayic.agr.service.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.net.back.CommonWcfInvoiceBean;
import com.erayic.agr.common.net.back.enums.EnumInvoiceStatue;
import com.erayic.agr.common.net.back.enums.EnumOrderType;
import com.erayic.agr.common.net.back.enums.EnumPayType;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicTextUtil;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.adapter.OrderByHistoryAdapter;
import com.erayic.agr.service.adapter.OrderByInfoAdapter;
import com.erayic.agr.service.adapter.entity.OrderByInfoEntity;
import com.erayic.agr.service.presenter.IOrderByInfoPresenter;
import com.erayic.agr.service.presenter.impl.OrderByInfoPresenterImpl;
import com.erayic.agr.service.view.IOrderInfoView;


import java.util.ArrayList;
import java.util.HashMap;
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

@Route(path = "/service/activity/OrderInfoActivity", name = "订单信息页面")
public class OrderInfoActivity extends BaseActivity implements IOrderInfoView, SwipeRefreshLayout.OnRefreshListener {

    @Autowired
    String orderID;
    @Autowired
    boolean isBuy;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.order_info_service_icon)
    ImageView orderInfoServiceIcon;
    @BindView(R2.id.order_info_service_name)
    TextView orderInfoServiceName;
    @BindView(R2.id.order_info_price)
    TextView orderInfoPrice;
    @BindView(R2.id.order_info_statue)
    TextView orderInfoStatue;
    @BindView(R2.id.order_info_recycler_order)
    RecyclerView orderInfoRecyclerOrder;
    @BindView(R2.id.order_info_layout_order)
    LinearLayout orderInfoLayoutOrder;
    @BindView(R2.id.order_info_layout_invoice)
    LinearLayout orderInfoLayoutInvoice;
    @BindView(R2.id.order_info_recycler_pay)
    RecyclerView orderInfoRecyclerPay;
    @BindView(R2.id.order_info_layout_pay)
    LinearLayout orderInfoLayoutPay;
    @BindView(R2.id.order_info_pay)
    Button orderInfoPay;
    @BindView(R2.id.order_info_swipe)
    SwipeRefreshLayout orderInfoSwipe;
    @BindView(R2.id.order_info_statue_invoice)
    TextView orderInfoStatueInvoice;
    @BindView(R2.id.order_info_ask_invoice)
    Button orderInfoAskInvoice;

    private int orderStatue = -1;
    private float orderPayPrice = -1f;
    private LoadingDialog dialog;

    private IOrderByInfoPresenter presenter;

    private OrderByInfoAdapter orderAdapter, payAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        toolbar.setTitle("订单详细信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        orderInfoSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(false);//滑动监听
        orderInfoRecyclerOrder.setLayoutManager(manager);
        orderAdapter = new OrderByInfoAdapter(null);
        orderAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        orderAdapter.isFirstOnly(false);
        orderInfoRecyclerOrder.setAdapter(orderAdapter);

        //使用线性布局管理器
        CustomLinearLayoutManager manager1 = new CustomLinearLayoutManager(this);
        manager1.setScrollEnabled(false);//滑动监听
        orderInfoRecyclerPay.setLayoutManager(manager1);
        payAdapter = new OrderByInfoAdapter(null);
        payAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        payAdapter.isFirstOnly(false);
        orderInfoRecyclerPay.setAdapter(payAdapter);

    }

    @Override
    public void initData() {
        presenter = new OrderByInfoPresenterImpl(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getSpecifyOrderDetail(orderID);
    }


    @OnClick(R2.id.order_info_pay)
    public void onOrderInfoPayClicked() {
        showToast("未实现");
    }

    @OnClick(R2.id.order_info_ask_invoice)
    public void onViewClicked() {
        //索要发票
        Map<String, Float> map = new HashMap<>();
        map.put(orderID, orderPayPrice);

        CommonWcfInvoiceBean bean = new CommonWcfInvoiceBean();
        List<CommonWcfInvoiceBean.CommonWcfInvoice> list = new ArrayList<>();
        CommonWcfInvoiceBean.CommonWcfInvoice invoice = new CommonWcfInvoiceBean.CommonWcfInvoice();
        invoice.setInvID(orderID);
        invoice.setPrice(orderPayPrice);
        list.add(invoice);
        bean.setOrderIDs(list);
        presenter.requestInvoice(bean);
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orderInfoSwipe != null && !orderInfoSwipe.isRefreshing())
                    orderInfoSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orderInfoSwipe != null && orderInfoSwipe.isRefreshing())
                    orderInfoSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(OrderInfoActivity.this);
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
                    dialog = new LoadingDialog(OrderInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void cancelOrderSure() {
        onRefresh();
        showToast("取消订单成功");
    }

    @Override
    public void requestInvoiceSure() {
        onRefresh();
        showToast("索要发票成功");
    }

    @Override
    public void refreshOrderView(final CommonOrderBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                orderStatue = bean.getStatus();
                orderPayPrice = bean.getPrice();
                Glide.with(OrderInfoActivity.this).load(AgrConstant.IMAGE_URL_PREFIX + bean.getIcon())
                        .error(R.drawable.image_service_test)
                        .into(orderInfoServiceIcon);
                orderInfoServiceName.setText(bean.getServiceName());
                orderInfoPrice.setText("总价：￥" + ErayicTextUtil.getPrice(bean.getPrice()) + "");
                orderInfoStatue.setText(bean.getStatusToString());

                List<OrderByInfoEntity> listOrder = new ArrayList<>();
                OrderByInfoEntity orderNum = new OrderByInfoEntity();
                OrderByInfoEntity orderStart = new OrderByInfoEntity();
                OrderByInfoEntity orderStatue = new OrderByInfoEntity();
                OrderByInfoEntity orderPrice = new OrderByInfoEntity();
                orderNum.setTitle("订单编号");
                orderNum.setContent(bean.getOrderID());
                orderStart.setTitle("生成时间");
                orderStart.setContent(ErayicNetDate.getStringDateYMDHM(bean.getOrderTime()));
                orderStatue.setTitle("订单状态");
                orderStatue.setContent(bean.getStatusToString());
                orderPrice.setTitle("订单总价");
                orderPrice.setContent("￥" + ErayicTextUtil.getPrice(bean.getPrice()));
                listOrder.add(orderNum);
                listOrder.add(orderStart);
                listOrder.add(orderStatue);
                listOrder.add(orderPrice);
                orderAdapter.setNewData(listOrder);
                if (bean.getStatus() == EnumOrderType.ORDER_SUCCESS) {
                    orderInfoLayoutInvoice.setVisibility(View.VISIBLE);
                    orderInfoLayoutPay.setVisibility(View.VISIBLE);
                    orderInfoPay.setVisibility(View.GONE);
                    orderInfoStatueInvoice.setText(EnumInvoiceStatue.getStatueDes(bean.getIsSend()));
                    if (bean.getIsSend() == EnumInvoiceStatue.STATUE_NoRequest)
                        orderInfoAskInvoice.setVisibility(View.VISIBLE);
                    else
                        orderInfoAskInvoice.setVisibility(View.GONE);

                    List<OrderByInfoEntity> listPay = new ArrayList<>();
                    OrderByInfoEntity payNum = new OrderByInfoEntity();
                    OrderByInfoEntity payType = new OrderByInfoEntity();
                    OrderByInfoEntity payPrice = new OrderByInfoEntity();
                    OrderByInfoEntity payTime = new OrderByInfoEntity();
                    payNum.setTitle("流水号");
                    payNum.setContent(bean.getPayID());
                    payType.setTitle("支付方式");
                    payType.setContent(EnumPayType.getStatueDes(bean.getPayType()));
                    payPrice.setTitle("支付金额");
                    payPrice.setContent("￥99999999999999999999999999999.99");
                    payTime.setTitle("完成时间");
                    payTime.setContent(ErayicNetDate.getStringDateYMDHM(bean.getFinishTime()));
                    listPay.add(payNum);
                    listPay.add(payType);
                    listPay.add(payPrice);
                    listPay.add(payTime);
                    payAdapter.setNewData(listPay);
                } else {
                    orderInfoLayoutInvoice.setVisibility(View.GONE);
                    orderInfoLayoutPay.setVisibility(View.GONE);
                    if (bean.getStatus() == EnumOrderType.ORDER_UNPAY) {
                        orderInfoPay.setVisibility(View.VISIBLE);
                    } else {
                        orderInfoPay.setVisibility(View.GONE);
                    }
                }
                supportInvalidateOptionsMenu();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_order_info, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem closeOrder = menu.findItem(R.id.action_order_close);
        if (orderStatue == EnumOrderType.ORDER_UNPAY)
            closeOrder.setVisible(true);
        else
            closeOrder.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_order_close) {//取消订单
            new ErayicDialog.Builder(OrderInfoActivity.this)
                    .setMessage("确定取消订单吗？\n取消后此订单将会失效，如需再次购买请前往服务市场！", null)
                    .setTitle("温馨提示")
                    .setButton1("确定", new ErayicDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which) {
                            presenter.cancelOrderByEntUser(orderID);
                            dialog.dismiss();
                        }
                    })
                    .setButton2("取消", new ErayicDialog.OnClickListener() {
                        @Override
                        public void onClick(Dialog dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
