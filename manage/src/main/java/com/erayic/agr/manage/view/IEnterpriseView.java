package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonEntInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：企业信息
 */

public interface IEnterpriseView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新企业信息
     */
    void refreshPersonnelView(CommonEntInfoBean bean);

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 修改资料成功
     */
    void updateSure(String strBuffer);

}
