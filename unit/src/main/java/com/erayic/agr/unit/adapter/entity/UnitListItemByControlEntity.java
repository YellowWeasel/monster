package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListItemByControlEntity implements MultiItemEntity {

    public static final int TYPE_ITEM_EQU = 0;//设备
    public static final int TYPE_ITEM_ST = 1;//启停
    public static final int TYPE_ITEM_PN = 2;//正反转
    public static final int TYPE_NO_EQU = 3;//没有任何设备

    private String name;
    private String subName;
    private Map<String, String> map;

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
