package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.CommonUserInfoBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：人员信息
 */

public interface IPersonnelListView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新人员列表
     */
    void refreshPersonnelView(List<CommonPersonnelBean> list);

}
