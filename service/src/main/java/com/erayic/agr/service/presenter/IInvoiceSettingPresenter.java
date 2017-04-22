package com.erayic.agr.service.presenter;

import com.erayic.agr.common.net.back.CommonInvoiceBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IInvoiceSettingPresenter {

    /**
     * 得到一个企业发票开具信息
     */
    void getInvoiceTitleInfo();

    /**
     * 更新一个企业发票开具信息
     */
    void updateInvoiceInfo(CommonInvoiceBean bean);

}
