package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by wxk on 2017/5/16.
 */

public class DesignatedMarketDatas {
    private String marketName;
    private String cropName;
    private String[] xDatas;
    private String[] marketDatas;

    public DesignatedMarketDatas(MarketDynamicPriceDatas beans, String mMarketName,String cropName) {
        this.marketName = mMarketName;
        if (beans == null&&beans.getPriceDatasList()!=null) return;
        xDatas = new String[beans.getPriceDatasList().size()];
        marketDatas = new String[beans.getPriceDatasList().size()];
        SimpleDateFormat format = new SimpleDateFormat("M月d日");
        for (int i = 0; i < beans.getPriceDatasList().size(); i++) {
            xDatas[i] = beans.getPriceDatasList().get(i).getKey();
            marketDatas[i] = beans.getPriceDatasList().get(i).getValue();
        }
        this.cropName = cropName; //没有作物名称
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String[] getxDatas() {
        return xDatas;
    }

    public void setxDatas(String[] xDatas) {
        this.xDatas = xDatas;
    }

    public String[] getMarketDatas() {
        return marketDatas;
    }

    public void setMarketDatas(String[] marketDatas) {
        this.marketDatas = marketDatas;
    }

    public DesignatedMarketDatas(String marketName, String[] xDatas, String[] marketDatas) {
        this.marketName = marketName;
        this.xDatas = xDatas;
        this.marketDatas = marketDatas;
    }
}
