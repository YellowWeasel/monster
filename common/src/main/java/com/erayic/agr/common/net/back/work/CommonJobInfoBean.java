package com.erayic.agr.common.net.back.work;

import com.erayic.agr.common.net.back.CommonMapArrayBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonJobInfoBean {

    private String SchID;//调度ID
    private String JobID;//作业ID
    private String DemandTime;//建议执行时间
    private List<CommonMapArrayBean> UnitIDs;//管理单元ID
    private int NoticeHour;//提醒时间
    private int Tips;//提醒方式
    private String CreateTime;//建立时间
    private String Creater;//建立人

    public String getSchID() {
        return SchID;
    }

    public void setSchID(String schID) {
        SchID = schID;
    }

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public String getDemandTime() {
        return DemandTime;
    }

    public void setDemandTime(String demandTime) {
        DemandTime = demandTime;
    }

    public List<CommonMapArrayBean> getUnitIDs() {
        return UnitIDs;
    }

    public void setUnitIDs(List<CommonMapArrayBean> unitIDs) {
        UnitIDs = unitIDs;
    }

    public int getNoticeTime() {
        return NoticeHour;
    }

    public void setNoticeTime(int noticeTime) {
        NoticeHour = noticeTime;
    }

    public int getTips() {
        return Tips;
    }

    public void setTips(int tips) {
        Tips = tips;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreater() {
        return Creater;
    }

    public void setCreater(String creater) {
        Creater = creater;
    }
}
