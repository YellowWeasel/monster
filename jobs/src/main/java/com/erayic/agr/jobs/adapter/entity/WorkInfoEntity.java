package com.erayic.agr.jobs.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class WorkInfoEntity implements MultiItemEntity {

    public static final int TYPE_WORK_DIVIDER = 0;//分割线
    public static final int TYPE_WORK_NAME = 1;//操作名称
    public static final int TYPE_WORK_CONTENT_TITLE_ADD = 2;//操作内容Title
    public static final int TYPE_WORK_CONTENT_FERTILIZE = 3;//施肥
    public static final int TYPE_WORK_CONTENT_PESTICIDE = 4;//打药
    public static final int TYPE_WORK_CONTENT_PICK = 5;//采摘
    public static final int TYPE_WORK_CONTENT_OTHER = 6;//其他
    public static final int TYPE_WORK_CONTENT_TITLE_SHOW = 7;//工作要求Title
    public static final int TYPE_WORK_CONTENT_NORM = 8;//工作要求

    private String name;
    private String subName;
    private String subProp;
    private String ID;


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

    public String getSubProp() {
        return subProp;
    }

    public void setSubProp(String subProp) {
        this.subProp = subProp;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
