package com.erayic.agr.service.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonOrderBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：订购历史
 */

public interface IOrderByHistoryView extends IBaseView{

    /**
     * 开启刷新
     */
    void openRefresh();

    /**
     * 关闭刷新
     */
    void clearRefresh();

    /**
     * 刷新列表
     */
    void refreshOrderView(List<CommonOrderBean> list);

    /**
     * 加载更多显示
     */
    void loadMoreSure(List<CommonOrderBean> list);

    /**
     * 加载更多失败
     */
    void loadMoreFailure();

}
