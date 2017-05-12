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

    void getAllUnit(int type,OnDataListener<List<CommonUnitListBean>> listener);

}
