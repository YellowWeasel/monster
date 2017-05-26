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

    /**
     * 得到已定义作业列表
     */
    void getJobList();

    /**
     * 得到所有管理单元列表
     */
    void getUnitList();

    /**
     * 得到所有通知方式
     */
    void getNoticeList();

}
