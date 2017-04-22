package com.erayic.agr.service.adapter;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.OrderByPayingItemViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByPayingAdapter extends BaseQuickAdapter<CommonOrderBean, OrderByPayingItemViewHolder> {

    private Context context;
    private OnOrderClickListener onOrderClickListener;

    public OrderByPayingAdapter(Context context, List<CommonOrderBean> data) {
        super(R.layout.adapter_order_paying_item, data);
        this.context = context;
    }

    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener) {
        this.onOrderClickListener = onOrderClickListener;
    }

    @Override
    protected void convert(OrderByPayingItemViewHolder helper, final CommonOrderBean item) {
        Glide.with(context).load(AgrConstant.IMAGE_URL_PREFIX + item.getIcon())
                .error(R.drawable.image_service_test)
                .into(helper.orderPayingItemImg);
        helper.orderPayingItemName.setText(item.getServiceName());
        helper.orderPayingItemDirections.setText("生成时间：" + ErayicNetDate.getStringDateYMDHM(item.getOrderTime()) + "");
        helper.orderPayingItemPrice.setMoney(item.getPrice(), "");
        helper.orderPayingItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOrderClickListener != null)
                    onOrderClickListener.onOrderClick(v, item.getOrderID(), 0);
            }
        });
        helper.orderPayingItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        helper.orderPayingItemPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOrderClickListener != null)
                    onOrderClickListener.onOrderClick(v, item.getOrderID(), 1);
            }
        });
        helper.orderPayingItemPay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    /**
     * type == 0 跳转到订单详情
     * type == 1 跳转到订单详情并调用支付
     */
    public interface OnOrderClickListener {
        void onOrderClick(View view, String orderID, int type);
    }
}
