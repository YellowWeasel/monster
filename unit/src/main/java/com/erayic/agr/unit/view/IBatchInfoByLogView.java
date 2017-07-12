package com.erayic.agr.unit.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchInfoByLogView extends IBaseView{

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
    void refreshLogsView(List<CommonUnitBatchLogsBean> list);

    /**
     * 加载更多显示
     */
    void loadMoreSure(List<CommonUnitBatchLogsBean> list);

    /**
     * 加载更多失败
     */
    void loadMoreFailure();

}
