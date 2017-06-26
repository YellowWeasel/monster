package com.erayic.agr.unit.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchItemEntity implements MultiItemEntity {

    public static final int TYPE_TITLE_MODEL = 0;//生长期评估标题
    public static final int TYPE_TITLE_SUGGEST = 1;//适应性标题
    public static final int TYPE_TITLE_PRODUCE = 2;//产量评估标题
    public static final int TYPE_MODEL = 3;//生产模型
    public static final int TYPE_SUGGEST = 4;//适应性
    public static final int TYPE_PRODUCE = 5;//产量评估

    private int itemType;

    private boolean isBind;//是否绑定服务
    private String name;
    private String subName;
    private Object Data;

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

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
