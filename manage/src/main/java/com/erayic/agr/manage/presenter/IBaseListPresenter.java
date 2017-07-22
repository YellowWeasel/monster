package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBaseListPresenter {

    /**
     * 得到一个企业的所有基地列表
     */
    void getBaseByEnt();

    /**
     * 增加一个企业基地
     */
    void addBaseByEnt(String newBaseName,String PhoneCode);

}
