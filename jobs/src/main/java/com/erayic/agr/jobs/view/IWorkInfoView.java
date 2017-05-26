package com.erayic.agr.jobs.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IWorkInfoView extends IBaseView{

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 更新或者删除数据成功
     */
    void updateOrDeleteSure();

    /**
     * 查询数据成功
     */
    void selectSure(CommonWorkInfoBean bean);

}
