package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class OrderByInfoItemViewHolder extends BaseViewHolder {

    @BindView(R2.id.order_info_item_name)
    public TextView orderInfoItemName;
    @BindView(R2.id.order_info_item_content)
    public TextView orderInfoItemContent;

    public OrderByInfoItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
