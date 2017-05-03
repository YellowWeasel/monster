package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IPesticideListView extends IBaseView {


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
    void refreshPersonnelView(List<Object> list);

}
