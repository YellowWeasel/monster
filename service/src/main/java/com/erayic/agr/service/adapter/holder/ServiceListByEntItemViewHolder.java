package com.erayic.agr.service.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class ServiceListByEntItemViewHolder extends BaseViewHolder {

    @BindView(R2.id.service_market_item_layout)
    public LinearLayout serviceMarketItemLayout;
    @BindView(R2.id.service_market_item_img)
    public ImageView serviceMarketItemImg;
    @BindView(R2.id.service_market_item_name)
    public TextView serviceMarketItemName;
    @BindView(R2.id.service_market_item_directions)
    public TextView serviceMarketItemDirections;
    @BindView(R2.id.service_market_item_price)
    public MoneyView serviceMarketItemPrice;

    public ServiceListByEntItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
