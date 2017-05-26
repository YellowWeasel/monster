package com.erayic.agr.manage.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageSelectEntity implements MultiItemEntity {

    public static final int TYPE_SELECT_USER = 1;//选择用户
    public static final int TYPE_SELECT_PRODUCT = 2;//选择产品
    public static final int TYPE_SELECT_FERTILIZER = 3;//选择肥料
    public static final int TYPE_SELECT_PESTICIDE = 4;//选择农药
    public static final int TYPE_SELECT_SEED = 5;//选择种苗
    public static final int TYPE_SELECT_WORK = 6;//选择工作

    private String icon;//头像
    private String name;//名称
    private String subName;//副名称
    private Object ID;//
    private boolean isCheck;//是否选择

    private int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Object getID() {
        return ID;
    }

    public void setID(Object ID) {
        this.ID = ID;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
