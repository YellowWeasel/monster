package com.erayic.agr.manage.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonFertilizerBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IFertilizerInfoView extends IBaseView {

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
    void updateSure(CommonFertilizerBean bean);

    /**
     * 保存成功
     */
    void saveSure();

}
