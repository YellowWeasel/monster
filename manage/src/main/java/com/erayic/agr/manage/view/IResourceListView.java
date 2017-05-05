package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.net.back.CommonResourceBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IResourceListView extends IBaseView {


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
    void refreshPersonnelView(List<CommonResourceBean> list);

}
