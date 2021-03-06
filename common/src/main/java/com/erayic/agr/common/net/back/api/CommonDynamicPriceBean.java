package com.erayic.agr.common.net.back.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxk  on 2017/5/11.
 */

public class CommonDynamicPriceBean {
     private List<DynamicPriceBean> Nation;
     private List<DynamicPriceBean> Provice;
     private List<PrincipalMarketPrice> Market;


    public List<PrincipalMarketPrice> getMarket() {
        return Market;
    }

    public void setMarket(List<PrincipalMarketPrice> market) {
        Market = market;
    }

    public CommonDynamicPriceBean(List<DynamicPriceBean> nation, List<DynamicPriceBean> provice,
                                  List<PrincipalMarketPrice> market) {
        Nation = nation;
        Provice = provice;
        Market = market;
    }

    public List<DynamicPriceBean> getNation() {
        return Nation;
    }

    public void setNation(List<DynamicPriceBean> nation) {
        Nation = nation;
    }

    public List<DynamicPriceBean> getProvice() {
        return Provice;
    }

    public void setProvice(List<DynamicPriceBean> provice) {
        Provice = provice;
    }

    public    static  class  DynamicPriceBean{
        private String Key;
        private String Value;

        public DynamicPriceBean() {
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

        public DynamicPriceBean(String key, String value) {
            Key = key;
            Value = value;
        }
    }
 public    static class PrincipalMarketPrice{
           private String Key;
          private MarketPriceInfo Value;

     public MarketPriceInfo getValue() {
         return Value;
     }

     public void setValue(MarketPriceInfo value) {
         Value = value;
     }

     public PrincipalMarketPrice() {
     }

     public String getKey() {
         return Key;
     }

     public void setKey(String key) {
         Key = key;
     }

     public PrincipalMarketPrice(String key, MarketPriceInfo value) {
         Key = key;
         Value = value;
     }
 }
 public static  class  MarketPriceInfo{
     private String Key;
     private String Value;

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

     public MarketPriceInfo() {
     }

     public MarketPriceInfo(String key, String value) {
         Key = key;
         Value = value;
     }
 }

}
