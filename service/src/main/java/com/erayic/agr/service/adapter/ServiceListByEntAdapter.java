package com.erayic.agr.service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.CommonImageBean;
import com.erayic.agr.common.util.ErayicTextUtil;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.entity.ServiceListByEntEntity;
import com.erayic.agr.service.adapter.holder.ServiceListByEntBannerViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceListByEntItemViewHolder;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceListByEntAdapter extends BaseMultiItemQuickAdapter<ServiceListByEntEntity, BaseViewHolder> {

    private Context context;

    private OnItemServiceMarketClickListener onItemServiceMarketClickListener;
    private OnBannerServiceMarketClickListener onBannerServiceMarketClickListener;

    private List<String> urlList = new ArrayList<String>() {{
        add("http://www.farmer.com.cn/tppd/snjj/201704/W020170411356976960828.jpg");
        add("http://www.farmer.com.cn/tppd/snjj/201704/W020170411356976963718.jpg");
        add("http://www.farmer.com.cn/tppd/snjj/201704/W020170411356977127485.jpg");
    }};

    public ServiceListByEntAdapter(Context context, List<ServiceListByEntEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemServiceMarketClickListener(OnItemServiceMarketClickListener onItemServiceMarketClickListener) {
        this.onItemServiceMarketClickListener = onItemServiceMarketClickListener;
    }

    public void setOnBannerServiceMarketClickListener(OnBannerServiceMarketClickListener onBannerServiceMarketClickListener) {
        this.onBannerServiceMarketClickListener = onBannerServiceMarketClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ServiceListByEntEntity.TYPE_BANNER:
                return new ServiceListByEntBannerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_list_manner, parent, false));
            case ServiceListByEntEntity.TYPE_ITEM:
                return new ServiceListByEntItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_list_item, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, final ServiceListByEntEntity item) {
        switch (helper.getItemViewType()) {
            case ServiceListByEntEntity.TYPE_BANNER:
                if (helper instanceof ServiceListByEntBannerViewHolder) {
                    ((ServiceListByEntBannerViewHolder) helper).serviceMarketBanner.setData(urlList, null);
                    ((ServiceListByEntBannerViewHolder) helper).serviceMarketBanner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            Glide.with(context).load(urlList.get(position)).into((ImageView) view);
                        }
                    });
                    ((ServiceListByEntBannerViewHolder) helper).serviceMarketBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(XBanner banner, int position) {
                            if (onBannerServiceMarketClickListener != null)
                                onBannerServiceMarketClickListener.onItemClick(banner, position, new CommonImageBean());//测试
                        }
                    });
                }
                break;
            case ServiceListByEntEntity.TYPE_ITEM:
                if (helper instanceof ServiceListByEntItemViewHolder) {
                    ((ServiceListByEntItemViewHolder) helper).serviceMarketItemLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemServiceMarketClickListener != null)
                                onItemServiceMarketClickListener.onItemClick(v, item);
                        }
                    });
                    ((ServiceListByEntItemViewHolder) helper).serviceMarketItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                    Glide.with(context).load(AgrConstant.IMAGE_URL_PREFIX + item.getServicesInfo().getIcon())
                            .error(R.drawable.image_service_test)
                            .into(((ServiceListByEntItemViewHolder) helper).serviceMarketItemImg);
                    ((ServiceListByEntItemViewHolder) helper).serviceMarketItemName.setText(item.getServicesInfo().getServiceName());
                    ((ServiceListByEntItemViewHolder) helper).serviceMarketItemDirections.setText(item.getServicesInfo().getDescript());
                    if (item.getServicesInfo().getMasterPrice().getPriceID() == 0) {
                        ((ServiceListByEntItemViewHolder) helper).serviceMarketItemPrice.setMoney(0, "免费");
                    } else {
                        ((ServiceListByEntItemViewHolder) helper).serviceMarketItemPrice.setMoney(item.getServicesInfo().getMasterPrice().getPrice(), "/" + item.getServicesInfo().getMasterPrice().getUnit());
                    }
                }
                break;
        }

    }

    public interface OnItemServiceMarketClickListener {
        void onItemClick(View view, ServiceListByEntEntity entity);
    }

    public interface OnBannerServiceMarketClickListener {
        void onItemClick(View view, int position, CommonImageBean bean);
    }
}
