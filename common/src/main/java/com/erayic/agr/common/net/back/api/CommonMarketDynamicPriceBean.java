package com.erayic.agr.common.net.back.api;

/**
 * Created by wxk on 2017/5/16.
 */

public class CommonMarketDynamicPriceBean {
        private String Key;
        private String Value;

    public CommonMarketDynamicPriceBean() {
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

    public CommonMarketDynamicPriceBean(String key, String value) {
        Key = key;
        Value = value;
    }
}
