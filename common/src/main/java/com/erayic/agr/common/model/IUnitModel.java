package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */


public interface IUnitModel extends IProvider {

    /**
     * 得到管理单元列表
     */
    void getAllUnit(int type,OnDataListener<List<CommonUnitListBean>> listener);

    /**
     * 新建一个批次
     */
    void createBatch(String proID, String seedID,String seedName, String quantity, int unit, String stTime, String ope, String unitID, int unitType,OnDataListener<Object> listener);

}
