package com.erayic.agr.service.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IServiceManagePresenter {

    /**
     * 得到用户已拥有的所有服务
     */
    void getAllSystemServiceByUser();

    /**
     * 用户订购服务
     */
    void orderServiceByBuyOfEntUser(String serviceID);

    /**
     * 用户取消服务
     */
    void cancelUserService(String serviceID);

}
