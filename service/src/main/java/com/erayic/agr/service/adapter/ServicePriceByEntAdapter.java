package com.erayic.agr.service.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServicePriceByEntItemViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServicePriceByEntAdapter extends BaseQuickAdapter<CommonPriceBean, ServicePriceByEntItemViewHolder> {


    public ServicePriceByEntAdapter(List<CommonPriceBean> data) {
        super(R.layout.adapter_service_price_ent_item, data);
    }

    @Override
    protected void convert(ServicePriceByEntItemViewHolder helper, CommonPriceBean item) {
        helper.servicePriceItemPrice.setMoney(item.getPrice(), "/" + item.getUnit());
        helper.servicePriceItemCheck.setChecked(item.isCheck());
    }


}
