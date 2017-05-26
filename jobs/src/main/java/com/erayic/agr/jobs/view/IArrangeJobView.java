package com.erayic.agr.jobs.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.work.CommonJobListBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IArrangeJobView extends IBaseView{

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新工作安排列表
     */
    void refreshJobView(List<CommonJobListBean> list);

}
