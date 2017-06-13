package com.erayic.agr.common.net.back.work;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonJobsListManagerBean {

    private String Day;
    private List<JobsInfo> Jobs;

    public static class JobsInfo {
        private String JobName;
        private String JobID;
        private List<JobsEntity> Jobs;//工作内容
        private int percentage;//完成百分比

        public String getJobName() {
            return JobName;
        }

        public void setJobName(String jobName) {
            JobName = jobName;
        }

        public String getJobID() {
            return JobID;
        }

        public void setJobID(String jobID) {
            JobID = jobID;
        }

        public List<JobsEntity> getJobs() {
            return Jobs;
        }

        public void setJobs(List<JobsEntity> jobs) {
            Jobs = jobs;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }
    }

    public static class JobsEntity {
        private String WorkID;//作业ID
        private String SchID;//调度ID
        private String UnitID;//管理单元ID
        private boolean IsFinish;//是否完成
        private String FinishTime;//完成时间
        private List<OperatersInfo> Operaters;//完成用户
        private RecorderInfo Recorder;//工作记录

        public String getWorkID() {
            return WorkID;
        }

        public void setWorkID(String workID) {
            WorkID = workID;
        }

        public String getSchID() {
            return SchID;
        }

        public void setSchID(String schID) {
            SchID = schID;
        }

        public String getUnitID() {
            return UnitID;
        }

        public void setUnitID(String unitID) {
            UnitID = unitID;
        }

        public boolean isFinish() {
            return IsFinish;
        }

        public void setFinish(boolean finish) {
            IsFinish = finish;
        }

        public String getFinishTime() {
            return FinishTime;
        }

        public void setFinishTime(String finishTime) {
            FinishTime = finishTime;
        }

        public List<OperatersInfo> getOperaters() {
            return Operaters;
        }

        public void setOperaters(List<OperatersInfo> operaters) {
            Operaters = operaters;
        }

        public RecorderInfo getRecorder() {
            return Recorder;
        }

        public void setRecorder(RecorderInfo recorder) {
            Recorder = recorder;
        }
    }

    public static class OperatersInfo {
        private String UserID;//用户ID
        private String UserName;//完成用户

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String userID) {
            UserID = userID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }
    }

    public static class RecorderInfo {
        private String ApplyID;//
        private String Descript;//描述
        private List<RecordsBean> Records;//资源地址

        public String getApplyID() {
            return ApplyID;
        }

        public void setApplyID(String applyID) {
            ApplyID = applyID;
        }

        public String getDescript() {
            return Descript;
        }

        public void setDescript(String descript) {
            Descript = descript;
        }

        public List<RecordsBean> getRecords() {
            return Records;
        }

        public void setRecords(List<RecordsBean> records) {
            Records = records;
        }
    }

    public static class RecordsBean {
        private String ImgPath;//图片地址
        private String Thumbnail;//缩略图提到

        public String getImgPath() {
            return ImgPath;
        }

        public void setImgPath(String imgPath) {
            ImgPath = imgPath;
        }

        public String getThumbnail() {
            return Thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            Thumbnail = thumbnail;
        }
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public List<JobsInfo> getJobs() {
        return Jobs;
    }

    public void setJobs(List<JobsInfo> jobs) {
        Jobs = jobs;
    }
}
