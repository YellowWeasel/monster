package com.erayic.agr.common.net.back.api;

/**
 * Created by wxk on 2017/5/19.
 */

public class CommonAgriculturalinfoDetailBean {
    private int Id;
    private String Title;
    private String TxtContent;
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

    public CommonAgriculturalinfoDetailBean() {
    }

    public CommonAgriculturalinfoDetailBean(int id, String title, String txtContent,
                                            String infoSource, String publishTime) {
        Id = id;
        Title = title;
        TxtContent = txtContent;
        InfoSource = infoSource;
        PublishTime = publishTime;
    }
}
