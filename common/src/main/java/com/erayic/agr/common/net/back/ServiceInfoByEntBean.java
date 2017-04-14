package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceInfoByEntBean {

    private ServiceInfo Service;
    private List<CommonImageBean> Images;

    public static class ServiceInfo {
        private String ServiceID;//服务ID
        private String ServiceName;//服务名称
        private int Type;//服务类型
        private String Descript;//服务描述
        private CommonPriceBean MasterPrice;//主价格
        private boolean IsTry;//是否试用
        private boolean IsExpire;//过去
        private boolean UserIsTry;//用户是否试用
        private boolean IsBuy;//是否购买
        private String DueDate;//到期时间
        private String Icon;//服务图标

        public String getServiceID() {
            return ServiceID;
        }

        public void setServiceID(String serviceID) {
            ServiceID = serviceID;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String serviceName) {
            ServiceName = serviceName;
        }

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public String getDescript() {
            return Descript;
        }

        public void setDescript(String descript) {
            Descript = descript;
        }

        public CommonPriceBean getMasterPrice() {
            return MasterPrice;
        }

        public void setMasterPrice(CommonPriceBean masterPrice) {
            MasterPrice = masterPrice;
        }

        public boolean isTry() {
            return IsTry;
        }

        public void setTry(boolean aTry) {
            IsTry = aTry;
        }

        public boolean isExpire() {
            return IsExpire;
        }

        public void setExpire(boolean expire) {
            IsExpire = expire;
        }

        public boolean isUserIsTry() {
            return UserIsTry;
        }

        public void setUserIsTry(boolean userIsTry) {
            UserIsTry = userIsTry;
        }

        public boolean isBuy() {
            return IsBuy;
        }

        public void setBuy(boolean buy) {
            IsBuy = buy;
        }

        public String getDueDate() {
            return DueDate;
        }

        public void setDueDate(String dueDate) {
            DueDate = dueDate;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }
    }

    public ServiceInfo getService() {
        return Service;
    }

    public void setService(ServiceInfo service) {
        Service = service;
    }

    public List<CommonImageBean> getImages() {
        return Images;
    }

    public void setImages(List<CommonImageBean> images) {
        Images = images;
    }
}
