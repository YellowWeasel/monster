package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.net.back.unit.CommonUnitListByBaseBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.manage.adapter.entity.ManageNoticeEntity;

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

    /**
     * 刷新预设工作信息
     */
    void refreshWorkView(List<CommonWorkListBean> list);

    /**
     * 刷新管理单元列表
     */
    void refreshUnitView(List<CommonUnitListByBaseBean> list);

    /**
     * 刷新通知方式列表
     */
    void refreshNoticeView(List<ManageNoticeEntity> list);

}
