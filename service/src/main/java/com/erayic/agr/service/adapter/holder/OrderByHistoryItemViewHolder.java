package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByHistoryItemViewHolder extends BaseViewHolder {

    @BindView(R2.id.order_history_item_img)
    public ImageView orderHistoryItemImg;
    @BindView(R2.id.order_history_item_name)
    public TextView orderHistoryItemName;
    @BindView(R2.id.order_history_item_finish)
    public TextView orderHistoryItemFinish;
    @BindView(R2.id.order_history_item_price)
    public TextView orderHistoryItemPrice;
    @BindView(R2.id.order_history_item_statue)
    public TextView orderHistoryItemStatue;
    @BindView(R2.id.order_history_item_buy)
    public Button orderHistoryItemBuy;
    @BindView(R2.id.order_history_item_layout)
    public LinearLayout orderHistoryItemLayout;

    public OrderByHistoryItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
