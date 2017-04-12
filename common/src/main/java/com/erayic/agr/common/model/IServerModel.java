package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
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
}
