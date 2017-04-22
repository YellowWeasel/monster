package com.erayic.agr.service.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IServiceEntranceView extends IBaseView {

    /**
     * 刷新列表
     */
    void refreshView(List<ServiceBuyByUserBean> list);

}
