package com.erayic.agr.common.net.back.work;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：预设作业详情
 */

public class CommonWorkInfoBean {

    private String JobID = "00000000-0000-0000-0000-000000000000";//作业ID
    private String JobName;//作业名称
    private String Descript;//作业描述
    private String UserID;//建立者
    private String UpdateTM;//最后更新时间
    private List<JobContent> Content;//操作集合


    public static class JobContent {
        private String ContentID;//操作ID
        private int OpeType;//操作类型（EnumOperationType类）
        private String ResID;//资源ID
        private String ResName;//资源名称
        private String Norm;//操作规范（配比，占比等）

        public String getContentID() {
            return ContentID;
        }

        public void setContentID(String contentID) {
            ContentID = contentID;
        }

        public int getOpeType() {
            return OpeType;
        }

        public void setOpeType(int opeType) {
            OpeType = opeType;
        }

        public String getResID() {
            return ResID;
        }

        public void setResID(String resID) {
            ResID = resID;
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

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUpdateTM() {
        return UpdateTM;
    }

    public void setUpdateTM(String updateTM) {
        UpdateTM = updateTM;
    }

    public List<JobContent> getContent() {
        return Content;
    }

    public void setContent(List<JobContent> content) {
        Content = content;
    }
}
