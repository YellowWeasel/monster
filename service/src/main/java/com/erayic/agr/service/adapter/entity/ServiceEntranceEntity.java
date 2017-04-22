package com.erayic.agr.service.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：我的服务 参数
 */

public class ServiceEntranceEntity implements MultiItemEntity {

    public static final int TYPE_SERVICE = 0;

    private int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }
}
