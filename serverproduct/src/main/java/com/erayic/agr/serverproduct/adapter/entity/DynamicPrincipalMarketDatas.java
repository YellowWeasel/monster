package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;

import java.util.List;

/**
 * Created by wxk on 2017/5/12.
 */

public class DynamicPrincipalMarketDatas {
    private String[] xDatas;
    private MarketPrice[] yDatas;

    public DynamicPrincipalMarketDatas(CommonDynamicPriceBean bean) {
        int maxSize=0;
        CommonDynamicPriceBean.PrincipalMarketPrice maxSizPrices=new CommonDynamicPriceBean.PrincipalMarketPrice();
        List<CommonDynamicPriceBean.PrincipalMarketPrice> marketPrices = bean.getMarket();
        if (marketPrices == null) {
            yDatas = new MarketPrice[0];
            xDatas = new String[0];
            return;
        }
        yDatas = new MarketPrice[marketPrices.size()];
        for (CommonDynamicPriceBean.PrincipalMarketPrice price:marketPrices){
            if (price.getPriceBeens().size()>maxSize)
            {
                maxSize=price.getPriceBeens().size();
                maxSizPrices=price;
            }
        }
        xDatas = new String[maxSizPrices.getPriceBeens().size()];
        for (int i=0;i<xDatas.length;i++){
            xDatas[i]=maxSizPrices.getPriceBeens().get(i).getKey();
        }
        for (int i = 0; i < marketPrices.size(); i++) {
            yDatas[i] = new MarketPrice(marketPrices.get(i),maxSize);
        }
    }

    public String[] getxDatas() {
        return xDatas;
    }

    public void setxDatas(String[] xDatas) {
        this.xDatas = xDatas;
    }

    public MarketPrice[] getyDatas() {
        return yDatas;
    }

    public void setyDatas(MarketPrice[] yDatas) {
        this.yDatas = yDatas;
    }

    public DynamicPrincipalMarketDatas() {
    }

    public DynamicPrincipalMarketDatas(String[] xDatas, MarketPrice[] yDatas) {
        this.xDatas = xDatas;
        this.yDatas = yDatas;
    }

    static class MarketPrice {
        private String name;
        private String[] datas;

        public MarketPrice(CommonDynamicPriceBean.PrincipalMarketPrice principalMarketPrice,int size) {
            if (principalMarketPrice == null||principalMarketPrice.getPriceBeens()==null)return;
            this.name = principalMarketPrice.getKey();
            datas = new String[size];
            for (int i = 0; i < datas.length; i++) {
                if (principalMarketPrice.getPriceBeens().size()>i){
                    datas[i]=principalMarketPrice.getPriceBeens().get(i).getValue();
                }
            }
        }

        public MarketPrice() {
        }

        public MarketPrice(String name, String[] datas) {
            this.name = name;
            this.datas = datas;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getDatas() {
            return datas;
        }

        public void setDatas(String[] datas) {
            this.datas = datas;
        }
    }
}
