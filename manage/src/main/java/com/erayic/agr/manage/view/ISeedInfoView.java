package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonSeedBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ISeedInfoView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 查询数据成功
     */
    void updateSure(CommonSeedBean bean);

    /**
     * 保存成功
     */
    void saveSure();

}
