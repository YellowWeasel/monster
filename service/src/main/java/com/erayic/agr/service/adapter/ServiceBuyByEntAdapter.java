package com.erayic.agr.service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TableRow;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.enums.EnumPayType;
import com.erayic.agr.common.net.back.enums.EnumServiceType;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.entity.ServiceBuyByEntEntity;
import com.erayic.agr.service.adapter.holder.ServiceBuyByEntContentViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceBuyByEntPayViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceBuyByEntAdapter extends BaseMultiItemQuickAdapter<ServiceBuyByEntEntity, BaseViewHolder> {


    private Context context;

    private OnSelectPriceClickListener onSelectPriceClickListener;

    public ServiceBuyByEntAdapter(Context context, List<ServiceBuyByEntEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnSelectPriceClickListener(OnSelectPriceClickListener onSelectPriceClickListener) {
        this.onSelectPriceClickListener = onSelectPriceClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ServiceBuyByEntEntity.TYPE_CONTENT:
                return new ServiceBuyByEntContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_buy_ent_content, parent, false));
            case ServiceBuyByEntEntity.TYPE_PAY:
                return new ServiceBuyByEntPayViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_buy_ent_pay, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ServiceBuyByEntEntity item) {
        switch (helper.getItemViewType()) {
            case ServiceBuyByEntEntity.TYPE_CONTENT:
                if (helper instanceof ServiceBuyByEntContentViewHolder) {
                    ((ServiceBuyByEntContentViewHolder) helper).serviceBuyContentName.setText(item.getBuyContent().getServiceName());
                    ((ServiceBuyByEntContentViewHolder) helper).serviceBuyContentPrice.setMoney(item.getBuyContent().getPriceBean().getPrice(), "/" +
                            item.getBuyContent().getPriceBean().getUnit());
                    ((ServiceBuyByEntContentViewHolder) helper).serviceBuyContentLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onSelectPriceClickListener != null) {
                                onSelectPriceClickListener.onClick(v);
                            }
                        }
                    });
                    ((ServiceBuyByEntContentViewHolder) helper).serviceBuyContentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                    if (item.getBuyContent().getServiceType() == EnumServiceType.Subject) {
                        ((ServiceBuyByEntContentViewHolder) helper).serviceBuyTopicLayout.setVisibility(View.VISIBLE);
                        ((ServiceBuyByEntContentViewHolder) helper).serviceBuyTopicName.setText("");
                        for (ServiceBuyByEntEntity.TopiceService service : item.getBuyContent().getTopiceServices()) {
                            ((ServiceBuyByEntContentViewHolder) helper).serviceBuyTopicName.append(service.getServiceName() + " ");
                        }
                    } else {
                        ((ServiceBuyByEntContentViewHolder) helper).serviceBuyTopicLayout.setVisibility(View.GONE);
                    }
                }
                break;
            case ServiceBuyByEntEntity.TYPE_PAY:
                if (helper instanceof ServiceBuyByEntPayViewHolder) {
                    ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixinLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixin.setChecked(!((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixin.isChecked());
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpay.setChecked(false);
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBank.setChecked(false);
                            if (((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixin.isChecked())
                                item.setBuyType(EnumPayType.PAY_WEIXIN);
                            else
                                item.setBuyType(EnumPayType.PAY_DEFAULT);
                            ErayicLog.e("当前状态：" + item.getBuyType());
                        }
                    });
                    ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixinLayout.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });


                    ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpayLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixin.setChecked(false);
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpay.setChecked(!((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpay.isChecked());
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBank.setChecked(false);
                            if (((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpay.isChecked())
                                item.setBuyType(EnumPayType.PAY_ALPAY);
                            else
                                item.setBuyType(EnumPayType.PAY_DEFAULT);
                            ErayicLog.e("当前状态：" + item.getBuyType());
                        }
                    });
                    ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpayLayout.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });

                    ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBankLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayWeixin.setChecked(false);
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayAlpay.setChecked(false);
                            ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBank.setChecked(!((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBank.isChecked());
                            if (((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBank.isChecked())
                                item.setBuyType(EnumPayType.PAY_BANK);
                            else
                                item.setBuyType(EnumPayType.PAY_DEFAULT);
                            ErayicLog.e("当前状态：" + item.getBuyType());
                        }
                    });
                    ((ServiceBuyByEntPayViewHolder) helper).serviceBuyPayBankLayout.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    public interface OnSelectPriceClickListener {
        void onClick(View view);
    }

}
