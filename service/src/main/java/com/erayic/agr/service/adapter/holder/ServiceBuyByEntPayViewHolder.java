package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableRow;

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

public class ServiceBuyByEntPayViewHolder extends BaseViewHolder {

    @BindView(R2.id.service_buy_pay_weixin_layout)
    public TableRow serviceBuyPayWeixinLayout;
    @BindView(R2.id.service_buy_pay_alpay_layout)
    public TableRow serviceBuyPayAlpayLayout;
    @BindView(R2.id.service_buy_pay_bank_layout)
    public TableRow serviceBuyPayBankLayout;
    @BindView(R2.id.service_buy_pay_weixin)
    public CheckBox serviceBuyPayWeixin;
    @BindView(R2.id.service_buy_pay_alpay)
    public CheckBox serviceBuyPayAlpay;
    @BindView(R2.id.service_buy_pay_bank)
    public CheckBox serviceBuyPayBank;
    @BindView(R2.id.item_layout)
    public LinearLayout itemLayout;

    public ServiceBuyByEntPayViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
