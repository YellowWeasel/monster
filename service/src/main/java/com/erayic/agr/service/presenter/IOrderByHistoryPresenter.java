package com.erayic.agr.service.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IOrderByHistoryPresenter {

    /**
     * 得到用户的历史订单列表(不含未支付）
     */
    void getHistoryOrderListByUser(int pageSize);//第一页条数

    /**
     * 得到用户的历史订单列表(不含未支付）
     */
    void getHistoryOrderListByUser(int pageNum, int pageSize);//页数 每页条数

}
