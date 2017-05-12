package com.erayic.agr.common.net.back.api;

/**
 * Created by wxk on 2017/5/11.
 */

public class CommonPoliciesRegulationsBean {
    private String PoliciesId;
    private String PID;
    private String Title;
    private String TxtContent;
    private String infoSource;
    private String PublishTime;

    public String getPoliciesId() {
        return PoliciesId;
    }

    public void setPoliciesId(String policiesId) {
        PoliciesId = policiesId;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
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

    public CommonPoliciesRegulationsBean() {
    }

    public CommonPoliciesRegulationsBean(String policiesId, String PID,
                                         String title, String txtContent,
                                         String infoSource, String publishTime) {
        PoliciesId = policiesId;
        this.PID = PID;
        Title = title;
        TxtContent = txtContent;
        this.infoSource = infoSource;
        PublishTime = publishTime;
    }
}
