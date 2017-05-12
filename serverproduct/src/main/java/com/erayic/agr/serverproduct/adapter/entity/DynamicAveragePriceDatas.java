package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wxk on 2017/5/12.
 */

public class DynamicAveragePriceDatas {
    private String[] nationalDatas;
    private String[] hnDatas;
    private String[] xDatas;

    public DynamicAveragePriceDatas(String[] nationalDatas, String[] hnDatas, String[] xDatas) {
        this.nationalDatas = nationalDatas;
        this.hnDatas = hnDatas;
        this.xDatas = xDatas;
    }

    public DynamicAveragePriceDatas() {
    }

    public DynamicAveragePriceDatas(CommonDynamicPriceBean bean) {
        List<CommonDynamicPriceBean.DynamicPriceBean>  national=bean.getNation();
        List<CommonDynamicPriceBean.DynamicPriceBean>  hn=bean.getProvice();
        int size=0;
        if (national!=null){
            size=national.size();
        }
        if (hn!=null){
            size=(hn.size()>size)?hn.size():size;
        }
        nationalDatas=new String[size];
        hnDatas=new String[size];
        xDatas=new String[size];
        for (int i=0;i<size;i++){
            if (hn!=null && i<hn.size()){
                hnDatas[i]=formatScale(hn.get(i).getValue(),2);
            }
            if (national!=null && i<national.size()){
                nationalDatas[i]=formatScale(national.get(i).getValue(),2);
            }
            String strDate;
            if (hn!=null&&hn.size()==size){
                 strDate=hn.get(i).getKey();
            }else{
                 strDate=national.get(i).getKey();

            }
            xDatas[i]=(new SimpleDateFormat("M.dd").
                    format(new Date(Long.valueOf(strDate.
                            substring(strDate.indexOf("(")+1,strDate.indexOf(")"))))));
        }
    }
    public  String formatScale(String value,int scale) {
        BigDecimal format=new BigDecimal(value);
        return format.setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }

    public String[] getxDatas() {
        return xDatas;
    }

    public void setxDatas(String[] xDatas) {
        this.xDatas = xDatas;
    }

    public String[] getNationalDatas() {
        return nationalDatas;
    }

    public void setNationalDatas(String[] nationalDatas) {
        this.nationalDatas = nationalDatas;
    }

    public String[] getHnDatas() {
        return hnDatas;
    }

    public void setHnDatas(String[] hnDatas) {
        this.hnDatas = hnDatas;
    }
}
