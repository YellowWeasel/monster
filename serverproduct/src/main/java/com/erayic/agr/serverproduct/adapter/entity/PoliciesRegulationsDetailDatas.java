package com.erayic.agr.serverproduct.adapter.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;
import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsDetailBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wxk on 2017/5/8.
 */
public class PoliciesRegulationsDetailDatas{
     private int Id;
     private String Title;
     private String TxtContent;
     private String infoSource;
     private String PublishTime;
     public PoliciesRegulationsDetailDatas(CommonPoliciesRegulationsDetailBean bean) {
          this.Id=bean.getId();
          this.Title=bean.getTitle();
          this.TxtContent=bean.getTxtContent();
          this.infoSource=bean.getInfoSource();
          String strDate=bean.getPublishTime();
          this.PublishTime=new SimpleDateFormat("yyyy-MM-dd HH:mm").
                  format(new Date(Long.valueOf(strDate.
                          substring(strDate.indexOf("(")+1,strDate.indexOf(")")))));

     }

     public PoliciesRegulationsDetailDatas() {
     }

     public PoliciesRegulationsDetailDatas(int id, String title, String txtContent,
                                           String infoSource, String publishTime) {
          Id = id;
          Title = title;
          TxtContent = txtContent;
          this.infoSource = infoSource;
          PublishTime = publishTime;
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
}
