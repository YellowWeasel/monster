package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.serverproduct.adapter.entity.DesignatedMarketDatas;

/**
 * Created by wxk on 2017/5/16.
 */

public interface IDynamicPriceDetailView extends IBaseView{
     void refreshMarketDynamicPrices(DesignatedMarketDatas datas);
    void showLoading();
    void dismissLoading();
}
