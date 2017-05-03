package com.erayic.agr.manage.presenter;

import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.common.net.back.CommonPesticideBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IPesticideInfoPresenter {

    /**
     * 查询农药库
     */
    void pestilizerCheck(String pID);

    /**
     * 增加农药
     */
    void addPesticide(CommonPesticideBean bean);

}
