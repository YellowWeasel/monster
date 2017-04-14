package com.erayic.agr.service.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.erayic.agr.common.net.back.CommonImageBean;
import com.erayic.agr.common.net.back.ServiceSystemBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：服务数据再次封装
 */

public class ServiceListByEntEntity implements MultiItemEntity {

    public final static int TYPE_BANNER = 0;
    public final static int TYPE_ITEM = 1;

    private int itemType;
    private List<CommonImageBean> headBeanList;
    private ServiceSystemBean.ServicesInfo servicesInfo;


    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public List<CommonImageBean> getHeadBeanList() {
        return headBeanList;
    }

    public void setHeadBeanList(List<CommonImageBean> headBeanList) {
        this.headBeanList = headBeanList;
    }

    public ServiceSystemBean.ServicesInfo getServicesInfo() {
        return servicesInfo;
    }

    public void setServicesInfo(ServiceSystemBean.ServicesInfo servicesInfo) {
        this.servicesInfo = servicesInfo;
    }
}
