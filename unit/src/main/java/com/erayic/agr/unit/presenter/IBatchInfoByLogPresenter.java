package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchInfoByLogPresenter {

    /**
     * 得到批次所有的工作日志（刷新）
     */
    void getWorkLogByBatch(String batchID,int pageSize);

    /**
     * 得到批次所有的工作日志(加载更多)
     */
    void getWorkLogByBatch(String batchID,int pageNum,int pageSize);

}
