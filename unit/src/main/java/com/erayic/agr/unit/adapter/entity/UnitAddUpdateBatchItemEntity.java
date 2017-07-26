package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitAddUpdateBatchItemEntity implements MultiItemEntity {

    public static final int TYPE_PRODUCT = 0;//产品
    public static final int TYPE_SEED = 1;//种苗
    public static final int TYPE_AREA = 2;//面积
    public static final int TYPE_DATE = 3;//时间
    public static final int TYPE_PERSONNEL = 4;//种植人员

    public static final int TYPE_DELETE = 5;//删除批次
    public static final int TYPE_FINISH = 6;//批次完成

    public static final int TYPE_DIVIDER = 7;//分割区域

    private String name;
    private String subName;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
