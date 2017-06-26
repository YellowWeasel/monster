package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchInfoByLogPresenter {

    /**
     * 得到批次所有的工作日志
     */
    void getWorkLogByBatch(String batchID);

}
