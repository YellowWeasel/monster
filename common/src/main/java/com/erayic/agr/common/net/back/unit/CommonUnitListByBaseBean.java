package com.erayic.agr.common.net.back.unit;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：只含管理单元信息
 */

public class CommonUnitListByBaseBean {

    private String UnitID;
    private String Name;
    private List<WorkersInfo> Workers;


    public static class WorkersInfo{
        private String Key;//负责人ID
        private String Value;//负责人名称

        public String getKey() {
            return Key;
        }

        public void setKey(String key) {
            Key = key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }
    }

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

    public List<WorkersInfo> getWorkers() {
        return Workers;
    }

    public void setWorkers(List<WorkersInfo> workers) {
        Workers = workers;
    }
}
