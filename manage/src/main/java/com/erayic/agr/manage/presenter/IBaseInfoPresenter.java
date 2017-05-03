package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBaseInfoPresenter {

    /**
     * 获取基地信息
     */
    void getBaseInfo(String baseID);

    /**
     * 增加管理单元
     */
    void addUnit(String baseID,String unitName);

    /**
     * 更新基地信息
     */
    void updateBaseInfo(String baseID,String baseName,String descript);

}
