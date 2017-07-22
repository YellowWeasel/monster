package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonProduceInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：产品详情
 */

public interface IProduceInfoView extends IBaseView {

    /**
     * 刷新产品列表
     */
    void refreshPersonnelView(CommonProduceInfoBean bean);

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 更新icon成功
     */
    void updateIconSure(String url);

    /**
     * 通知产品页面刷新
     */
    void noticeRefresh();

    /**
     * 关闭页面
     */
    void finishCurrent();

    /**
     *
     */

}
