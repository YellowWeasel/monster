package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：批次详情生长数据
 */

public interface IBatchInfoByStatueView extends IBaseView {

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();


    /**
     * 刷新数据
     */
    void refreshBatchView(CommonUnitBatchInfoBean bean);

}
