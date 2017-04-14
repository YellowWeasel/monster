package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonPriceBean;
import com.erayic.agr.common.net.back.ServiceInfoByEntBean;
import com.erayic.agr.common.net.back.ServiceSystemBean;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IServerModel extends IProvider {

    /**
     * 得到用户所拥有的所有服务
     */
    void getAllServiceByUser(OnDataListener<List<ServiceBuyByUserBean>> listener);

    /**
     * 得到系统所有的服务信息
     */
    void getAllSystemServiceByEnt(OnDataListener<ServiceSystemBean> listener);

    /**
     * 以企业身份得到一个服务详情
     */
    void getSpecifyServiceByEnt(String serviceID,OnDataListener<ServiceInfoByEntBean> listener);

    /**
     * 得到服务所有的价格
     */
    void getAllPriceByService(String serviceID, OnDataListener<List<CommonPriceBean>> listener);
}
