package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.serverproduct.TextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxk on 2017/5/16.
 */

public class MarketDynamicPriceDatas {
    private List<priceDatas> priceDatasList;

    public List<priceDatas> getPriceDatasList() {
        return priceDatasList;
    }

    public void setPriceDatasList(List<priceDatas> priceDatasList) {
        this.priceDatasList = priceDatasList;
    }

    public MarketDynamicPriceDatas(List<CommonMarketDynamicPriceBean> beans) {
        this.priceDatasList=new ArrayList<>();
        for (CommonMarketDynamicPriceBean bean :beans){
            priceDatasList.add(new priceDatas(bean));
        }
    }

    public static  class  priceDatas{
        private String Key;
        private String Value;

        public priceDatas() {
        }

        public priceDatas(CommonMarketDynamicPriceBean bean) {
            String strDate=bean.getKey().substring(bean.getKey().indexOf("(")+1,bean.getKey().indexOf(")"));
            this.Key=new SimpleDateFormat("MM.dd").format(new Date(Long.valueOf(strDate)));
            this.Value= TextUtils.FormatNumber(bean.getValue(),2);
        }

        public priceDatas(String key, String value) {
            Key = key;
            Value = value;
        }

        public String getKey() {
            return Key;
        }

        public void setKey(String key) {
            Key = key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

    }
}
