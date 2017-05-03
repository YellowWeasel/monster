package com.erayic.agr.manage.adapter.entity;

import android.support.v4.util.ArrayMap;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManagePesticideEntity implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;//分割线
    public static final int TYPE_NUM = 1;//登记证号
    public static final int TYPE_NAME = 2;//农药名称
    public static final int TYPE_TOXICITY = 3;//农药毒性
    public static final int TYPE_DOSAGE = 4;//剂型
    public static final int TYPE_FACTORY = 5;//生产厂家
    public static final int TYPE_START_TIME = 6;//有效起始日
    public static final int TYPE_END_TIME = 7;//有效截止日
    public static final int TYPE_ACTIVE_ADD = 8;//有效成分ADD
    public static final int TYPE_ACTIVE = 9;//有效成分
    public static final int TYPE_METHOD_ADD = 10;//用法ADD
    public static final int TYPE_METHOD = 11;//用法ADD

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
