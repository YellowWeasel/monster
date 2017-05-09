package com.erayic.agr.serverproduct.adapter.entity;

/**
 * Created by wxk on 2017/5/8.
 */

public class PoliciesRegulationsDatas {
     private String PoliciesRegulationName;
     private String Title;
     private String MainBody;
     private String PublishDate;

     public String getPoliciesRegulationName() {
          return PoliciesRegulationName;
     }

     public void setPoliciesRegulationName(String policiesRegulationName) {
          PoliciesRegulationName = policiesRegulationName;
     }

     public String getTitle() {
          return Title;
     }

     public void setTitle(String title) {
          Title = title;
     }

     public String getMainBody() {
          return MainBody;
     }

     public void setMainBody(String mainBody) {
          MainBody = mainBody;
     }

     public String getPublishDate() {
          return PublishDate;
     }

     public void setPublishDate(String publishDate) {
          PublishDate = publishDate;
     }
}
