package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IAddUpdateBatchPresenter {

    /**
     * 新建批次
     */
    void createBatch(String proID, String seedID, String seedName, String quantity, String stTime, String ope, String unitID);

    /**
     * 更新批次
     */
    void updateBatch(String batchID, String proID, String seedID, String seedName, String quantity, String stTime,String ope, String unitID);

    /**
     * 删除批次
     */
    void deleteBatch(String batchID);

    /**
     * 批次完成
     */
    void finishBatch(String batchID, String finishTime);
}
