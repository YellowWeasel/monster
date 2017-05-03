package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IProduceListPresenter {

    /**
     * 创建一个企业产品
     */
    void createProduct(String productName);

    /**
     *  得到一个企业的所有产品
     */
    void getAllProduct();

}
