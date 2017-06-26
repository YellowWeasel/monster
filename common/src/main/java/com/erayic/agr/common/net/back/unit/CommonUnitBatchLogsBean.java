package com.erayic.agr.common.net.back.unit;

import com.erayic.agr.common.net.back.base.CommonImagesEntity;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUnitBatchLogsBean {

    private String LogID;//日志ID
    private String BatchID;//批次ID
    private String Descipt;//描述
    private String RecordTime;//记录时间
    private List<CommonImagesEntity> Images;//图片集合
    private String Recorder;//记录用户ID
    private String RecordName;//记录人

    public String getLogID() {
        return LogID;
    }

    public void setLogID(String logID) {
        LogID = logID;
    }

    public String getBatchID() {
        return BatchID;
    }

    public void setBatchID(String batchID) {
        BatchID = batchID;
    }

    public String getDescipt() {
        return Descipt;
    }

    public void setDescipt(String descipt) {
        Descipt = descipt;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }

    public List<CommonImagesEntity> getImages() {
        return Images;
    }

    public void setImages(List<CommonImagesEntity> images) {
        Images = images;
    }

    public String getRecorder() {
        return Recorder;
    }

    public void setRecorder(String recorder) {
        Recorder = recorder;
    }

    public String getRecordName() {
        return RecordName;
    }

    public void setRecordName(String recordName) {
        RecordName = recordName;
    }
}
