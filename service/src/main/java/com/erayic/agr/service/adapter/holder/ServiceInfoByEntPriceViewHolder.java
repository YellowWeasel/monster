package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.Button;
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

public class ServiceInfoByEntPriceViewHolder extends BaseViewHolder {

    @BindView(R2.id.service_info_price)
    public TextView serviceInfoPrice;
    @BindView(R2.id.service_info_buy)
    public Button serviceInfoBuy;
    @BindView(R2.id.service_info_dueDate)
    public TextView serviceInfoDueDate;
    public ServiceInfoByEntPriceViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
