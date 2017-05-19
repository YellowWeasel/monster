package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：选择产品
 */

public interface ISelectView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新产品信息
     */
    void refreshProductView(List<CommonProduceListBean> list);

    /**
     * 刷新资源信息（农药、化肥、种子）
     */
    void refreshResourceView(List<CommonResourceBean> list);

    /**
     * 刷新人员信息
     */
    void refreshUserView(List<CommonPersonnelBean> list);

}
