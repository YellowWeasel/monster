package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxk on 2017/5/13.
 */

public class DynamicPricePrincipalMarketDatas  {
        private List<MarketPriceDatas> marketPriceDatasList;

    public DynamicPricePrincipalMarketDatas(CommonDynamicPriceBean bean) {
        marketPriceDatasList=new ArrayList<>();
        List<CommonDynamicPriceBean.PrincipalMarketPrice> markets = bean.getMarket();
        for (CommonDynamicPriceBean.PrincipalMarketPrice value:markets){
            marketPriceDatasList.add(new MarketPriceDatas(value));
        }
    }

    public List<MarketPriceDatas> getMarketPriceDatasList() {
        return marketPriceDatasList;
    }

    public void setMarketPriceDatasList(List<MarketPriceDatas> marketPriceDatasList) {
        this.marketPriceDatasList = marketPriceDatasList;
    }

    public DynamicPricePrincipalMarketDatas(List<MarketPriceDatas> marketPriceDatasList) {
        this.marketPriceDatasList = marketPriceDatasList;
    }
    public static  class MarketPriceDatas{
            private String MarketName;
            private String Value;
            private String Date;

        public MarketPriceDatas(CommonDynamicPriceBean.PrincipalMarketPrice value) {
            this.MarketName=value.getKey();
            this.Value=format2(value.getValue().getValue());
            String strDate=value.getValue().getKey();
            this.Date=new SimpleDateFormat("MM.dd").
                    format(new Date(Long.valueOf(strDate.
                            substring(strDate.indexOf("(")+1,strDate.indexOf(")")))));
        }
        public  String format2(String value) {
            BigDecimal format=new BigDecimal(value);
            return format.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public MarketPriceDatas(String marketName, String value, String date) {
            MarketName = marketName;
            Value = value;
            Date = date;
        }

        public String getMarketName() {
                return MarketName;
            }

            public void setMarketName(String marketName) {
                MarketName = marketName;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String value) {
                Value = value;
            }
        }
}
