package com.erayic.agr.jobs.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobInfoEntity implements MultiItemEntity {

    public static final int TYPE_JOB_DIVIDER = 0;//分割线
    public static final int TYPE_JOB_WORK = 1;//选择作业
    public static final int TYPE_JOB_UNIT_ADD = 2;//增加管理单元
    public static final int TYPE_JOB_UNIT = 3;//管理单元
    public static final int TYPE_JOB_DATE = 4;//选择时间
    public static final int TYPE_JOB_NOTICE = 5;//选择通知方式
    public static final int TYPE_JOB_NOTICE_DATE = 6;//选择通知时间

    private String name;
    private String subName;
    private Object ID;

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

    public Object getID() {
        return ID;
    }

    public void setID(Object ID) {
        this.ID = ID;
    }
}
