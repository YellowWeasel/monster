package com.erayic.agr.serverproduct.presenter;

/**
 * Created by wxk on 2017/5/11.
 */

public interface IDynamicPriceDetailPresenter {
    void getMarketDatas(int cropId,String marketName, String start, String end,String serviceId);
}
