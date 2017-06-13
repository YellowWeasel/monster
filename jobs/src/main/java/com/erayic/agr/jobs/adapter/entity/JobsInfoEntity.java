package com.erayic.agr.jobs.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsInfoEntity implements MultiItemEntity {

    public static final int TYPE_WORK_CONTENT = 0;//工作内容
    public static final int TYPE_WORK_REQUIRE = 1;//工作要求
    public static final int TYPE_WORK_UNIT = 2;//工作范围
    public static final int TYPE_WORK_NOTE = 3;//工作记录

    private int itemType;
    private String name;
    private String subName;
    private Map<String,Object> map;

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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
