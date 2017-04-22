package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.MoneyView;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByPayingItemViewHolder extends BaseViewHolder {

    @BindView(R2.id.order_paying_item_layout)
    public LinearLayout orderPayingItemLayout;
    @BindView(R2.id.order_paying_item_img)
    public ImageView orderPayingItemImg;
    @BindView(R2.id.order_paying_item_name)
    public TextView orderPayingItemName;
    @BindView(R2.id.order_paying_item_directions)
    public TextView orderPayingItemDirections;
    @BindView(R2.id.order_paying_item_price)
    public MoneyView orderPayingItemPrice;
    @BindView(R2.id.order_paying_item_pay)
    public Button orderPayingItemPay;

    public OrderByPayingItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
