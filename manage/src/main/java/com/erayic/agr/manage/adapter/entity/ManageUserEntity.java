package com.erayic.agr.manage.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageUserEntity implements MultiItemEntity {

    public static final int TYPE_DIVIDER = 0;
    public static final int TYPE_USER_ICON = 1;//头像
    public static final int TYPE_USER_NAME = 2;//姓名
    public static final int TYPE_USER_TEL = 3;//电话号码
    public static final int TYPE_USER_ENT = 4;//企业名称
    public static final int TYPE_USER_BASE = 5;//基地信息
    public static final int TYPE_USER_WEIXIN = 6;//微信
    public static final int TYPE_USER_PASS = 7;//更换密码
    public static final int TYPE_USER_LOGOUT = 8;//注销登录

    private int itemType;
    private String title;
    private String subTitle;

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
}
