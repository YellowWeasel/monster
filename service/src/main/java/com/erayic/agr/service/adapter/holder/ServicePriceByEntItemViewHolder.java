package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;

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

public class ServicePriceByEntItemViewHolder extends BaseViewHolder {

    @BindView(R2.id.service_price_item_price)
    public MoneyView servicePriceItemPrice;
    @BindView(R2.id.service_price_item_check)
    public CheckBox servicePriceItemCheck;
    @BindView(R2.id.service_price_item_layout)
    public TableRow servicePriceItemLayout;

    public ServicePriceByEntItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
