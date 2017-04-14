package com.erayic.agr.service.adapter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.erayic.agr.common.net.back.CommonImageBean;
import com.erayic.agr.common.net.back.CommonPriceBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntEntity implements MultiItemEntity {

    public final static int TYPE_TITLE = 0;
    public final static int TYPE_DESCRIPT = 1;
    public final static int TYPE_PRICE = 2;
    public final static int TYPE_IMAGES = 3;

    private int itemType;
    private ServiceInfoByEntTitle title;
    private ServiceInfoByEntDescript descript;
    private ServiceInfoByEntPrice price;
    private List<CommonImageBean> imageBeanList;

    @Override
    public int getItemType() {
        return itemType;
    }

    public static class ServiceInfoByEntTitle{
        private String serviceIcon;//服务图标
        private String serviceName;//服务名称

        public String getServiceIcon() {
            return serviceIcon;
        }

        public void setServiceIcon(String serviceIcon) {
            this.serviceIcon = serviceIcon;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }

    public static class ServiceInfoByEntDescript{
        private String serviceDescript;//服务介绍

        public String getServiceDescript() {
            return serviceDescript;
        }

        public void setServiceDescript(String serviceDescript) {
            this.serviceDescript = serviceDescript;
        }
    }

    public static class ServiceInfoByEntPrice{
        private CommonPriceBean servicePrice;
        private boolean trys;//是否试用
        private boolean expire;//是否到期
        private boolean userTry;//用户是否试用
        private boolean buy;//是否购买
        private String dueDate;//到期时间

        public CommonPriceBean getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(CommonPriceBean servicePrice) {
            this.servicePrice = servicePrice;
        }

        public boolean isTrys() {
            return trys;
        }

        public void setTrys(boolean trys) {
            this.trys = trys;
        }

        public boolean isExpire() {
            return expire;
        }

        public void setExpire(boolean expire) {
            this.expire = expire;
        }

        public boolean isUserTry() {
            return userTry;
        }

        public void setUserTry(boolean userTry) {
            this.userTry = userTry;
        }

        public boolean isBuy() {
            return buy;
        }

        public void setBuy(boolean buy) {
            this.buy = buy;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public ServiceInfoByEntTitle getTitle() {
        return title;
    }

    public void setTitle(ServiceInfoByEntTitle title) {
        this.title = title;
    }

    public ServiceInfoByEntDescript getDescript() {
        return descript;
    }

    public void setDescript(ServiceInfoByEntDescript descript) {
        this.descript = descript;
    }

    public ServiceInfoByEntPrice getPrice() {
        return price;
    }

    public void setPrice(ServiceInfoByEntPrice price) {
        this.price = price;
    }

    public List<CommonImageBean> getImageBeanList() {
        return imageBeanList;
    }

    public void setImageBeanList(List<CommonImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }
}
