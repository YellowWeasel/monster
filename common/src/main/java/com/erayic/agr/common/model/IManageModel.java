package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.CommonBaseInfoBean;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.common.net.back.CommonEntInfoBean;
import com.erayic.agr.common.net.back.CommonUnitInfoBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IManageModel extends IProvider {

    /**
     * 获取企业信息
     */
    void getEntInfo(OnDataListener<CommonEntInfoBean> listener);

    /**
     * 更新企业名称
     */
    void updateEntName(String newEntName, OnDataListener<Object> listener);


    /**
     * 得到一个企业的所有基地列表
     */
    void getBaseByEnt(OnDataListener<List<CommonBaseListBean>> listener);

    /**
     * 增加一个企业基地
     */
    void addBaseByEnt(String newBaseName, OnDataListener<Object> listener);

    /**
     * 用户变更所属基地
     */
    void changeBase(String newBaseID, OnDataListener<Object> listener);

    /**
     * 上传基地位置信息
     */

    /**
     * 得到基地信息
     */
    void getBaseInfo(String baseID,OnDataListener<CommonBaseInfoBean> listener);

    /**
     * 增加管理单元
     */
    void addUnit(String baseID,String unitName,OnDataListener<Object> listener);

    /**
     * 更新基地信息
     */
    void updateBaseInfo(String baseID,String baseName,String descript,OnDataListener<Object> listener);

    /**
     * 得到管理单元详情
     */
    void getUnitDetailInfo(String unitID, int type,OnDataListener<CommonUnitInfoBean> listener);

    /**
     * 更新地块信息
     */
    void  updateBlockInfo(String unitID, String unitName, double area, List<String> regions, List<String> workes, boolean isGreenhouse,OnDataListener<Object> listener);

}
