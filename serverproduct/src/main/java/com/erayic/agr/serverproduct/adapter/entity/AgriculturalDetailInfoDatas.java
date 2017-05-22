package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.common.net.back.api.CommonAgriculturalinfoDetailBean;

import java.text.SimpleDateFormat;

/**
 * Created by wxk on 2017/5/19.
 */

public class AgriculturalDetailInfoDatas {
    private int Id;
    private String Title;
    private String TxtContent;
    private String InfoSource;
    private String PublishTime;
    public AgriculturalDetailInfoDatas(CommonAgriculturalinfoDetailBean bean) {
        this.Id=bean.getId();
        this.Title=bean.getTitle();
        this.TxtContent=bean.getTxtContent();
        this.InfoSource=bean.getInfoSource();
        String strDate=bean.getPublishTime();
        this.PublishTime=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(strDate);
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

    public String getTxtContent() {
        return TxtContent;
    }

    public void setTxtContent(String txtContent) {
        TxtContent = txtContent;
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

    public AgriculturalDetailInfoDatas(int id, String title, String txtContent,
                                       String infoSource, String publishTime) {
        Id = id;
        Title = title;
        TxtContent = txtContent;
        InfoSource = infoSource;
        PublishTime = publishTime;
    }
}
