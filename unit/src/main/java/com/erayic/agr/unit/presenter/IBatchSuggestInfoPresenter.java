package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchSuggestInfoPresenter {


    /**
     *得到生产建议详情
     */
    void getSuggestDetail(String unitID, int type, String batchID);

}
