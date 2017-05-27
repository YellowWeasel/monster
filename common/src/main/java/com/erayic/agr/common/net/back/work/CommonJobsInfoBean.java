package com.erayic.agr.common.net.back.work;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：今日工作Bean
 */

public class CommonJobsInfoBean {

    private String Day;//日期
    private List<JobsInfo> Jobs;//工作内容

    public static class JobsInfo {
        private String testName;
        private List<childJobInfo> childJobInfos;

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public List<childJobInfo> getChildJobInfos() {
            return childJobInfos;
        }

        public void setChildJobInfos(List<childJobInfo> childJobInfos) {
            this.childJobInfos = childJobInfos;
        }
    }

    public static class childJobInfo {
        private String testChildName;

        public String getTestChildName() {
            return testChildName;
        }

        public void setTestChildName(String testChildName) {
            this.testChildName = testChildName;
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
