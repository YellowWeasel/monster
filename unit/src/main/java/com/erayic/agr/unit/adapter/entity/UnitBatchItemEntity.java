package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchItemEntity implements MultiItemEntity {

    public static final int TYPE_TITLE = 0;//标题
    public static final int TYPE_BATCH = 1;//批次详情
    public static final int TYPE_MODEL = 2;//模型（生产模型、产量评估）
    public static final int TYPE_SUGGEST = 3;//适应性

    private int itemType;

    private String name;
    private String subName;

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
}
