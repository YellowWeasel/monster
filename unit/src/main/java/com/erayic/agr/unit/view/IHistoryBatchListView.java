package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：历史批次
 */

public interface IHistoryBatchListView extends IBaseView{

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
    void refreshBatchView(List<CommonUnitBatchInfoBean.Batch> list);

    /**
     * 加载更多显示
     */
    void loadMoreSure(List<CommonUnitBatchInfoBean.Batch> list);

    /**
     * 加载更多失败
     */
    void loadMoreFailure();

}
