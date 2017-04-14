package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.MoneyView;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByEntContentViewHolder extends BaseViewHolder {

    @BindView(R2.id.service_buy_content_name)
    public TextView serviceBuyContentName;
    @BindView(R2.id.service_buy_content_price)
    public MoneyView serviceBuyContentPrice;
    @BindView(R2.id.service_buy_content_layout)
    public LinearLayout serviceBuyContentLayout;
    @BindView(R2.id.service_buy_topic_name)
    public TextView serviceBuyTopicName;
    @BindView(R2.id.service_buy_topic_layout)
    public LinearLayout serviceBuyTopicLayout;
    @BindView(R2.id.item_layout)
    public LinearLayout itemLayout;

    public ServiceBuyByEntContentViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
