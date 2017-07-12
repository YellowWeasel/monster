package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListItemByEnvironmentEntity implements MultiItemEntity {

    public static final int TYPE_AIR_TEM = 0;//空气温度
    public static final int TYPE_AIR_HUM = 1;//空气湿度
    public static final int TYPE_SOIL_TEM = 2;//土壤温度
    public static final int TYPE_SOIL_HUM = 3;//土壤湿度
    public static final int TYPE_WATER = 4;//降水量
    public static final int TYPE_ILL = 5;//光照强度
    public static final int TYPE_WIND = 6;//风力
    public static final int TYPE_CO2 = 7;//二氧化碳

    private String name;
    private String subName;
    private Map<String,String> map;

    private int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
