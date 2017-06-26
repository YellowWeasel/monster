package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchInfoByStatuePresenter {

    /**
     * 得到批次详细信息
     */
    void getBatchInfo(String unitID,int unitType,String batchID);

}
