package com.erayic.agr.service.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.common.net.back.enums.EnumOrderType;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicTextUtil;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.OrderByHistoryItemViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByHistoryAdapter extends BaseQuickAdapter<CommonOrderBean, OrderByHistoryItemViewHolder> {

    private Context context;

    private OnOrderClickListener onOrderClickListener;

    public OrderByHistoryAdapter(Context context, List<CommonOrderBean> data) {
        super(R.layout.adapter_order_history_item, data);
        this.context = context;
    }

    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener) {
        this.onOrderClickListener = onOrderClickListener;
    }

    @Override
    protected void convert(OrderByHistoryItemViewHolder helper, final CommonOrderBean item) {

        Glide.with(context)
                .load(TextUtils.isEmpty(item.getIcon()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + item.getIcon()))
                .apply(AgrConstant.iconOptions)
                .into(helper.orderHistoryItemImg);
        helper.orderHistoryItemName.setText(item.getServiceName());
        helper.orderHistoryItemFinish.setText("订购时间：" + ErayicNetDate.getStringDateYMDHM(item.getOrderTime()) + "");
        helper.orderHistoryItemPrice.setText("总价：￥" + ErayicTextUtil.getPrice(item.getPrice()) + "");
        if (item.getStatus() == EnumOrderType.ORDER_SUCCESS) {
            helper.orderHistoryItemStatue.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_green));
        } else {
            helper.orderHistoryItemStatue.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_gray));
        }
        helper.orderHistoryItemStatue.setText(EnumOrderType.getStatueDes(item.getStatus()));

        helper.orderHistoryItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOrderClickListener != null)
                    onOrderClickListener.onOrderClick(v, item.getOrderID(), 0);
            }
        });
        helper.orderHistoryItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        helper.orderHistoryItemBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        helper.orderHistoryItemBuy.setOnLongClickListener(new View.OnLongClickListener() {
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
