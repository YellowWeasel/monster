package com.erayic.agr.service.presenter;

import com.erayic.agr.common.net.back.CommonSubServiceBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IServiceBuyByEntPresenter {

    void orderServiceByBuyOfEnt(String serviceID, int priceID, List<CommonSubServiceBean> subServiceIDs, int payMode);

}
