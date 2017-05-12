package com.erayic.agr.common.net.back;

import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonResUnitListBean {

    private String UnitID;//管理单元ID
    private String Name;//管理单元名称
    private List<CommonMapArrayBean> Workers;//负责人

    public String getUnitID() {
        return UnitID;
    }

    public void setUnitID(String unitID) {
        UnitID = unitID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<CommonMapArrayBean> getWorkers() {
        return Workers;
    }

    public void setWorkers(List<CommonMapArrayBean> workers) {
        Workers = workers;
    }
}
