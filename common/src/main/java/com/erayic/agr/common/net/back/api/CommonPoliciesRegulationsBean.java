package com.erayic.agr.common.net.back.api;

/**
 * Created by wxk on 2017/5/11.
 */

public class CommonPoliciesRegulationsBean {
   private int Id;
    private String Title;
    private String InfoSource;
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

    public CommonPoliciesRegulationsBean() {
    }

    public CommonPoliciesRegulationsBean(int id, String title, String infoSource, String publishTime) {
        Id = id;
        Title = title;
        InfoSource = infoSource;
        PublishTime = publishTime;
    }
}
