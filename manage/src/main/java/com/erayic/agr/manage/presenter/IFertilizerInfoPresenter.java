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
     * 得到指定农药的信息
     */
    void getSpecifyResources(String resID, int type);

    /**
     * 删除一个生产资料
     */
    void deleteResource(String resID, int type);

    /**
     * 保存肥料
     */
    void saveFertilizer(CommonFertilizerBean bean);

    /**
     * 保存一个自定义肥料
     */
    void saveFertilizerByUserDefine(CommonFertilizerBean bean);

}
