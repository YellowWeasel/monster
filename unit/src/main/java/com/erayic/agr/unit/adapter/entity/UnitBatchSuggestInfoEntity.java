package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchSuggestInfoEntity implements MultiItemEntity {

    public final static int TYPE_BATCH = 0;//批次
    public final static int TYPE_TITLE = 1;//标题
    public final static int TYPE_GIVE = 2;//适应性
    public final static int TYPE_WEATHER = 3;//未来24小时天气
    public final static int TYPE_ENVIRONMENT = 4;//当前环境

    private int itemType;

    private String name;
    private String subName;
    private Object object;

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
