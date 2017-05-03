package com.erayic.agr.common.net.back;

import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUnitInfoBean {

    private boolean IsGreenhouse;
    private String UnitID;//管理单元ID
    private int Type;//管理单元类型
    private String EntID;//企业ID
    private String BaseID;//基地ID
    private String Name;//管理单元名称
    private String CreateTime;//创建时间
    private String Creater;//创建人ID
    private int Status;//管理单元状态
    private double Area;//面积（亩）
    private List<String> Region;//区域（GPS坐标点）
    private boolean IsSensor;//是否传感器
    private boolean IsMonitor;//是否监控
    private boolean IsControl;//是否远程控制
    private List<CommonMapArrayBean> Workers;//负责人

    public boolean isGreenhouse() {
        return IsGreenhouse;
    }

    public void setGreenhouse(boolean greenhouse) {
        IsGreenhouse = greenhouse;
    }

    public String getUnitID() {
        return UnitID;
    }

    public void setUnitID(String unitID) {
        UnitID = unitID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getEntID() {
        return EntID;
    }

    public void setEntID(String entID) {
        EntID = entID;
    }

    public String getBaseID() {
        return BaseID;
    }

    public void setBaseID(String baseID) {
        BaseID = baseID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreater() {
        return Creater;
    }

    public void setCreater(String creater) {
        Creater = creater;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
    }

    public List<String> getRegion() {
        return Region;
    }

    public void setRegion(List<String> region) {
        Region = region;
    }

    public boolean isSensor() {
        return IsSensor;
    }

    public void setSensor(boolean sensor) {
        IsSensor = sensor;
    }

    public boolean isMonitor() {
        return IsMonitor;
    }

    public void setMonitor(boolean monitor) {
        IsMonitor = monitor;
    }

    public boolean isControl() {
        return IsControl;
    }

    public void setControl(boolean control) {
        IsControl = control;
    }

    public  List<CommonMapArrayBean> getWorkers() {
        return Workers;
    }

    public void setWorkers( List<CommonMapArrayBean> workers) {
        Workers = workers;
    }
}
