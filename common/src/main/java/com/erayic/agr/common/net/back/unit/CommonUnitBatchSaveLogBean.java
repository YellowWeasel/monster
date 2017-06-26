package com.erayic.agr.common.net.back.unit;

import com.erayic.agr.common.net.back.base.CommonImagesEntity;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUnitBatchSaveLogBean {

    private String logID = "00000000-0000-0000-0000-000000000000";//工作日志ID（Guid，新建时不需要该参数）
    private String batchID;//批次ID（Guid）
    private String descript;//	工作日志描述（string）
    private ApplyPic pics;


    public static class ApplyPic {

        private List<CommonImagesEntity> pics;//	图片集合（PicLib集合）

        public List<CommonImagesEntity> getPics() {
            return pics;
        }

        public void setPics(List<CommonImagesEntity> pics) {
            this.pics = pics;
        }
    }


    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public ApplyPic getPics() {
        return pics;
    }

    public void setPics(ApplyPic pics) {
        this.pics = pics;
    }
}
