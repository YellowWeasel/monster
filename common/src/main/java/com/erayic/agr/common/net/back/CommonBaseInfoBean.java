package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonBaseInfoBean {

    private String baseID;//基地ID
    private String name;//基地名称
    private String descript;//基地描述
    private boolean isRegion;//是否上传位置
    private double lon;//经度
    private double lat;//纬度
    private String address;//地址
    private List<CommonResUnitListBean> units;//基地管理单元列表

    public String getBaseID() {
        return baseID;
    }

    public void setBaseID(String baseID) {
        this.baseID = baseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public boolean isRegion() {
        return isRegion;
    }

    public void setRegion(boolean region) {
        isRegion = region;
    }

    public List<CommonResUnitListBean> getUnits() {
        return units;
    }

    public void setUnits(List<CommonResUnitListBean> units) {
        this.units = units;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
