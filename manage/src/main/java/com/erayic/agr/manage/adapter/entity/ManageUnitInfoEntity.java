package com.erayic.agr.manage.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageUnitInfoEntity  implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;//分割线
    public static final int TYPE_UNIT_NAME = 1;//管理单元名称
    public static final int TYPE_UNIT_AREA = 2;//管理单元面积
    public static final int TYPE_UNIT_REGIN = 3;//管理单元位置
    public static final int TYPE_UNIT_WORK = 4;//管理单元负责人
    public static final int TYPE_UNIT_GREEN = 5;//是否为大棚

    private String name;
    private String subName;
    private boolean isGreenhouse;//是否为大棚

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

    public boolean isGreenhouse() {
        return isGreenhouse;
    }

    public void setGreenhouse(boolean greenhouse) {
        isGreenhouse = greenhouse;
    }
}
