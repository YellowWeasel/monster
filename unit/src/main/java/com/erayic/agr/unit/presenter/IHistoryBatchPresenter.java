package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IHistoryBatchPresenter {

    /**
     * 得到历史批次(刷新)
     */
    void getAllBatchByHistory(String unitID, int pageSize);

    /**
     * 得到历史批次（加载）
     */
    void getAllBatchByHistory(String unitID, int pageNum, int pageSize);

}
