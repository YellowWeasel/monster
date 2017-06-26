package com.erayic.agr.common.net.back.unit;

import com.erayic.agr.common.net.back.base.CommonImagesEntity;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：生产履历
 */

public class CommonUnitBatchResumeBean {

    private String OpeTime;//操作时间
    private String Operator;//操作人
    private RecoderInfo Recoder;//描述（描述+图片资源）
    private int OpeType;//操作类型
    private String SchID;
    private String UnitID;//管理单元ID
    private List<OpesInfo> Opes;

    public static class RecoderInfo{
        private String ApplyID;
        private String Descript;//描述
        private List<CommonImagesEntity> Records;//图片资源

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

        public List<CommonImagesEntity> getRecords() {
            return Records;
        }

        public void setRecords(List<CommonImagesEntity> records) {
            Records = records;
        }
    }

    public static class OpesInfo{
        private String OpeID;
        private String WorkID;
        private String ResName;//资源名称
        private String Norm;//资源配比

        public String getOpeID() {
            return OpeID;
        }

        public void setOpeID(String opeID) {
            OpeID = opeID;
        }

        public String getWorkID() {
            return WorkID;
        }

        public void setWorkID(String workID) {
            WorkID = workID;
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

    public String getOpeTime() {
        return OpeTime;
    }

    public void setOpeTime(String opeTime) {
        OpeTime = opeTime;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public RecoderInfo getRecoder() {
        return Recoder;
    }

    public void setRecoder(RecoderInfo recoder) {
        Recoder = recoder;
    }

    public int getOpeType() {
        return OpeType;
    }

    public void setOpeType(int opeType) {
        OpeType = opeType;
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

    public List<OpesInfo> getOpes() {
        return Opes;
    }

    public void setOpes(List<OpesInfo> opes) {
        Opes = opes;
    }
}
