package com.erayic.agr.manage.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageProduceInfoEntity implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;
    public static final int TYPE_NAME = 1;//产品名称
    public static final int TYPE_ICON = 2;//产品头像
    public static final int TYPE_TYPE = 3;//产品类别
    public static final int TYPE_DEPICT = 4;//产品描述
    public static final int TYPE_IMAGE_LIST = 5;//图片集合

    private int itemType;
    private String name;
    private String subName;
    private Object data;

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
