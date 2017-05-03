package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonUnitInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUnitInfoView extends IBaseView{

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
    void refreshUnitInfoView(CommonUnitInfoBean bean);

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
    void updateSure();

}
