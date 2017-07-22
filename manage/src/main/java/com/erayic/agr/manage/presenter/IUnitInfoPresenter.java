package com.erayic.agr.manage.presenter;

import com.erayic.agr.common.net.back.CommonUnitInfoBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IUnitInfoPresenter {

    /**
     * 得到管理单元详情
     */
    void getUnitDetailInfo(String unitID);

    void updateBlockInfo(String baseID,CommonUnitInfoBean bean);

}
