package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBindServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchBuyServiceBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchServiceBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：批次绑定服务
 */

public interface IBatchBindServiceView extends IBaseView {

    /**
     * 显示加载Dialog
     */
    void showLoading();

    /**
     * 隐藏加载Dialog
     */
    void dismissLoading();

    /**
     * 刷新绑定服务
     */
    void refreshServiceBindView(CommonUnitBatchBindServiceBean bean);

    /**
     * 刷新服务布局
     */
    void refreshServiceListView(List<CommonUnitBatchServiceBean> list);

    /**
     * 保存成功
     */
    void saveSure();

    /**
     * 跳转到服务购买页面
     */
    void toServiceBuy(CommonUnitBatchBuyServiceBean subServiceID);

}
