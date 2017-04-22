package com.erayic.agr.service.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.entity.OrderByInfoEntity;
import com.erayic.agr.service.adapter.holder.OrderByInfoItemViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：订单信息adapter
 */

public class OrderByInfoAdapter extends BaseQuickAdapter<OrderByInfoEntity, OrderByInfoItemViewHolder> {


    public OrderByInfoAdapter(List<OrderByInfoEntity> data) {
        super(R.layout.adapter_order_info_item, data);
    }

    @Override
    protected void convert(OrderByInfoItemViewHolder helper, OrderByInfoEntity item) {
        helper.orderInfoItemName.setText(item.getTitle());
        helper.orderInfoItemContent.setText(item.getContent());
    }
}
