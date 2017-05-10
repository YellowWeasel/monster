package com.erayic.agr.manage.presenter;

import com.erayic.agr.common.net.back.CommonSeedBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ISeedInfoPresenter {

    /**
     * 得到指定种子的信息
     */
    void getSpecifyResources(String resID, int type);

    /**
     * 删除一个生产资料
     */
    void deleteResource(String resID, int type);

    /**
     * 保存种子
     */
    void saveSeed(CommonSeedBean bean);

}
