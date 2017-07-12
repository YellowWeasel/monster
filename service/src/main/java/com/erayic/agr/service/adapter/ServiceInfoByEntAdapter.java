package com.erayic.agr.service.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.entity.ServiceInfoByEntEntity;
import com.erayic.agr.service.adapter.holder.ServiceInfoByEntDescriptViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceInfoByEntImagesViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceInfoByEntPriceViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceInfoByEntTitleViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntAdapter extends BaseMultiItemQuickAdapter<ServiceInfoByEntEntity, BaseViewHolder> {

    private Context context;
    private OnBuyClickListener onBuyClickListener;

    public ServiceInfoByEntAdapter(Context context, List<ServiceInfoByEntEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnBuyClickListener(OnBuyClickListener onBuyClickListener) {
        this.onBuyClickListener = onBuyClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ServiceInfoByEntEntity.TYPE_TITLE:
                return new ServiceInfoByEntTitleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_info_ent_title, parent, false));
            case ServiceInfoByEntEntity.TYPE_DESCRIPT:
                return new ServiceInfoByEntDescriptViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_info_ent_descript, parent, false));
            case ServiceInfoByEntEntity.TYPE_PRICE:
                return new ServiceInfoByEntPriceViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_info_ent_price, parent, false));
            case ServiceInfoByEntEntity.TYPE_IMAGES:
                return new ServiceInfoByEntImagesViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_info_ent_image, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, final ServiceInfoByEntEntity item) {
        switch (helper.getItemViewType()) {
            case ServiceInfoByEntEntity.TYPE_TITLE:
                if (helper instanceof ServiceInfoByEntTitleViewHolder) {
                    Glide.with(context).load(item.getTitle().getServiceIcon()).apply(AgrConstant.iconOptions).into(((ServiceInfoByEntTitleViewHolder) helper).serviceInfoIcon);
                    ((ServiceInfoByEntTitleViewHolder) helper).serviceInfoName.setText(item.getTitle().getServiceName());
                }
                break;
            case ServiceInfoByEntEntity.TYPE_DESCRIPT:
                if (helper instanceof ServiceInfoByEntDescriptViewHolder) {
                    ((ServiceInfoByEntDescriptViewHolder) helper).serviceInfoDescrip.setText("\u3000\u3000" + item.getDescript().getServiceDescript() + "");
                }
                break;
            case ServiceInfoByEntEntity.TYPE_PRICE:
                if (helper instanceof ServiceInfoByEntPriceViewHolder) {
                    if (item.getPrice().getServicePrice() == null) {
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoPrice.setText("服务价格：免费");
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoBuy.setVisibility(View.GONE);
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoDueDate.setVisibility(View.GONE);
                    } else {
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoPrice.setText("服务价格：￥" + item.getPrice().getServicePrice().getPrice() + "/" + item.getPrice().getServicePrice().getUnit());
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoBuy.setVisibility(View.VISIBLE);
                        if (item.getPrice().isBuy()) {
                            ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoBuy.setText("续购");
                            ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoDueDate.setVisibility(View.VISIBLE);
                            ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoDueDate.setText("到期时间：" + ErayicNetDate.getStringDate(item.getPrice().getDueDate()) + "");
                        } else {
                            ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoBuy.setText("购买");
                            ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoDueDate.setVisibility(View.GONE);
                        }
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoBuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (onBuyClickListener != null)
                                    onBuyClickListener.onBuyClick(v, item.getPrice().getServicePrice());
                            }
                        });
                        ((ServiceInfoByEntPriceViewHolder) helper).serviceInfoBuy.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                return true;
                            }
                        });
                    }
                }
                break;
            case ServiceInfoByEntEntity.TYPE_IMAGES:
                if (helper instanceof ServiceInfoByEntImagesViewHolder) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    ((ServiceInfoByEntImagesViewHolder) helper).serviceInfoRecyclerView.setLayoutManager(linearLayoutManager);
                    ServiceInfoByEntImageAdapter adapter = new ServiceInfoByEntImageAdapter(context, item.getImageBeanList());
                    ((ServiceInfoByEntImagesViewHolder) helper).serviceInfoRecyclerView.setAdapter(adapter);
                }
                break;
            default:
                break;
        }
    }

    public interface OnBuyClickListener {
        void onBuyClick(View view, CommonPriceBean bean);
    }
}
