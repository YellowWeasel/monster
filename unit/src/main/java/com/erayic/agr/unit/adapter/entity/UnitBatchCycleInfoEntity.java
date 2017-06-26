package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchCycleInfoEntity implements MultiItemEntity {

    public static final int TYPE_TITLE = 0;//标题栏
    public static final int TYPE_CYCLE = 1;//周期
    public static final int TYPE_ACCTEM = 2;//积温
    public static final int TYPE_ACCRAIN = 3;//累积降水

    private int itemType;

    private String name;
    private String subName;

    private Object data1;//其他数据
    private Object data2;//其他数据

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

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

}
