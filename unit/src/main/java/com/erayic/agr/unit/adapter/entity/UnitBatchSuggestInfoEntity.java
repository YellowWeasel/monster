package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchSuggestInfoEntity implements MultiItemEntity {

    public final static int TYPE_TITLE = 0;//标题
    public final static int TYPE_SUGGEST = 1;//适应性
    public final static int TYPE_WEATHER = 2;//未来24小时天气
    public final static int TYPE_ENVIRONMENT = 3;//当前环境

    private int itemType;

    private String name;
    private String subName;
    private Object data;
    private Object subData;

    public Object getSubData() {
        return subData;
    }

    public void setSubData(Object subData) {
        this.subData = subData;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
