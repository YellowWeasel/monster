package com.erayic.agr.manage.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageBaseInfoEntity implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;//分割线
    public static final int TYPE_BASE_NAME = 1;//基地名称
    public static final int TYPE_BASE_IMAGE = 2;//基地图片
    public static final int TYPE_BASE_POSITION = 3;//基地位置
    public static final int TYPE_BASE_ADD_UNIT = 4;//增加管理单元
    public static final int TYPE_BASE_UNIT_NAME = 5;//管理单元信息
    public static final int TYPE_BASE_DEPICT = 6;//描述

    private int icon;//图片
    private String name;//名称
    private String subName;//副标题
    private String unitID;//管理单元ID

    private int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }
}
