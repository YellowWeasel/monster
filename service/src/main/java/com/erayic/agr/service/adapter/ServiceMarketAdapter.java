package com.erayic.agr.service.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServiceMarketBannerViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceMarketItemViewHolder;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceMarketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_BANBER = 0;
    private final static int TYPE_ITEM = 1;

    private Context context;

    private ServiceSystemBean bean;

    private OnItemClickListener onItemClickListener;

    private List<String> urlList = new ArrayList<String>() {{
        add("http://www.farmer.com.cn/tppd/snjj/201704/W020170411356976960828.jpg");
        add("http://www.farmer.com.cn/tppd/snjj/201704/W020170411356976963718.jpg");
        add("http://www.farmer.com.cn/tppd/snjj/201704/W020170411356977127485.jpg");
    }};

    public ServiceMarketAdapter(Context context) {
        this.context = context;
    }

    public void setList(ServiceSystemBean bean) {
        bean.getServices().add(bean.getServices().get(0));
        bean.getServices().add(bean.getServices().get(0));
        bean.getServices().add(bean.getServices().get(0));
        this.bean = bean;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANBER) {
            return new ServiceMarketBannerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_market_manner, parent, false));
        } else {
            return new ServiceMarketItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_market_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ServiceMarketBannerViewHolder) {
            ((ServiceMarketBannerViewHolder) holder).serviceMarketBanner.setData(urlList, null);
            ((ServiceMarketBannerViewHolder) holder).serviceMarketBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(urlList.get(position)).into((ImageView) view);
                }
            });
        } else if (holder instanceof ServiceMarketItemViewHolder) {
            ((ServiceMarketItemViewHolder) holder).serviceMarketItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(v, bean.getServices().get(position - 1));
                }
            });
            ((ServiceMarketItemViewHolder) holder).serviceMarketItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            ((ServiceMarketItemViewHolder) holder).serviceMarketItemName.setText(bean.getServices().get(position - 1).getServiceName());
            ((ServiceMarketItemViewHolder) holder).serviceMarketItemDirections.setText(bean.getServices().get(position - 1).getDescript());
            if (bean.getServices().get(position - 1).getMasterPrice().getPriceID() == 0) {
                ((ServiceMarketItemViewHolder) holder).serviceMarketItemPrice.setText("免费");
            } else {
                ((ServiceMarketItemViewHolder) holder).serviceMarketItemPrice.setText(bean.getServices().get(position - 1).getMasterPrice().getPrice() + "/" +
                        bean.getServices().get(position - 1).getMasterPrice().getUnit());
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANBER;
        } else
            return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getServices().size() + 1;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ServiceSystemBean.ServicesInfo servicesInfo);
    }
}
