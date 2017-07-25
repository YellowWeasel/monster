package com.erayic.agr.unit.event;

import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class BatchInfoEvent {

    private CommonUnitBatchInfoBean.Batch batchInfo;

    public CommonUnitBatchInfoBean.Batch getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(CommonUnitBatchInfoBean.Batch batchInfo) {
        this.batchInfo = batchInfo;
    }
}
