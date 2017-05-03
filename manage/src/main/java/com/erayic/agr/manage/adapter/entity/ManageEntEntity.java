package com.erayic.agr.manage.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageEntEntity implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;//分割线
    public static final int TYPE_ENT_NEMA = 1;//企业名称
    public static final int TYPE_ENT_BL = 2;//营业执照
    public static final int TYPE_ENT_OCC = 3;//组织机构代码证
    public static final int TYPE_ENT_SERVICE = 4;//基础服务
    public static final int TYPE_ENT_CONTACTER = 5;//联系人
    public static final int TYPE_ENT_IMAGE = 6;//企业照片
    public static final int TYPE_ENT_OTHER = 7;//其他资质
    public static final int TYPE_ENT_DEPICT = 8;//描述

    private int itemType;

    private String title;//名称
    private String subTitle;//副名称
    private boolean subBool;//副名称boolean表示

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public boolean isSubBool() {
        return subBool;
    }

    public void setSubBool(boolean subBool) {
        this.subBool = subBool;
    }
}
