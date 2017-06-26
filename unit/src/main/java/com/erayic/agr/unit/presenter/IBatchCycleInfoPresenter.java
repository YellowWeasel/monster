package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchCycleInfoPresenter {

    /**
     * 得到作物的生长评估详情(生长期、积温、累积降水)
     */
    void getCycleDetailByBatch(String batchID, String unitID, int type);

    /**
     * 得到批次的生长周期
     */
    void getCycleByBatch(String batchID);

}
