package com.erayic.agr.manage.presenter;

import com.erayic.agr.common.net.back.CommonFertilizerBean;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IFertilizerInfoPresenter {

    /**
     * 查询肥料库
     */
    void fertilizerCheck(String pID);

    /**
     * 增加肥料
     */
    void addFertilizer(CommonFertilizerBean bean);

    /**
     * 得到指定农药的信息
     */
    void getSpecifyResources(String resID, int type);

    /**
     * 删除一个生产资料
     */
    void deleteResource(String resID, int type);

    /**
     * 更新肥料
     */
    void updateFertilizer(CommonFertilizerBean bean);

}
