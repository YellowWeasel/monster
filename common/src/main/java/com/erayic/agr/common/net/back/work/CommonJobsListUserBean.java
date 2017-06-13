package com.erayic.agr.common.net.back.work;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：今日工作Bean
 */

public class CommonJobsListUserBean {

    private String Day;//日期
    private List<JobsInfo> Jobs;//工作内容

    public static class JobsInfo {
        private String JobName;
        private String JobID;
        private List<JobsContents> Contents;//工作内容集合
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

        public List<JobsContents> getContents() {
            return Contents;
        }

        public void setContents(List<JobsContents> contents) {
            Contents = contents;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }
    }

    public static class JobsContents {
        private String DemandTime;//需求时间
        private boolean IsFinish;//是否完成
        private String UnitID;//管理单元ID
        private String UnitName;//管理单元名称
        private String SchID;//作业调度ID
        private String ExecuteTm;//执行时间
        private String Descript;//描述
        private List<JobsContent> Content;//工作单项内容集合

        public String getDemandTime() {
            return DemandTime;
        }

        public void setDemandTime(String demandTime) {
            DemandTime = demandTime;
        }

        public boolean isFinish() {
            return IsFinish;
        }

        public void setFinish(boolean finish) {
            IsFinish = finish;
        }

        public String getUnitID() {
            return UnitID;
        }

        public void setUnitID(String unitID) {
            UnitID = unitID;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String unitName) {
            UnitName = unitName;
        }

        public String getSchID() {
            return SchID;
        }

        public void setSchID(String schID) {
            SchID = schID;
        }

        public String getExecuteTm() {
            return ExecuteTm;
        }

        public void setExecuteTm(String executeTm) {
            ExecuteTm = executeTm;
        }

        public String getDescript() {
            return Descript;
        }

        public void setDescript(String descript) {
            Descript = descript;
        }

        public List<JobsContent> getContent() {
            return Content;
        }

        public void setContent(List<JobsContent> content) {
            Content = content;
        }

    }

    public static class JobsContent {
        private String ContentID;//内容ID
        private String WorkerID;//工作人ID
        private String ResName;//资源名称
        private String Norm;//配比

        public String getContentID() {
            return ContentID;
        }

        public void setContentID(String contentID) {
            ContentID = contentID;
        }

        public String getWorkerID() {
            return WorkerID;
        }

        public void setWorkerID(String workerID) {
            WorkerID = workerID;
        }

        public String getResName() {
            return ResName;
        }

        public void setResName(String resName) {
            ResName = resName;
        }

        public String getNorm() {
            return Norm;
        }

        public void setNorm(String norm) {
            Norm = norm;
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
