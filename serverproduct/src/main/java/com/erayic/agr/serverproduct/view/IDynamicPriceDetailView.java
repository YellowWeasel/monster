package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.serverproduct.adapter.entity.DesignatedMarketDatas;
import com.erayic.agr.serverproduct.adapter.entity.MarketDynamicPriceDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/16.
 */

public interface IDynamicPriceDetailView extends IBaseView{
     void refreshMarketDynamicPrices(MarketDynamicPriceDatas beans);
    void showLoading();
    void dismissLoading();
}
