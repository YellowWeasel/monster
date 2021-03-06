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
     * 得到指定农药的信息
     */
    void getSpecifyResources(String resID,int type);

    /**
     * 删除一个生产资料
     */
    void deleteResource(String resID,int type);

    /**
     * 保存农药
     */
    void savePesticide(CommonPesticideBean bean);

    /**
     * 保存一个自定义农药
     */
    void savePesticideByUserDefine(CommonPesticideBean bean);

}
