package com.erayic.agr.common.net.back.work;

import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.base.CommonImagesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonJobsInfoBean {

    private String JobName;//作业名称
    private String JobID;//作业ID
    private String JobDescript;//作业描述
    private String UnitName;//管理单元名称
    private List<ContentInfo> Content;//内容信息
    private List<BatchInfo> Batchs;//批次信息
    private RecordsInfo Records;//工作记录
    private List<String> Workers;//操作人

    public static class ContentInfo {
        private String ContentID;
        private String WorkerID;//
        private String ResName;//资源名称
        private String Norm;//配比、占比

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

    public static class BatchInfo {
        private String ProductName;//批次名称
        private String SeedDate;//种植时间
        private String BatchID;//批次ID
        private boolean IsApply;//是否应用

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getSeedDate() {
            return SeedDate;
        }

        public void setSeedDate(String seedDate) {
            SeedDate = seedDate;
        }

        public String getBatchID() {
            return BatchID;
        }

        public void setBatchID(String batchID) {
            BatchID = batchID;
        }

        public boolean isApply() {
            return IsApply;
        }

        public void setApply(boolean apply) {
            IsApply = apply;
        }
    }

    public static class RecordsInfo {
        private String ApplyID = AgrConstant.GUID_Empty;//
        private String Descript = "";
        private List<CommonImagesEntity> Records;//工作记录

        public List<CommonImagesEntity> getRecords() {
            return Records;
        }

        public void setRecords(List<CommonImagesEntity> records) {
            Records = records;
        }

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
    }

//    public static class ApplyPicInfo {
//        private String ImgPath;
//        private String Thumbnail = "";
//
//        public String getImgPath() {
//            return ImgPath;
//        }
//
//        public void setImgPath(String imgPath) {
//            ImgPath = imgPath;
//        }
//
//        public String getThumbnail() {
//            return Thumbnail;
//        }
//
//        public void setThumbnail(String thumbnail) {
//            Thumbnail = thumbnail;
//        }
//    }

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

    public String getJobDescript() {
        return JobDescript;
    }

    public void setJobDescript(String jobDescript) {
        JobDescript = jobDescript;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public List<ContentInfo> getContent() {
        return Content;
    }

    public void setContent(List<ContentInfo> content) {
        Content = content;
    }

    public List<BatchInfo> getBatchs() {
        return Batchs;
    }

    public void setBatchs(List<BatchInfo> batchs) {
        Batchs = batchs;
    }

    public RecordsInfo getRecords() {
        return Records;
    }

    public void setRecords(RecordsInfo records) {
        Records = records;
    }

    public List<String> getWorkers() {
        return Workers;
    }

    public void setWorkers(List<String> workers) {
        Workers = workers;
    }

}
