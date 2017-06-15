package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonAgriculturalInfoBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wxk on 2017/5/19.
 */

public class AgriculturalInfoDatas {
    private int Id;
    private String Title;
    private String InfoSource;
    private String PublishTime;
    public AgriculturalInfoDatas(CommonAgriculturalInfoBean bean) {
        this.Id=bean.getId();
        this.Title=bean.getTitle();
        this.InfoSource=bean.getInfoSource();
        String strDate=bean.getPublishTime().substring(bean.getPublishTime().indexOf("(")+1,bean.getPublishTime().indexOf(")"));
        this.PublishTime=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(Long.parseLong(strDate)));
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getInfoSource() {
        return InfoSource;
    }

    public void setInfoSource(String infoSource) {
        InfoSource = infoSource;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }



    public AgriculturalInfoDatas(int id, String title, String infoSource, String publishTime) {
        Id = id;
        Title = title;
        InfoSource = infoSource;
        PublishTime = publishTime;
    }
}
