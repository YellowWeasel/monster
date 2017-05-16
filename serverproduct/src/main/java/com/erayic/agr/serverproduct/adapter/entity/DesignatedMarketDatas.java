package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    public DesignatedMarketDatas(List<CommonMarketDynamicPriceBean> beans,String mMarketName) {
        this.marketName=mMarketName;
        if (beans==null)return;
        xDatas=new String[beans.size()];
        marketDatas=new String[beans.size()];
        SimpleDateFormat format= new SimpleDateFormat("M月d日");
        for (int i=0;i<beans.size();i++){
            String strDate=beans.get(i).getKey();
            xDatas[i]=format.
                    format(new Date(Long.valueOf(strDate.
                            substring(strDate.indexOf("(")+1,strDate.indexOf(")")))));
            marketDatas[i]=beans.get(i).getValue();
        }
        cropName="大白菜";
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
