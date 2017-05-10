package com.erayic.agr.manage.adapter.entity;

import android.support.v4.util.ArrayMap;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageFertilizerEntity implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;//分割线
    public static final int TYPE_IMPORT_NAME = 1;//化肥名称（输入）
    public static final int TYPE_IMPORT_MANUFACTURER = 2;//生产厂家（输入）
    public static final int TYPE_COMMON_NAME = 3;//产品通用名称
    public static final int TYPE_PRODUCT_NAME = 4;//产品商家名称
    public static final int TYPE_MANUFACTURER = 5;//生产厂家
    public static final int TYPE_CROPS = 6;//适宜作物
    public static final int TYPE_NORM = 7;//技术指标
    public static final int TYPE_SHAPE = 8;//产品形态
    public static final int TYPE_PID = 9;//产品PID

    private String name;//标题名称
    private Map<String, String> subMap = new ArrayMap<>();//副标题名称(key:sub1,sub2...subN)

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

    public Map<String, String> getSubMap() {
        return subMap;
    }

    public void setSubMap(Map<String, String> subMap) {
        this.subMap = subMap;
    }
}
