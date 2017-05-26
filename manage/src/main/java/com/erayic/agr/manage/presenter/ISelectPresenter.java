package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ISelectPresenter {

    /**
     * 得到一个企业的所有产品
     */
    void getAllProduct();

    /**
     * 得到资源
     */
    void getResourceByType(int type);

    /**
     * 得到基地所有用户
     */
    void getAllUserByBase(String baseID);

}
