package com.erayic.agr.serverproduct.adapter.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.erayic.agr.common.net.back.api.CommonPoliciesRegulationsBean;

/**
 * Created by wxk on 2017/5/8.
 */
public class PoliciesRegulationsDetailDatas implements Parcelable {
     private int PoliciesId;
     private String PID;
     private String Title;
     private String TxtContent;
     private String infoSource;
     private String PublishTime;

     public PoliciesRegulationsDetailDatas(CommonPoliciesRegulationsBean bean) {
          this.PoliciesId=Integer.valueOf(bean.getPoliciesId());
          this.PID=bean.getPID();
          this.Title=bean.getTitle();
          this.TxtContent=bean.getTxtContent();
          this.infoSource=bean.getInfoSource();
          this.PublishTime=bean.getPublishTime();
     }

     protected PoliciesRegulationsDetailDatas(Parcel in) {
          PoliciesId = in.readInt();
          PID = in.readString();
          Title = in.readString();
          TxtContent = in.readString();
          infoSource = in.readString();
          PublishTime = in.readString();
     }

     public static final Creator<PoliciesRegulationsDetailDatas> CREATOR = new Creator<PoliciesRegulationsDetailDatas>() {
          @Override
          public PoliciesRegulationsDetailDatas createFromParcel(Parcel in) {
               return new PoliciesRegulationsDetailDatas(in);
          }

          @Override
          public PoliciesRegulationsDetailDatas[] newArray(int size) {
               return new PoliciesRegulationsDetailDatas[size];
          }
     };

     public int getPoliciesId() {
          return PoliciesId;
     }

     public void setPoliciesId(int policiesId) {
          PoliciesId = policiesId;
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

     public PoliciesRegulationsDetailDatas() {
     }



     public String getPID() {
          return PID;
     }

     public void setPID(String PID) {
          this.PID = PID;
     }

     public PoliciesRegulationsDetailDatas(int policiesId, String PID, String title,
                                           String txtContent, String infoSource, String publishTime) {
          PoliciesId = policiesId;
          this.PID = PID;
          Title = title;
          TxtContent = txtContent;
          this.infoSource = infoSource;
          PublishTime = publishTime;
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
          dest.writeInt(PoliciesId);
          dest.writeString(PID);
          dest.writeString(Title);
          dest.writeString(TxtContent);
          dest.writeString(infoSource);
          dest.writeString(PublishTime);
     }
}
