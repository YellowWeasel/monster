package com.erayic.agr.service.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.erayic.agr.service.R2;
import com.stx.xhb.xbanner.XBanner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceMarketBannerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.service_market_banner)
    public XBanner serviceMarketBanner;

    public ServiceMarketBannerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
