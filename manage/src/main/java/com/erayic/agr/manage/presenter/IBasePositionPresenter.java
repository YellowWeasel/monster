package com.erayic.agr.manage.presenter;

import com.erayic.agr.common.net.back.manage.CommonBasePositionBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBasePositionPresenter {

    /**
     * 上传地理位置信息
     */
    void setBasePosition(String baseID, CommonBasePositionBean bean);

}
