package com.erayic.agr.common.net.back.api;

/**
 * Created by wxk on 2017/5/12.
 */

public class   CommonPoliciesRegulationsDetailBean{
    private int Id;
    private String Title;
    private String TxtContent;
    private String infoSource;
    private String PublishTime;

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
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public CommonPoliciesRegulationsDetailBean() {
    }

    public CommonPoliciesRegulationsDetailBean(int id, String title, String txtContent, String infoSource, String publishTime) {
        Id = id;
        Title = title;
        TxtContent = txtContent;
        this.infoSource = infoSource;
        PublishTime = publishTime;
    }
}
