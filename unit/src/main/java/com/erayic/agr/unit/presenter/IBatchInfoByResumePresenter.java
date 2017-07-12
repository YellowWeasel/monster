package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchInfoByResumePresenter {

    /**
     * 得到作物批次的生产履历（刷新）
     */
    void getPorduceHistoryByBatch(String batchID,int pageSize);

    /**
     * 得到作物批次的生产履历(更多)
     */
    void getPorduceHistoryByBatch(String batchID,int pageNum,int pageSize);

}
